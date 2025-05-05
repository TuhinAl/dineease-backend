package com.altuhin.dineease.entity;


import com.altuhin.dineease.enums.MemberTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MemberInfo extends Auditable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "address")
    private String address;

    @Column(name = "member_type_enum_key")
    @Enumerated(EnumType.STRING)
    private MemberTypeEnum memberTypeEnumKey;

    @Column(name = "member_type_enum_value")
    private String memberTypeEnumValue;

    @Column(name = "number_of_free_dine_associated")
    private Integer numberOfFreeDineAssociated;

    @OneToMany(mappedBy = "dineInfo", fetch = FetchType.LAZY)
    private List<DineInfo> dineInfoList;

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

    public MemberInfo(String id) {
        this.id = id;
    }

}
