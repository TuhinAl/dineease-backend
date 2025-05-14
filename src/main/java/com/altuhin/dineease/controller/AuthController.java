package com.altuhin.dineease.controller;

import com.altuhin.dineease.service.security.AuthenticationService;
import com.altuhin.dineease.service.security.dto.AuthenticationRequest;
import com.altuhin.dineease.service.security.dto.AuthenticationResponse;
import com.altuhin.dineease.service.security.dto.RegisterRequest;
import com.altuhin.dineease.util.ApiResponse;
import com.altuhin.dineease.util.ApiResponseEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/secure")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final ApiResponseEntityFactory responseFactory;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
        return responseFactory.saveResponse(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login( @RequestBody AuthenticationRequest request) {
        return responseFactory.saveResponse(authenticationService.authenticate(request));
    }
}
