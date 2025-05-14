package com.altuhin.dineease.service.security.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String memberName;
    private String phoneNumber;
}
