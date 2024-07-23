package com.ian.reserviox.controller;

import com.ian.reserviox.dto.AuthenticationRequest;
import com.ian.reserviox.dto.SignupRequestDto;
import com.ian.reserviox.exception.InvalidCredentialsException;
import com.ian.reserviox.service.auth.AuthServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthServiceImpl authService;
    public static final String TOKEN_PREFIX = "Bearer ";

    public AuthenticationController(AuthServiceImpl authService) {
        this.authService = authService;
    }
    @PostMapping("/client/signup")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.isEmailAlreadyInUse(signupRequestDto.getEmail())){
            return ResponseEntity.badRequest().body("Email already in use");
        }
        return ResponseEntity.ok(authService.signupClient(signupRequestDto));
    }
    @PostMapping("/company/signup")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.isEmailAlreadyInUse(signupRequestDto.getEmail())){
            return ResponseEntity.badRequest().body("Email already in use");
        }
        return ResponseEntity.ok(authService.signupClient(signupRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            String jwt = authService.authenticateUser(authenticationRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", TOKEN_PREFIX + jwt);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
