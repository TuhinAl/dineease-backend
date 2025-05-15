package com.altuhin.dineease.service;

import com.altuhin.dineease.dto.DineInfoDto;
import com.altuhin.dineease.dto.DineMemberMappingDto;
import com.altuhin.dineease.dto.MemberInfoDto;
import com.altuhin.dineease.entity.DineInfo;
import com.altuhin.dineease.entity.DineMemberMapping;
import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.enums.MemberTypeEnum;
import com.altuhin.dineease.enums.SubscriptionTypeEnum;
import com.altuhin.dineease.repository.DineInfoRepository;
import com.altuhin.dineease.repository.DineMemberMappingRepository;
import com.altuhin.dineease.repository.MemberInfoRepository;
import com.altuhin.dineease.util.exception.DineNotFoundException;
import com.altuhin.dineease.util.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.altuhin.dineease.util.TransformUtil.copyProp;

@Service
@RequiredArgsConstructor
public class DineMemberMappingService {
    private final DineMemberMappingRepository dineMemberMappingRepository;
    private final DineInfoRepository dineInfoRepository;
    private final MemberInfoRepository memberInfoRepository;

    public DineMemberMappingDto createDine(DineMemberMappingDto dineMemberMappingDto) {
        DineInfoDto dineInfoDto;
        MemberInfoDto memberInfoDto;
        DineMemberMapping currentDineExist = dineMemberMappingRepository
                .findByPhoneNumberAndCurrentStatus(dineMemberMappingDto.getMemberInfoDto().getPhoneNumber(),
                        SubscriptionTypeEnum.LEAVED);
        if (Objects.nonNull(currentDineExist)) {
            throw new RuntimeException("Already exist with this phone number");
        } else {

            if (!StringUtils.isEmpty(dineMemberMappingDto.getMemberInfoDto().getPhoneNumber())) {
                MemberInfo memberInfo = memberInfoRepository.findMemberInfoByPhoneNumber(dineMemberMappingDto.getMemberInfoDto().getPhoneNumber())
                        .orElseThrow(() -> new MemberNotFoundException("Member not found with this phone number"));
                DineInfo dineInfo = new DineInfo();
                dineInfo.setDineName(dineMemberMappingDto.getDineInfoDto().getDineName());
                dineInfo.setTotalMember(1);
                dineInfo.setCurrentAdminId(memberInfo.getId());
                dineInfo.setAdminPhoneNumber(memberInfo.getPhoneNumber());
                dineInfo.setIsSubscriptionEnabled(Boolean.FALSE);
                dineInfo.setIsInFreeTrial(Boolean.TRUE);
                dineInfo.setTrialStartDate(LocalDateTime.now());
                int dayOfMonth = LocalDateTime.now().getDayOfMonth();
                int monthNumber = LocalDateTime.now().getMonthValue();
                if (dayOfMonth > 7) {
                    LocalDateTime trialEndDateTime = getLastDayOfNextMonth(monthNumber);
                    dineInfo.setTrialEndDate(trialEndDateTime);
                } else {
                    dineInfo.setTrialEndDate(LocalDateTime.now().plusDays(30));
                }
                dineInfo.setLastSubscriptionEndDate(null);
                dineInfo.setSubscriptionTypeEnumKey(SubscriptionTypeEnum.FREE_TRIAL);
                dineInfo.setSubscriptionTypeEnumValue(SubscriptionTypeEnum.FREE_TRIAL.getValue());
                DineInfo persistedDine = dineInfoRepository.save(dineInfo);
                dineInfoDto = copyProp(dineInfo, DineInfoDto.class);

                memberInfo.setIsAdmin(Boolean.TRUE);
                memberInfo.setMemberTypeEnumKey(MemberTypeEnum.ADMIN);
                memberInfo.setMemberTypeEnumValue(MemberTypeEnum.ADMIN.getValue());
                memberInfo.setNumberOfFreeDineAssociated(1);
                MemberInfo persistedMemberInfo = memberInfoRepository.save(memberInfo);
                memberInfoDto = copyProp(memberInfo, MemberInfoDto.class);

                DineMemberMapping dineMemberMapping = new DineMemberMapping();
                dineMemberMapping.setDineInfo(persistedDine);
                dineMemberMapping.setDineInfoId(persistedDine.getId());
                dineMemberMapping.setMemberInfo(persistedMemberInfo);
                dineMemberMapping.setMemberInfoId(persistedMemberInfo.getId());
                dineMemberMapping.setIsAdmin(Boolean.TRUE);
                dineMemberMapping.setMemberDineJoinDate(LocalDateTime.now());
                dineMemberMapping.setMemberInDineStatusEnumKey(SubscriptionTypeEnum.CURRENTLY_ACTIVE);
                dineMemberMapping.setMemberInDineStatusEnumValue(SubscriptionTypeEnum.CURRENTLY_ACTIVE.getValue());
                DineMemberMapping persistedDineMemberMapping = dineMemberMappingRepository.save(dineMemberMapping);
                dineMemberMappingDto = copyProp(persistedDineMemberMapping, DineMemberMappingDto.class);
                dineMemberMappingDto.setDineInfoDto(dineInfoDto);
                dineMemberMappingDto.setMemberInfoDto(memberInfoDto);
            }
        }
        return dineMemberMappingDto;
    }

