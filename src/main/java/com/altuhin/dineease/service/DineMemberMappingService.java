package com.altuhin.dineease.service;

import com.altuhin.dineease.repository.DineMemberMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DineMemberMappingService {
    private final DineMemberMappingRepository dineMemberMappingRepository;

}
