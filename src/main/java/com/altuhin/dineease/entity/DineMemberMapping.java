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

@Entity
@Table(name = "dine_member_mapping")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DineMemberMapping extends Auditable {

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

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "member_dine_join_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberDineJoinDate;

    @Column(name = "member_in_dine_status_enum_key")
    @Enumerated(EnumType.STRING)
    private SubscriptionTypeEnum memberInDineStatusEnumKey;

    @Column(name = "member_in_dine_status_enum_value")
    private String memberInDineStatusEnumValue;
}