package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.DineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DineInfoRepository extends JpaRepository<DineInfo, String> {

    @Query("SELECT DISTINCT d FROM DineInfo d " +
            "JOIN d.dineMemberMappingList dm " +
            "JOIN dm.memberInfo m " +
            "LEFT JOIN FETCH d.mealHistoryDetailsList mh " +
            "LEFT JOIN FETCH d.subscriptionHistoryList sh " +
            "WHERE m.id = :memberId " +
            "AND d.createdDate BETWEEN :startDate AND :endDate")
    List<DineInfo> findAllByMemberIdAndDateRange(@Param("memberId") String memberId,
                                                 @Param("startDate") LocalDateTime startDate,
                                                 @Param("endDate") LocalDateTime endDate);

}
