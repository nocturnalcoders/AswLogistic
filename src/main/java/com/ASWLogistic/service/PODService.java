package com.ASWLogistic.service;

import com.ASWLogistic.dto.PODDTO;
import com.ASWLogistic.model.POD;
import com.ASWLogistic.repository.PODRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PODService {

    private final PODRepository podRepository;


    public POD addPOD(PODDTO podDTO) {
        POD pod = new POD();
        pod.setContainerCode(podDTO.getContainerCode());
        pod.setReceivingNumber(podDTO.getReceivingNumber());
        pod.setDeliveryType(podDTO.getDeliveryType());
        pod.setDeliveryDate(podDTO.getDeliveryDate());
        pod.setReceivingDate(podDTO.getReceivingDate());
        pod.setDriverId(podDTO.getDriverId());
        pod.setStatus(podDTO.getStatus());
        pod.setFiles(podDTO.getFiles());
        pod.setExtrafeeId(podDTO.getExtrafeeId());
        pod.setLocalReceipt(podDTO.getLocalReceipt());
        return podRepository.save(pod);
    }

    public List<POD> findAll() {
        return podRepository.findAll();
    }


}
