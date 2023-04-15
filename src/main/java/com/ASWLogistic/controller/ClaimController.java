package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.exception.throwable.BadRequestException;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.Stuffing;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.ClaimRepository;
import com.ASWLogistic.service.ClaimService;
import com.ASWLogistic.service.FileService;
import com.ASWLogistic.service.StuffingService;
import com.ASWLogistic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ASWLogistic.constant.GeneralConstants.*;
import static com.ASWLogistic.constant.OASConstants.*;

@Controller
@RequestMapping("/claim")
@RequiredArgsConstructor
@Slf4j
public class ClaimController {

    private final ClaimService claimService;
    private final StuffingService stuffingService;
    private final FileService fileService;

    private final UserService userService;

    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainClaim(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_CLAIM);
        model.addAttribute("pageTitle", TITLE_CLAIM);
        model.addAttribute("pageSubTitle", "List " + TITLE_CLAIM);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listClaims", claimService.findAll());
        model.addAttribute("listContainerName", stuffingService.findAllContainerCode());
        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        return FOLDER_NAME_CLAIM + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addClaim(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new ClaimDTO());
        }

        model.addAttribute("activeMenu", MENU_CLAIM);
        model.addAttribute("pageTitle", TITLE_CLAIM);
        model.addAttribute("listContainerCode", stuffingService.findAll());
        model.addAttribute("pageSubTitle", "create " + TITLE_CLAIM);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));

        return FOLDER_NAME_CLAIM + CREATE;
    }

    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addClaim(@Valid @ModelAttribute("holder") ClaimDTO claimDTO, Model model) throws IOException {
        log.info(GeneralConstants.startLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        claimService.addClaim(claimDTO);
        //============================================
        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        return mainClaim(model);
    }

    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, Model model) {
        log.info(GeneralConstants.startLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new ClaimDTO());
        }

        model.addAttribute("activeMenu", MENU_CLAIM);
        model.addAttribute("pageTitle", TITLE_CLAIM);
        model.addAttribute("listContainerCode", stuffingService.findAll());
        model.addAttribute("pageSubTitle", "Edit " + TITLE_CLAIM);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        Claim claim = claimService.getClaimsById(id);
        model.addAttribute("claims", claim);
        model.addAttribute("editedData", true);
        if (claim.getFiles() != null) {
            model.addAttribute("fileName", claim.getFiles());
        }
        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));

        return FOLDER_NAME_CLAIM + EDIT;
    }

    @PostMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, @Valid ClaimDTO claimDTO, @RequestParam("file") MultipartFile file, Model model) throws NotFoundException, IOException {
        log.info(GeneralConstants.startLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        Claim updatedClaim = claimService.updateClaimsById(id,claimDTO, file);

        model.addAttribute("activeMenu", MENU_CLAIM);
        model.addAttribute("pageTitle", TITLE_CLAIM);
        model.addAttribute("pageSubTitle", "List " + TITLE_CLAIM);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listClaims", claimService.findAll());
        // Add the updated claim to the model
        model.addAttribute("claims", updatedClaim);

        // Add the file name to the model
        model.addAttribute("fileName", updatedClaim.getFiles());
        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        return FOLDER_NAME_CLAIM + INDEX;
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String deleteClaim(@PathVariable("id") String id, @Valid ClaimDTO claimDTO, Model model) throws NotFoundException {
        log.info(GeneralConstants.startLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        claimService.deleteClaimById(id);

        model.addAttribute("activeMenu", MENU_CLAIM);
        model.addAttribute("pageTitle", TITLE_CLAIM);
        model.addAttribute("pageSubTitle", "List " + TITLE_CLAIM);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listClaims", claimService.findAll());

        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        return FOLDER_NAME_CLAIM + INDEX;
    }
}
