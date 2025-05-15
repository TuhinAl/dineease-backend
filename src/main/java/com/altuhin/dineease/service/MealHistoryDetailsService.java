package com.altuhin.dineease.service;

import com.altuhin.dineease.dto.MealHistoryDetailsDto;
import com.altuhin.dineease.entity.DineInfo;
import com.altuhin.dineease.entity.MealHistoryDetails;
import com.altuhin.dineease.entity.MemberInfo;
import com.altuhin.dineease.repository.DineInfoRepository;
import com.altuhin.dineease.repository.MealHistoryDetailsRepository;
import com.altuhin.dineease.repository.MemberInfoRepository;
import com.altuhin.dineease.util.exception.DineNotFoundException;
import com.altuhin.dineease.util.exception.MealHistoryDetailsNotFoundException;
import com.altuhin.dineease.util.exception.MemberNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.altuhin.dineease.util.TransformUtil.copyProp;

@Service
@RequiredArgsConstructor
public class MealHistoryDetailsService {
    private final MealHistoryDetailsRepository mealHistoryDetailsRepository;
    private final DineInfoRepository dineInfoRepository;
    private final MemberInfoRepository memberInfoRepository;

    public MealHistoryDetailsDto createMeals(MealHistoryDetailsDto mealHistoryDetailsDto) {

        MealHistoryDetails mealHistoryDetails = new MealHistoryDetails();
        DineInfo dineInfo = new DineInfo();
        MemberInfo memberInfo = new MemberInfo();

        if (!StringUtils.isEmpty(mealHistoryDetailsDto.getId())) {
            mealHistoryDetails = mealHistoryDetailsRepository.findById(mealHistoryDetailsDto.getId())
                    .orElseThrow(() -> new MealHistoryDetailsNotFoundException("Meal history details with this id not found"));
        }
        if (!StringUtils.isEmpty(mealHistoryDetailsDto.getDineInfoDto().getId())) {
            dineInfo = dineInfoRepository.findById(mealHistoryDetailsDto.getDineInfoDto().getId()).orElseThrow(
                    () -> new DineNotFoundException("Dine with this id not found")
            );
        }
        if (!StringUtils.isEmpty(mealHistoryDetailsDto.getMemberInfoDto().getId())) {
            memberInfo = memberInfoRepository.findById(mealHistoryDetailsDto.getMemberInfoDto().getId()).orElseThrow(
                    () -> new MemberNotFoundException("Member with this id not found")
            );
        }
        if (!Objects.nonNull(mealHistoryDetails)) {
            mealHistoryDetails.setMealDateTime(LocalDateTime.now());
            mealHistoryDetails.setMealCreateBy(memberInfo.getId());
        } else {
            mealHistoryDetails.setMealLastUpdateDateTime(LocalDateTime.now());
        }
        mealHistoryDetails.setDineInfo(dineInfo);
        mealHistoryDetails.setDineInfoId(dineInfo.getId());
        mealHistoryDetails.setMemberInfo(memberInfo);
        mealHistoryDetails.setMemberInfoId(memberInfo.getId());
        mealHistoryDetails.setIsApprovedByManager(Boolean.FALSE);
        mealHistoryDetails.setBreakfastMealNumber(mealHistoryDetailsDto.getBreakfastMealNumber());
        mealHistoryDetails.setLunchMealNumber(mealHistoryDetailsDto.getLunchMealNumber());
        mealHistoryDetails.setDinnerMealNumber(mealHistoryDetailsDto.getDinnerMealNumber());
        Double totalNumberOfMeal = (mealHistoryDetailsDto.getBreakfastMealNumber() +
                mealHistoryDetailsDto.getLunchMealNumber()
                + mealHistoryDetailsDto.getDinnerMealNumber());
        mealHistoryDetails.setTotalMealNumber(totalNumberOfMeal);
        MealHistoryDetails savedEntity = mealHistoryDetailsRepository.save(mealHistoryDetails);
        return copyProp(savedEntity, MealHistoryDetailsDto.class);
    }
    //search between date of a member
    //setApprovedByManager

}