    public DineMemberMappingDto addMemberInDine(DineMemberMappingDto dineMemberMappingDto) {

        DineMemberMapping memberCurrentlyIsNotBelongsToAnyDine = dineMemberMappingRepository
                .findByPhoneNumberAndCurrentStatus(dineMemberMappingDto.getMemberInfoDto().getPhoneNumber(),
                        SubscriptionTypeEnum.LEAVED);

        if (!Objects.isNull(memberCurrentlyIsNotBelongsToAnyDine)) {
            DineInfo dineInfo = new DineInfo();
            MemberInfo memberInfo = new MemberInfo();
            if (!StringUtils.isEmpty(dineMemberMappingDto.getDineInfoDto().getId())) {
                 dineInfo = dineInfoRepository.findById(dineMemberMappingDto.getDineInfoDto().getId()).orElseThrow(
                        () -> new DineNotFoundException("Dine with this id not found")
                );
            }
            if (!StringUtils.isEmpty(dineMemberMappingDto.getMemberInfoDto().getId())) {
                 memberInfo = memberInfoRepository.findById(dineMemberMappingDto.getMemberInfoDto().getId()).orElseThrow(
                        () -> new MemberNotFoundException("Member with this id not found")
                );
            }

            memberInfo.setNumberOfFreeDineAssociated(1);
            memberInfoRepository.save(memberInfo);
            DineMemberMapping dineMemberMapping = new DineMemberMapping();
            dineMemberMapping.setDineInfo(dineInfo);
            dineMemberMapping.setDineInfoId(dineInfo.getId());
            dineMemberMapping.setMemberInfo(memberInfo);
            dineMemberMapping.setMemberInfoId(memberInfo.getId());
            dineMemberMapping.setIsAdmin(Boolean.FALSE);
            dineMemberMapping.setMemberDineJoinDate(LocalDateTime.now());
            dineMemberMapping.setMemberInDineStatusEnumKey(SubscriptionTypeEnum.CURRENTLY_ACTIVE);
            dineMemberMapping.setMemberInDineStatusEnumValue(SubscriptionTypeEnum.CURRENTLY_ACTIVE.getValue());
            DineMemberMapping persistedDineMemberMapping = dineMemberMappingRepository.save(dineMemberMapping);
            dineMemberMappingDto.setId(persistedDineMemberMapping.getId());
        }

        return dineMemberMappingDto;
    }

    /*public DineMemberMappingDto leavedFromDine(DineMemberMappingDto dineMemberMappingDto) {

        //check any active with phone number: active status should be LEAVED

        //is this member already in another dine.
        DineMemberMapping memberCurrentlyIsNotBelongsToAnyDine = dineMemberMappingRepository
                .findByPhoneNumberAndCurrentStatus(dineMemberMappingDto.getMemberInfoDto().getPhoneNumber(),
                        SubscriptionTypeEnum.LEAVED);

        if(Objects.isNull(currentDineExist))
    }*/



    public static LocalDateTime getLastDayOfNextMonth(int monthNumber) {
        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Invalid month number! Must be between 1 and 12.");
        }
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), monthNumber, 1);
        LocalDate nextMonth = date.plusMonths(1);
        LocalDate lastDayOfNextMonth = nextMonth.withDayOfMonth(nextMonth.lengthOfMonth());
        return lastDayOfNextMonth.atTime(23, 59, 59);
    }

}
