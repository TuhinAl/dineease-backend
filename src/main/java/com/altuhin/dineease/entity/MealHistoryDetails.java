package com.altuhin.dineease.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal_history_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MealHistoryDetails extends Auditable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dine_info_id", nullable = false)
    private DineInfo dineInfo;

    @Column(name = "dine_info_id", insertable = false, updatable = false)
    private String dineInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_info_id", nullable = false)
    private MemberInfo memberInfo;

    @Column(name = "member_info_id", insertable = false, updatable = false)
    private String memberInfoId;

    @Column(name = "meal_date_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime mealDateTime;

    @Column(name = "meal_last_update_date_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime mealLastUpdateDateTime;

    @Column(name = "breakfast_meal_number")
    private Double breakfastMealNumber; // todo: need to set default

    @Column(name = "lunch_meal_number")
    private Double lunchMealNumber; // todo: need to set default

    @Column(name = "dinner_meal_number")
    private Double dinnerMealNumber; // todo: need to set default

    @Column(name = "total_meal_number")
    private Double totalMealNumber; // todo: need to set default

    @Column(name = "is_approved_by_manager") // todo: need to set default
    private Boolean isApprovedByManager;

    @Column(name = "meal_create_by") // todo: need to set default
    private String mealCreateBy;


    public MealHistoryDetails(String id) {
        this.id = id;
    }

}
