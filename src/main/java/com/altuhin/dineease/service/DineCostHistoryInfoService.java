package com.altuhin.dineease.service;

import com.altuhin.dineease.repository.DineCostHistoryInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DineCostHistoryInfoService {
    private final DineCostHistoryInfoRepository dineCostHistoryInfoRepository;

}
