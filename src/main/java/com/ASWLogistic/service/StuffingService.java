package com.ASWLogistic.service;

import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.dto.PODDTO;
import com.ASWLogistic.dto.StuffingDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.POD;
import com.ASWLogistic.model.Stuffing;
import com.ASWLogistic.repository.StuffingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StuffingService {

    private final StuffingRepository stuffingRepository;


    public List<Stuffing> findAll() {
        return stuffingRepository.findAll();
    }


    public List<String> findAllContainerCode() {
        return stuffingRepository.findAllContainerCode();
    }

    public Optional<Stuffing> findById(String id) {
        return stuffingRepository.findById(id);
    }


    public Stuffing addStuffing(StuffingDTO stuffingDTO) {
        Stuffing stuffing = new Stuffing();
        stuffing.setDescription(extraFeeDTO.getDescription());
        stuffing.setNominal(extraFeeDTO.getNominal());
        extraFee.setNotes(extraFeeDTO.getNotes());
        return extraFeeRepository.save(extraFee);
    }

    public ExtraFee getExtrafeeById(String id) {
        return extraFeeRepository.getExtraFeeById(id);
    }

    public ExtraFee updateExtraFeesById(String id, ExtraFeeDTO extraFeeDTO) throws NotFoundException {
        ExtraFee extraFee = extraFeeRepository.getExtraFeeById(id);
        if (extraFee == null) {
            throw new NotFoundException(FieldConstants.ID, FieldConstants.ID);
        }
        extraFee.setNominal(extraFeeDTO.getNominal());
        extraFee.setDescription(extraFeeDTO.getDescription());
        extraFee.setNotes(extraFeeDTO.getNotes());
        return extraFeeRepository.save(extraFee);
    }
    public void deleteExtraFeeById(String id) {
        extraFeeRepository.deleteExtraFeeById(id);
    }



}
