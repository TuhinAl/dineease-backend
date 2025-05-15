package com.altuhin.dineease.dto;


import com.altuhin.dineease.enums.SubscriptionTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DineInfoDto {

    private String id;
    private String dineName;
    private Integer totalMember;
    private String currentAdminId;
    private String adminPhoneNumber;
    private Boolean isSubscriptionEnabled;
    private Boolean isInFreeTrial;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime trialStartDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime trialEndDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastSubscriptionEndDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionTypeEnum subscriptionTypeEnumKey;
    private String subscriptionTypeEnumValue;

    private Boolean enabled;
    private String createdBy;
    private LocalDateTime createdDate;

    private List<DineMemberMappingDto> dineMemberMappingDtoList;
    private List<MealHistoryDetailsDto> mealHistoryDetailsDtoList;
    private List<SubscriptionHistoryDto> subscriptionHistoryDtoList;

    public DineInfoDto(String id) {
        this.id = id;
    }

}
