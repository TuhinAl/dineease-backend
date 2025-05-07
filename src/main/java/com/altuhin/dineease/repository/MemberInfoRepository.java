package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo, String> {

}
