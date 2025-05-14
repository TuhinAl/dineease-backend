package com.altuhin.dineease.service;

import com.altuhin.dineease.dto.MemberInfoDto;
import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.enums.MemberTypeEnum;
import com.altuhin.dineease.repository.MemberInfoRepository;
import com.altuhin.dineease.util.TransformUtil;
import com.altuhin.dineease.util.exception.MemberNotFoundException;
import com.altuhin.dineease.util.exception.OTPMissMatchException;
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

    //create member
    public MemberInfoDto registerMemberInfo(MemberInfoDto memberInfoDto) {
        MemberInfoDto registeredMemberTto = getMemberInfoByPhoneNumber(memberInfoDto.getPhoneNumber());
        if (!Objects.nonNull(registeredMemberTto)) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setEmail(memberInfoDto.getEmail());
            memberInfo.setPhoneNumber(memberInfoDto.getPhoneNumber());
            memberInfo.setFullName(memberInfo.getFullName());
            memberInfo.setMemberTypeEnumKey(MemberTypeEnum.NORMAL_USER);
            memberInfo.setMemberTypeEnumValue(MemberTypeEnum.NORMAL_USER.getValue());
            memberInfo.setIsAdmin(Boolean.FALSE);
            memberInfo.setAddress(memberInfoDto.getAddress());
            memberInfo.setNumberOfFreeDineAssociated(0);
            memberInfoRepository.save(memberInfo);
        }
        return registeredMemberTto;
    }
    //create member
    public MemberInfoDto verifyPhone(MemberInfoDto memberInfoDto) {
        MemberInfo memberInfo = memberInfoRepository.findMemberInfoByPhoneNumber(memberInfoDto.getPhoneNumber()).get();
        if (Objects.isNull(memberInfo)) {
            MemberInfo memberInfoObj = new MemberInfo();
            memberInfoObj.setPhoneNumber(memberInfoDto.getPhoneNumber());
            //todo: OTP Sent
            memberInfoObj.setOtp("000000");
            MemberInfo persistedMember = memberInfoRepository.save(memberInfoObj);
            memberInfoDto.setId(persistedMember.getId());
            memberInfoDto.setOtp(persistedMember.getOtp());
        }
        return memberInfoDto;
    }

    //create member
    public MemberInfoDto verifyOTP(MemberInfoDto memberInfoDto) {
        MemberInfo memberInfo = memberInfoRepository.findMemberInfoByPhoneNumber(memberInfoDto.getPhoneNumber())
                .orElseThrow(
                        () -> new MemberNotFoundException("Member with this IP is not found"));

        if (!Objects.isNull(memberInfo)) {
            if (memberInfoDto.getOtp().equals(memberInfo.getOtp())) {
                memberInfo.setIsPhoneVerified(Boolean.TRUE);
             } else {
                throw new OTPMissMatchException("Incorrect OTP!!");
            }
        }
        return memberInfoDto;
    }

}
