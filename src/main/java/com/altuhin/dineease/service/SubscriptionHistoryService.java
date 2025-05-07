package com.altuhin.dineease.service;

import com.altuhin.dineease.repository.SubscriptionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionHistoryService {
    private final SubscriptionHistoryRepository subscriptionHistoryRepository;

}
