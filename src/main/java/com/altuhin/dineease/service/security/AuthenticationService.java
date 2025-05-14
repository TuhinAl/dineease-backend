package com.altuhin.dineease.service.security;

import com.altuhin.dineease.dto.MemberInfoDto;
import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.entity.Role;
import com.altuhin.dineease.enums.MemberTypeEnum;
import com.altuhin.dineease.repository.MemberInfoRepository;
import com.altuhin.dineease.repository.RoleRepository;
import com.altuhin.dineease.service.security.dto.AuthenticationRequest;
import com.altuhin.dineease.service.security.dto.AuthenticationResponse;
import com.altuhin.dineease.service.security.dto.RegisterRequest;
import com.altuhin.dineease.util.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.altuhin.dineease.util.TransformUtil.copyProp;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MemberInfoRepository memberInfoRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public MemberInfoDto register(MemberInfoDto memberInfoDto) {
        MemberInfo memberInfo = memberInfoRepository.findMemberInfoByPhoneNumber(memberInfoDto.getPhoneNumber())
                .orElseThrow(
                        () -> new MemberNotFoundException("Member with this phone number is not found!")
                );

        // Create new member
        if (!Objects.isNull(memberInfo)) {
            memberInfo.setEmail(memberInfoDto.getEmail());
            memberInfo.setPhoneNumber(memberInfoDto.getPhoneNumber());
            memberInfo.setFullName(memberInfo.getFullName());
            memberInfo.setMemberTypeEnumKey(MemberTypeEnum.NORMAL_USER);
            memberInfo.setMemberTypeEnumValue(MemberTypeEnum.NORMAL_USER.getValue());
            memberInfo.setIsAdmin(Boolean.FALSE);
            memberInfo.setAddress(memberInfoDto.getAddress());
            memberInfo.setNumberOfFreeDineAssociated(0);
            memberInfo.setPassword(passwordEncoder.encode(memberInfoDto.getPassword()));
            // Assign default role
            Role userRole = roleRepository.findRoleByRoleTypeEnumKey(MemberTypeEnum.NORMAL_USER.name())
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            memberInfo.setRoles(roles);

            memberInfoRepository.save(memberInfo);
        }
        return copyProp(memberInfo, MemberInfoDto.class);
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
                .orElseThrow(() -> new MemberNotFoundException("Member with this phone number is not found!"));

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
