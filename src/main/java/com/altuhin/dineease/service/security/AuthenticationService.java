package com.altuhin.dineease.service.security;

import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.entity.Role;
import com.altuhin.dineease.enums.MemberTypeEnum;
import com.altuhin.dineease.repository.MemberInfoRepository;
import com.altuhin.dineease.repository.RoleRepository;
import com.altuhin.dineease.service.security.dto.AuthenticationRequest;
import com.altuhin.dineease.service.security.dto.AuthenticationResponse;
import com.altuhin.dineease.service.security.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MemberInfoRepository memberInfoRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse register(RegisterRequest request) {
        // Check if user already exists
        if (memberInfoRepository.findMemberInfoByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("User already exists with this phone number");
        }

        // Create new member
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setFullName(request.getFullName());
        memberInfo.setEmail(request.getEmail());
        memberInfo.setPhoneNumber(request.getPhoneNumber());
        memberInfo.setPassword(passwordEncoder.encode(request.getPassword()));
        memberInfo.setAddress(request.getAddress());
        memberInfo.setIsAdmin(false);
        memberInfo.setIsPhoneVerified(false);
        memberInfo.setMemberTypeEnumKey(MemberTypeEnum.NORMAL_USER);
        memberInfo.setMemberTypeEnumValue(MemberTypeEnum.NORMAL_USER.getValue());
        memberInfo.setNumberOfFreeDineAssociated(0);

        // Assign default role
        Role userRole = roleRepository.findRoleByRoleTypeEnumKey(MemberTypeEnum.NORMAL_USER.name())
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        memberInfo.setRoles(roles);

        // Save member
        memberInfoRepository.save(memberInfo);

        // Generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(memberInfo.getPhoneNumber());
        String jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .memberName(memberInfo.getFullName())
                .phoneNumber(memberInfo.getPhoneNumber())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );

        // Get user details
        MemberInfo memberInfo = memberInfoRepository.findMemberInfoByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(memberInfo.getPhoneNumber());
        String jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .memberName(memberInfo.getFullName())
                .phoneNumber(memberInfo.getPhoneNumber())
                .build();
    }
}
