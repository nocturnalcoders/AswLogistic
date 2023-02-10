package com.ASWLogistic.service;

import com.ASWLogistic.dto.ClaimDto;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;


    public Claim addClaim(ClaimDto claimDto) {
        Claim claim = new Claim();

        claim.setContainerCode(claimDto.getContainerCode());
        claim.setReceivingNumber(claimDto.getReceivingNumber());
        claim.setClaim(claimDto.getContainerCode());
        claim.setQty(claimDto.getQty());
        claim.setPrice(claimDto.getPrice());
        claim.setRate(claimDto.getRate());
        claim.setAmount(claimDto.getAmount());
        claim.setRemark(claimDto.getRemark());
        claim.setFiles(claimDto.getFiles());
        return claim;
    }



}
