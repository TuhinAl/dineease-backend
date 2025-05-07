package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.MealHistoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealHistoryDetailsRepository extends JpaRepository<MealHistoryDetails, String> {

}
