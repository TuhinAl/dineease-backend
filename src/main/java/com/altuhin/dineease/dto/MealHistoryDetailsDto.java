package com.altuhin.dineease.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MealHistoryDetailsDto {

    private String id;

    private DineInfoDto dineInfoDto;
    private String dineInfoId;
    private MemberInfoDto memberInfoDto;
    private String memberInfoId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime meal_date;
    private Double breakfastMealNumber;  // todo: need to set default
    private Double lunchMealNumber; // todo: need to set default
    private Double dinnerMealNumber; // todo: need to set default
    private Boolean isApprovedByManager; // todo: need to set default
    private Boolean mealCreateBy;

    private Boolean enabled;
    private String createdBy;
    private LocalDateTime createdDate;


    public MealHistoryDetailsDto(String id) {
        this.id = id;
    }

}
