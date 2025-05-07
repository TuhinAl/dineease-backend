package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.DineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DineInfoRepository extends JpaRepository<DineInfo, String> {


}
