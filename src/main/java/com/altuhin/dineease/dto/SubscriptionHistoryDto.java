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
public class SubscriptionHistoryDto {

    private String id;
    private DineInfoDto dineInfoDto;
    private String dineInfoId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionEndDate;
    private String transactionId;


    private Boolean enabled;
    private String createdBy;
    private LocalDateTime createdDate;

    public SubscriptionHistoryDto(String id) {
        this.id = id;
    }

}
