package com.altuhin.dineease.service;

import com.altuhin.dineease.repository.DineInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DineInfoService {
    private final DineInfoRepository dineInfoRepository;

}
