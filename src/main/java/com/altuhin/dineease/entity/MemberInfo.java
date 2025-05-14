package com.altuhin.dineease.entity;


import com.altuhin.dineease.enums.MemberTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
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

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "otp")
    private String otp;

    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified;

    @Column(name = "is_admin",  nullable = false)
    private Boolean isAdmin = Boolean.FALSE;

    @Column(name = "address")
    private String address;

    @Column(name = "member_type_enum_key")
    @Enumerated(EnumType.STRING)
    private MemberTypeEnum memberTypeEnumKey;

    @Column(name = "member_type_enum_value")
    private String memberTypeEnumValue;

    @Column(name = "number_of_free_dine_associated")
    private Integer numberOfFreeDineAssociated;

    @OneToMany(mappedBy = "memberInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DineMemberMapping> dineMemberMappingList;

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
