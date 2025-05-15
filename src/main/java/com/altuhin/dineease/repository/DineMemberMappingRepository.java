package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.DineMemberMapping;
import com.altuhin.dineease.enums.SubscriptionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DineMemberMappingRepository extends JpaRepository<DineMemberMapping, String> {

    @Query("SELECT DISTINCT dm FROM DineMemberMapping dm " +
            "JOIN FETCH dm.dineInfo d " +
            "JOIN FETCH dm.memberInfo m " +
            "WHERE m.phoneNumber = :phoneNumber " +
            "AND dm.memberInDineStatusEnumKey = :status")
    DineMemberMapping findByPhoneNumberAndCurrentStatus(
            @Param("phoneNumber") String phoneNumber,
            @Param("status") SubscriptionTypeEnum status
    );

}
