package com.altuhin.dineease.service;

import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.repository.MemberInfoRepository;
import com.altuhin.dineease.util.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilityService {
    private final MemberInfoRepository memberInfoRepository;


    public MemberInfo getMemberInfoByPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return null;
        }

        return memberInfoRepository.findMemberInfoByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with phone number: " + phoneNumber));
    }


    public MemberInfo getMemberInfoById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        return memberInfoRepository.findMemberInfoById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));
    }
}
