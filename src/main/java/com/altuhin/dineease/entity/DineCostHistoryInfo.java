package com.altuhin.dineease.entity;


import com.altuhin.dineease.util.MealCostJsonData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal_cost_history_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DineCostHistoryInfo extends Auditable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dine_id", nullable = false)
    private DineInfo dineInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberInfo memberInfo;

    @Column(name = "total_cost")
    private Double totalCost; // todo: need to set default

    @Column(name = "last_subscription_end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastSubscriptionEndDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "meal_cost_json_data", columnDefinition = "jsonb")
    private MealCostJsonData mealCostJsonData;

    public DineCostHistoryInfo(String id) {
        this.id = id;
    }

}
