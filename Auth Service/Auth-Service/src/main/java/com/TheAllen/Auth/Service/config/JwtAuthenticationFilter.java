package com.TheAllen.Auth.Service.config;

import com.TheAllen.Auth.Service.models.InstaUserDetails;
import com.TheAllen.Auth.Service.service.JwtProvider;
import com.TheAllen.Auth.Service.service.UserService;
import com.ctc.wstx.util.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtConfig jwtConfig;

    private JwtProvider jwtProvider;

    private UserService userService;

    private String serviceUsername;

    public JwtAuthenticationFilter(JwtConfig jwtConfig, JwtProvider jwtProvider, UserService userService, String serviceUsername) {
        this.jwtConfig = jwtConfig;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.serviceUsername = serviceUsername;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        /*
            Look to store the JWT in the cookies as HttpOnly
         */
        String header = httpServletRequest.getHeader(jwtConfig.getHeader());

        //Verify header to the prefix
        if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //Retrieve token
        //TODO get the jwt from the cookies
        String token = httpServletRequest.getHeader(jwtConfig.getPrefix());

        if(StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
            Claims claims = jwtProvider.getClaimsFromJWT(token);
            String username = claims.getSubject();

            UsernamePasswordAuthenticationToken auth = null;

            if(username.equals(serviceUsername)) {

                List<String> authorities = (List<String>) claims.get("authorities");

                auth = new UsernamePasswordAuthenticationToken(username, null,
                                authorities.stream().map(SimpleGrantedAuthority::new).collect(toList()));
            }
            else {

                auth = userService.findByUsername(username).map(InstaUserDetails::new)
                                .map(userDetails -> {

                                    UsernamePasswordAuthenticationToken authenticationToken =
                                            new UsernamePasswordAuthenticationToken(
                                                    userDetails, null, userDetails.getAuthorities()
                                            );
                                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                                    return authenticationToken;
                                })
                                .orElse(null);

            }
        } else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
