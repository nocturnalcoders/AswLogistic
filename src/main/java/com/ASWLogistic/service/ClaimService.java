package com.ASWLogistic.service;

import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.repository.ClaimRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;

    public Claim addClaim(ClaimDTO claimDTO) throws IOException {
        Claim claim = new Claim();
        claim.setContainerCode(claimDTO.getContainerCode());
        claim.setReceivingNumber(claimDTO.getReceivingNumber());
        claim.setClaim(claimDTO.getClaim());
        claim.setQty(claimDTO.getQty());
        claim.setPrice(claimDTO.getPrice());
        claim.setRate(claimDTO.getRate());
        claim.setAmount(claimDTO.getAmount());
        claim.setRemark(claimDTO.getRemark());

        // save file to local directory
        MultipartFile file = claimDTO.getFiles();
        if (file != null && !file.isEmpty()) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            String directoryPath = "./uploads/"; // change this to the directory you want to save files in
            Path directory = Paths.get(directoryPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            Path filePath = directory.resolve(filename);
            try {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                claim.setFiles(filename);
            } catch (IOException e) {
                // handle exception
                System.err.println("Error saving file: " + e.getMessage());
                return null;
            }
        } else {
            claim.setFiles(null);
        }
        return claimRepository.save(claim);
    }


    public List<Claim> findAll() {
        return claimRepository.findAll();
    }

    public Claim getClaimsById(String id) {
        return claimRepository.getClaimsById(id);
    }

    public void deleteClaimById(String id) {
        claimRepository.deleteClaimById(id);
    }

    public Claim updateClaimsById(String id, ClaimDTO claimDTO, MultipartFile file) throws NotFoundException, IOException {
        Claim claim = claimRepository.getClaimsById(id);
        if (claim == null) {
            throw new NotFoundException(FieldConstants.ID, FieldConstants.ID);
        }
        claim.setContainerCode(claimDTO.getContainerCode());
        claim.setReceivingNumber(claimDTO.getReceivingNumber());
        claim.setClaim(claimDTO.getClaim());
        claim.setQty(claimDTO.getQty());
        claim.setPrice(claimDTO.getPrice());
        claim.setRate(claimDTO.getRate());
        claim.setAmount(claimDTO.getAmount());
        claim.setRemark(claimDTO.getRemark());
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = FilenameUtils.getExtension(fileName);
            String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
            Path uploadDir = Paths.get("uploads");
            Path filePath = uploadDir.resolve(newFileName).normalize();
            Files.createDirectories(uploadDir);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            claim.setFiles(newFileName);
        }
        return claimRepository.save(claim);
    }



    public List<Claim> getClaim() {
        List<Claim> claimList = new ArrayList<>();
        for (Claim claim : claimList) {
            claim.getId();
            claim.getContainerCode();
            claim.getReceivingNumber();
            claim.getClaim();
            claim.getQty();
            claim.getPrice();
            claim.getRate();
            claim.getAmount();
            claim.getRemark();
            claim.getFiles();
        }
        return claimList;
    }


}
