package com.ASWLogistic.service;

import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.repository.ExtraFeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class ExtraFeeService {

    private final ExtraFeeRepository extraFeeRepository;
    public List<ExtraFee> findAll() {
        return extraFeeRepository.findAll();
    }

    public ExtraFee addExtraFee(ExtraFeeDTO extraFeeDTO) {
        ExtraFee extraFee = new ExtraFee();
        extraFee.setDescription(extraFeeDTO.getDescription());
        extraFee.setNominal(extraFeeDTO.getNominal());
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
