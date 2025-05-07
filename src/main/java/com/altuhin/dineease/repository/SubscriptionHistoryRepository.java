package com.altuhin.dineease.repository;

import com.altuhin.dineease.entity.SubscriptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory, String> {


}
