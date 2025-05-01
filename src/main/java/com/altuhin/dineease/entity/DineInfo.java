package com.altuhin.dineease.entity;


import com.altuhin.dineease.enums.MemberTypeEnum;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Column(name = "subscription_type_enumvalue")
    private String subscriptionTypeEnumKeyValue;


    /*@OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<Leave> leaveList;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<LeaveHistory> leaveHistoryList;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<EmployeeAccountTransaction> employeeAccountTransactionList;

    @OneToMany(mappedBy = "employeeInfo", fetch = FetchType.LAZY)
    private List<EmployeeAttendance> employeeAttendanceList;*/

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_bank_info_id")
    private EmployeeBankInfo employeeBankInfo;*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "employee_info_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"
                    , referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public DineInfo(String id) {
        this.id = id;
    }

}
