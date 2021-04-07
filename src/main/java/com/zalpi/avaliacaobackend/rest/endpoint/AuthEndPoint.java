package com.zalpi.avaliacaobackend.rest.endpoint;


import com.zalpi.avaliacaobackend.config.security.JwtTokenUtil;
import com.zalpi.avaliacaobackend.config.security.SecurityConfigInfoConstants;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.User;
import com.zalpi.avaliacaobackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.zalpi.avaliacaobackend.util.misc.ResponseUtil.createResponse;

//@Tag(name = "Authentication")
@RestController
@RequestMapping(path = SecurityConfigInfoConstants.publicUrl)
@RequiredArgsConstructor
public class AuthEndPoint {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final SecurityConfigInfoConstants config;

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody @Valid User request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
            User user = userService.findByUsernanme(userDetails.getUsername());
            user.setToken(jwtTokenUtil.generateAccessToken(user));
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, config.getTokenPefix()+ " " +user.getToken())
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<ResponseObject> register(@RequestBody @Valid User request) {
        return createResponse(userService.saveUser(request));
    }

}
