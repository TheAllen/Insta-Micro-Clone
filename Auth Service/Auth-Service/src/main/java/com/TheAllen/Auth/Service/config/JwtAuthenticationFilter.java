package com.TheAllen.Auth.Service.config;

import com.TheAllen.Auth.Service.service.JwtProvider;
import com.TheAllen.Auth.Service.service.UserService;
import com.ctc.wstx.util.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    private String serviceUsername;

    public JwtAuthenticationFilter(String serviceUsername) {
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

            UsernamePasswordAuthenticationToken
        } else { //Token validated


        }

    }
}
