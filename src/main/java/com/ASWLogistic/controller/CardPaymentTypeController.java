package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.dto.CardPaymentTypeDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.dto.UserRegistrationDto;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.CardPaymentType;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.User;
import com.ASWLogistic.service.CardPaymentTypeService;
import com.ASWLogistic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;

import static com.ASWLogistic.constant.GeneralConstants.*;
import static com.ASWLogistic.constant.OASConstants.*;

@Controller
@RequestMapping("/cardPaymentType")
@RequiredArgsConstructor
@Slf4j
public class CardPaymentTypeController {

    private final CardPaymentTypeService cardPaymentTypeService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CardPaymentTypeController.class);


    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainCardPaymentType(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT_TYPE, this.getClass().getSimpleName()));

        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_CARD_PAYMENT_TYPE);
        model.addAttribute("pageTitle", TITLE_CARD_PAYMENT_TYPE);
        model.addAttribute("pageSubTitle", "List " + TITLE_CARD_PAYMENT_TYPE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCardPaymentTypes", cardPaymentTypeService.findAll());
        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT_TYPE, this.getClass().getSimpleName()));
        return FOLDER_NAME_CARD_PAYMENT_TYPE + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addCardPaymentType(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT_TYPE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new CardPaymentTypeDTO());
        }

        model.addAttribute("activeMenu", MENU_CARD_PAYMENT);
        model.addAttribute("pageTitle", TITLE_CARD_PAYMENT);
        model.addAttribute("pageSubTitle", "Create " + TITLE_CARD_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT_TYPE, this.getClass().getSimpleName()));
        return FOLDER_NAME_CARD_PAYMENT_TYPE + CREATE;
    }
    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addCardPaymentType(@Valid @ModelAttribute("holder") CardPaymentTypeDTO cardPaymentTypeDTO, Model model) {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT_TYPE, this.getClass().getSimpleName()));
        cardPaymentTypeService.addCardPaymentType(cardPaymentTypeDTO);
        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT_TYPE, this.getClass().getSimpleName()));
        return mainCardPaymentType(model);
    }

    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, Model model) {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT_TYPE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new CardPaymentTypeDTO());
        }

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "Edit " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        CardPaymentType cardPaymentType = cardPaymentTypeService.getCardPaymentTypeById(id);
        model.addAttribute("cardPaymentTypes", cardPaymentType);
        model.addAttribute("editedData", true);
        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));

        return FOLDER_NAME_CARD_PAYMENT_TYPE + EDIT;
    }

    @PostMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, @Valid CardPaymentTypeDTO cardPaymentTypeDTO, Model model) throws NotFoundException, IOException {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        CardPaymentType updateCardPaymentType = cardPaymentTypeService.updateCardPaymentTypeId(id,cardPaymentTypeDTO);

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "List " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCardPaymentTypes", cardPaymentTypeService.findAll());
        // Add the updated claim to the model
        model.addAttribute("cardPaymentTypes", updateCardPaymentType);

        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return mainCardPaymentType(model);
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

        cardPaymentTypeService.deleteCardPaymentTypeById(id);

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "List " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCardPaymentTypes", cardPaymentTypeService.findAll());

        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return FOLDER_NAME_CARD_PAYMENT_TYPE + INDEX;
    }


}
