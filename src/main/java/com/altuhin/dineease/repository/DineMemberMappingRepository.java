package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.DineMemberMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DineMemberMappingRepository extends JpaRepository<DineMemberMapping, String> {


}
