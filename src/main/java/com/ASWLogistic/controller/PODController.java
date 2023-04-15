package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.constant.OASConstants;
import com.ASWLogistic.dto.PODDTO;
import com.ASWLogistic.dto.PODDetailsDTO;
import com.ASWLogistic.dto.PODWrapperDTO;
import com.ASWLogistic.dto.UserRegistrationDto;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.PODRepository;
import com.ASWLogistic.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.ASWLogistic.constant.GeneralConstants.*;
import static com.ASWLogistic.constant.OASConstants.*;

@Controller
@RequestMapping("/pod")
@RequiredArgsConstructor
@Slf4j
public class PODController {

    private final PODService podService;
    private final PODRepository podRepository;

    private final ExtraFeeService extraFeeService;
    private final StuffingService stuffingService;
    private final DriverService driverService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(PODController.class);


    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainPod(Model model) {
        log.info(GeneralConstants.startLog(OASConstants.TITLE_POD, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_POD);
        model.addAttribute("pageTitle", TITLE_POD);
        model.addAttribute("pageSubTitle", "List " + TITLE_POD);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listPODs", podService.findAll());
        log.info(GeneralConstants.finishLog(OASConstants.TITLE_POD, this.getClass().getSimpleName()));
        return FOLDER_NAME_POD + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addPOD(Model model) {
        log.info(GeneralConstants.startLog(TITLE_POD, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.podWrapperDTO",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("podWrapperDTO")) {
            PODWrapperDTO podWrapperDTO = new PODWrapperDTO();
            podWrapperDTO.setHolder(new PODDTO());
            podWrapperDTO.setHolderDetail(new PODDetailsDTO());
            model.addAttribute("podWrapperDTO", podWrapperDTO);
        }

        model.addAttribute("activeMenu", MENU_POD);
        model.addAttribute("pageTitle", TITLE_POD);
        model.addAttribute("listContainerCode", stuffingService.findAll());
        model.addAttribute("listExtraFee", extraFeeService.findAll());
        model.addAttribute("listDriver", driverService.findAll());
        model.addAttribute("pageSubTitle", "Create " + OASConstants.TITLE_POD);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        log.info(GeneralConstants.finishLog(TITLE_POD, this.getClass().getSimpleName()));
        return FOLDER_NAME_POD + CREATE;
    }

    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addPOD(@Valid @ModelAttribute("holder") PODDTO podDTO) {
        log.info(GeneralConstants.startLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        podService.addPOD(podDTO);
        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        return FOLDER_NAME_POD + CREATE;
    }
}
