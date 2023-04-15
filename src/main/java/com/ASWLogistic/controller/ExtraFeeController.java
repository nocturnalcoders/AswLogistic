package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.ClaimRepository;
import com.ASWLogistic.service.ClaimService;
import com.ASWLogistic.service.ExtraFeeService;
import com.ASWLogistic.service.StuffingService;
import com.ASWLogistic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;

import static com.ASWLogistic.constant.GeneralConstants.*;
import static com.ASWLogistic.constant.OASConstants.*;

@Controller
@RequestMapping("/extrafee")
@RequiredArgsConstructor
@Slf4j
public class ExtraFeeController {

    private final ExtraFeeService extraFeeService;
    private final UserService userService;

    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainExtraFee(Model model) {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "List " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listExtraFees", extraFeeService.findAll());
        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return FOLDER_NAME_EXTRA_FEE + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addExtraFee(Model model) {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new ExtraFeeDTO());
        }

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "create " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));

        return FOLDER_NAME_EXTRA_FEE + CREATE;
    }

    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addExtraFee(@Valid @ModelAttribute("holder") ExtraFeeDTO extraFeeDTO, Model model) {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        extraFeeService.addExtraFee(extraFeeDTO);
        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return mainExtraFee(model);
    }

    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, Model model) {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new ExtraFeeDTO());
        }

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "Edit " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        ExtraFee extraFee = extraFeeService.getExtrafeeById(id);
        model.addAttribute("extrafees", extraFee);
        model.addAttribute("editedData", true);
        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));

        return FOLDER_NAME_EXTRA_FEE + EDIT;
    }

    @PostMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, @Valid ExtraFeeDTO extraFeeDTO, Model model) throws NotFoundException, IOException {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        ExtraFee updatedExtraFee = extraFeeService.updateExtraFeesById(id,extraFeeDTO);

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "List " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listExtraFees", extraFeeService.findAll());
        // Add the updated claim to the model
        model.addAttribute("extrafees", updatedExtraFee);

        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return FOLDER_NAME_EXTRA_FEE + INDEX;
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String deleteClaim(@PathVariable("id") String id, Model model)  {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        extraFeeService.deleteExtraFeeById(id);

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "List " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listExtraFees", extraFeeService.findAll());

        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return mainExtraFee(model);
    }
}
