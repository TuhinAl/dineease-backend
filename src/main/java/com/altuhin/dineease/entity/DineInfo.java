package com.altuhin.dineease.entity;


import com.altuhin.dineease.enums.SubscriptionTypeEnum;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dine_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DineInfo extends Auditable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "dine_name")
    private String dineName;

    @Column(name = "total_member", nullable = false)
    private Integer totalMember;

    @Column(name = "current_admin_id", nullable = false)
    private String currentAdminId;

    @Column(name = "admin_phone_number", unique = true, nullable = false)
    private String adminPhoneNumber;

    @Column(name = "is_subscription_enabled")
    private Boolean isSubscriptionEnabled;

    @Column(name = "is_in_free_trial")
    private Boolean isInFreeTrial;

    @Column(name = "trial_start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime trialStartDate;

    @Column(name = "trial_end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime trialEndDate;

    @Column(name = "last_subscription_end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastSubscriptionEndDate;

    @Column(name = "subscription_type_enum_key")
    @Enumerated(EnumType.STRING)
    private SubscriptionTypeEnum subscriptionTypeEnumKey;

    @Column(name = "subscription_type_enum_value")
    private String subscriptionTypeEnumKeyValue;

    @OneToMany(mappedBy = "dineInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DineMemberMapping> dineMemberMappingList;

    @OneToMany(mappedBy = "dineInfo", fetch = FetchType.LAZY)
    private List<MealHistoryDetails> mealHistoryDetailsList;

    @OneToMany(mappedBy = "dineInfo", fetch = FetchType.LAZY)
    private List<SubscriptionHistory> subscriptionHistoryList;

    public DineInfo(String id) {
        this.id = id;
    }

}
