package com.altuhin.dineease.service;

import com.altuhin.dineease.dto.MemberInfoDto;
import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.repository.MemberInfoRepository;
import com.altuhin.dineease.util.TransformUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;
    private final UtilityService utilityService;


    public MemberInfoDto getMemberInfoByPhoneNumber(String phoneNumber) {
        MemberInfo memberInfo = utilityService.getMemberInfoByPhoneNumber(phoneNumber);
        MemberInfoDto memberInfoDto = TransformUtil.copyProp(memberInfo, MemberInfoDto.class);
        if (ObjectUtils.isEmpty(memberInfo)) {
            memberInfoDto.setIsMemberRegistered(Boolean.FALSE);
        }
        return memberInfoDto;
    }

    public MemberInfoDto registerMemberInfo(MemberInfoDto memberInfoDto) {
        MemberInfoDto registeredMemberTto = getMemberInfoByPhoneNumber(memberInfoDto.getPhoneNumber());
        if (Objects.nonNull(registeredMemberTto)) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setEmail(memberInfoDto.getEmail());
        }
        return registeredMemberTto;
    }
}
