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


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DineMemberMappingDto {

    private String id;
    private DineInfoDto dineInfoDto;
    private String employeeInfoId;
    private MemberInfoDto memberInfoDto;
    private String memberInfoId;
    private Boolean isAdmin;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberDineJoinDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime mealLastUpdateDateTime;

    @Enumerated(EnumType.STRING)
    private SubscriptionTypeEnum memberInDineStatusEnumKey;
    private String memberInDineStatusEnumValue;

    private Boolean enabled;
    private String createdBy;
    private LocalDateTime createdDate;
}