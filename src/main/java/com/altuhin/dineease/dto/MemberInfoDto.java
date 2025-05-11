package com.altuhin.dineease.dto;


import com.altuhin.dineease.entity.Role;
import com.altuhin.dineease.enums.MemberTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MemberInfoDto {

    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean isPhoneVerified;
    private Boolean isAdmin;
    private String address;
    @Enumerated(EnumType.STRING)
    private MemberTypeEnum memberTypeEnumKey;
    private String memberTypeEnumValue;
    private Integer numberOfFreeDineAssociated;
    private List<DineInfoDto> dineInfoDtoList;
    private List<DineMemberMappingDto> dineMemberMappingDtoList;

    private Set<Role> roles = new HashSet<>();

    private Boolean enabled;
    private Boolean isMemberRegistered;
    private String createdBy;
    private LocalDateTime createdDate;

    public MemberInfoDto(String id) {
        this.id = id;
    }

}
