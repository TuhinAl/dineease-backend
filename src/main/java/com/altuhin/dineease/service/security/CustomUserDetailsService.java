package com.altuhin.dineease.service.security;


import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.entity.Role;
import com.altuhin.dineease.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberInfoRepository memberInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        MemberInfo memberInfo = memberInfoRepository.findMemberInfoByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + phoneNumber));

        return new User(
                memberInfo.getPhoneNumber(),
                memberInfo.getPassword(),
                mapRolesToAuthorities(memberInfo.getRoles())
        );
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleTypeEnumKey()))
                .collect(Collectors.toList());
    }
}
