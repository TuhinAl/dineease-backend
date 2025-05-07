package com.altuhin.dineease.service;

import com.altuhin.dineease.repository.MealHistoryDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealHistoryDetailsService {
    private final MealHistoryDetailsRepository mealHistoryDetailsRepository;

}
