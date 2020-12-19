package com.TheAllen.Auth.Service.controller;

import com.TheAllen.Auth.Service.exceptions.ResourceNotFoundException;
import com.TheAllen.Auth.Service.models.*;
import com.TheAllen.Auth.Service.payload.*;
import com.TheAllen.Auth.Service.service.JwtProvider;
import com.TheAllen.Auth.Service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequestMapping(value = "/api")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Authentication Server";
    }

    //Sign in
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> userSignIn(@Valid @RequestBody LoginRequest loginRequest) {

        //Authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    //Register
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        logger.info("Registering user {}", signUpRequest.getUsername());

        //Create user
        User user = new User();

        try {

        } catch(Exception e) {

        }

        //Get Location
        URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/users/{username}")
                        .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true, "User successfully registered"));

    }

    //Edit picture
    @RequestMapping(value = "/users/me/picture", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProfilePicture(
            @RequestBody String profilePicture,
            @AuthenticationPrincipal InstaUserDetails userDetails) {

        userService.updateProfilePicture(profilePicture, userDetails.getId());

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true, "Profile picture successfully updated"));
    }

    @RequestMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUser(@PathVariable("username") String username) {

        logger.info("retrieving user {}", username);

        return userService
                .findByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User {} not found", username)));
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUsers() {

        logger.info("retrieving all users");

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/users/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('user')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal InstaUserDetails userDetails) {

        UserSummary userSummary = new UserSummary();
        userSummary.setId(userDetails.getId());
        userSummary.setUsername(userDetails.getUsername());
        userSummary.setName(userDetails.getProfile().getUsername());
        userSummary.setProfilePicture(userDetails.getProfile().getProfilePictureUrl());

        return ResponseEntity.ok(userSummary);
    }

}
