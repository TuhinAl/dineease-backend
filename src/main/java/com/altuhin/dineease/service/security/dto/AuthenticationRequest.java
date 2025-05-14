package com.altuhin.dineease.service.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String phoneNumber;
    private String password;
}
