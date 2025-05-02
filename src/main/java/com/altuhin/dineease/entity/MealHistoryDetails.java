package com.altuhin.dineease.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "dine_id", nullable = false)
    private DineInfo dineInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberInfo memberInfo;

    @Column(name = "meal_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime meal_date;

//    @Type(type = "jsonb")
    @Column(name = "meal_config", columnDefinition = "jsonb")
    private String mealConfigJson;



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

    public MealHistoryDetails(String id) {
        this.id = id;
    }

}
