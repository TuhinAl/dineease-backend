package com.altuhin.dineease.dto;


import com.altuhin.dineease.util.MealCostJsonData;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DineCostHistoryInfoDto {

    private String id;
    private DineInfoDto dineInfoDto;
    private MemberInfoDto memberInfoDto;
    private Double totalCost;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastSubscriptionEndDate;

    @JdbcTypeCode(SqlTypes.JSON)
    private MealCostJsonData mealCostJsonData;

    private Boolean enabled;
    private String createdBy;
    private LocalDateTime createdDate;

    public DineCostHistoryInfoDto(String id) {
        this.id = id;
    }

}
