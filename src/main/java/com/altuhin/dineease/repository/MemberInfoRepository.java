package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo, String> {
    Optional<MemberInfo> findMemberInfoByPhoneNumber(String phoneNumber);
    Optional<MemberInfo> findMemberInfoById(String id);

}
