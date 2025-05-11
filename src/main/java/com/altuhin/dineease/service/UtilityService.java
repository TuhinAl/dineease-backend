package com.altuhin.dineease.service;

import com.altuhin.dineease.dto.MemberInfoDto;
import com.altuhin.dineease.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;


    public MemberInfoDto getMemberInfoByPhoneNumber(String phoneNumber) {
        if (StringUtils.isNoneBlank(phoneNumber)) {

        }
    }
}
