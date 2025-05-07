package com.altuhin.dineease.service;

import com.altuhin.dineease.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;

}
