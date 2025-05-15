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

    private Double breakfastMealNumber;
    private Double lunchMealNumber;
    private Double dinnerMealNumber;
    private Double totalMealNumber;

    private Boolean isApprovedByManager;
    private String mealCreateBy;

    private Boolean enabled;
    private String createdBy;
    private LocalDateTime createdDate;


    public MealHistoryDetailsDto(String id) {
        this.id = id;
    }

}
