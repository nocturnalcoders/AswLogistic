package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.constant.OASConstants;
import com.ASWLogistic.dto.StuffingDTO;
import com.ASWLogistic.dto.UserRegistrationDto;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.StuffingRepository;
import com.ASWLogistic.service.StuffingService;
import com.ASWLogistic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.ASWLogistic.constant.GeneralConstants.INDEX;
import static com.ASWLogistic.constant.OASConstants.*;

@Controller
@RequestMapping("/stuffing")
@RequiredArgsConstructor
@Slf4j
public class StuffingController {

    private final StuffingService stuffingService;
    private final StuffingRepository stuffingRepository;

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(StuffingController.class);


    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainStuffing(Model model) {
        log.info(GeneralConstants.startLog(OASConstants.TITLE_STUFFING, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_STUFFING);
        model.addAttribute("pageTitle", TITLE_STUFFING);
        model.addAttribute("pageSubTitle", "List " + TITLE_STUFFING);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listStuffings", stuffingService.findAll());
        log.info(GeneralConstants.finishLog(OASConstants.TITLE_STUFFING, this.getClass().getSimpleName()));
        return FOLDER_NAME_STUFFING + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addStuffing(Model model) {
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new StuffingDTO());
        }

        model.addAttribute("activeMenu", MENU_STUFFING);
        model.addAttribute("pageTitle", OASConstants.TITLE_STUFFING);
        model.addAttribute("pageSubTitle", "Create " + OASConstants.TITLE_STUFFING);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        return FOLDER_NAME_STUFFING + "create";
    }

//    @PostMapping(path = "/add")
//    @PreAuthorize(("@securityService.hasPrivilege('CREATE_USERS')"))
//    public String addPOD(@Valid @ModelAttribute("holder") PODDTO podDto, BindingResult result) {
//        logger.info("/student/add");
//        POD existing = podRepository.findByContainerCode(podDto.getContainerCode());
//        if (existing != null){
//            result.rejectValue("container_code", "There is already container code inserted with that Container Code");
//        }
//        if (result.hasErrors()){
//            logger.info("error");
//            return "claim/add";
//        }
//
//        podService.addPOD(podDto);
//
//        return FOLDER_NAME_STUFFING + "create";
//    }
}
