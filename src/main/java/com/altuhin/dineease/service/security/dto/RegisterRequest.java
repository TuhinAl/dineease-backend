package com.altuhin.dineease.service.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
}
