package com.altuhin.dineease.service;

import com.altuhin.dineease.entity.DineInfo;
import com.altuhin.dineease.repository.DineInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DineInfoService {
    private final DineInfoRepository dineInfoRepository;

    public List<DineInfo> getDineInfosByMemberAndDate(String memberId, LocalDateTime startDate, LocalDateTime endDate) {
        return dineInfoRepository.findAllByMemberIdAndDateRange(memberId, startDate, endDate);
    }
}
