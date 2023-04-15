package com.ASWLogistic.service;

import com.ASWLogistic.dto.PODDTO;
import com.ASWLogistic.model.Driver;
import com.ASWLogistic.model.POD;
import com.ASWLogistic.model.Stuffing;
import com.ASWLogistic.repository.DriverRepository;
import com.ASWLogistic.repository.StuffingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;


    public List<Driver> findAll() {
        return driverRepository.findAll();
    }


}
