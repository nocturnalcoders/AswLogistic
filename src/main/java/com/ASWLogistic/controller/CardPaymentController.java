package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.dto.CardPaymentDTO;
import com.ASWLogistic.dto.CardPaymentTypeDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.dto.UserRegistrationDto;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.CardPayment;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.CardPaymentRepository;
import com.ASWLogistic.service.CardPaymentService;
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
@RequestMapping("/cardPayment")
@Slf4j
@RequiredArgsConstructor
public class CardPaymentController {

    private final CardPaymentService cardPaymentService;
    private final CardPaymentTypeService cardPaymentTypeService;

    private final CardPaymentRepository cardPaymentRepository;

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CardPaymentController.class);


    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainCardPayment(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_CARD_PAYMENT);
        model.addAttribute("pageTitle", TITLE_CARD_PAYMENT);
        model.addAttribute("pageSubTitle", "List " + TITLE_CARD_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCardPayments", cardPaymentService.findAll());
        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        return FOLDER_NAME_CARD_PAYMENT + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addCardPayment(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new CardPaymentDTO());
        }

        model.addAttribute("activeMenu", MENU_CARD_PAYMENT);
        model.addAttribute("pageTitle", TITLE_CARD_PAYMENT);
        model.addAttribute("pageSubTitle", "Create " + TITLE_CARD_PAYMENT);
        model.addAttribute("listCardType", cardPaymentTypeService.findAll());
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        return FOLDER_NAME_CARD_PAYMENT + CREATE;
    }

    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addExtraFee(@Valid @ModelAttribute("holder") CardPaymentDTO cardPaymentDTO, Model model) {
        log.info(GeneralConstants.startLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        cardPaymentService.addCardPayment(cardPaymentDTO);
        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return mainCardPayment(model);
    }


    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, Model model) {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new ExtraFeeDTO());
        }

        model.addAttribute("activeMenu", MENU_CARD_PAYMENT);
        model.addAttribute("pageTitle", TITLE_CARD_PAYMENT);
        model.addAttribute("pageSubTitle", "Edit " + TITLE_CARD_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        CardPayment cardPayment = cardPaymentService.getCardPaymentById(id);
        model.addAttribute("cardPayments", cardPayment);
        model.addAttribute("editedData", true);
        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));

        return FOLDER_NAME_CARD_PAYMENT + EDIT;
    }

    @PostMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, @Valid CardPaymentDTO cardPaymentDTO, Model model) throws NotFoundException, IOException {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        CardPayment updatedCardPayment = cardPaymentService.updateCardPaymentById(id,cardPaymentDTO);

        model.addAttribute("activeMenu", MENU_CARD_PAYMENT);
        model.addAttribute("pageTitle", TITLE_CARD_PAYMENT);
        model.addAttribute("pageSubTitle", "List " + TITLE_CARD_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("ListCardPayment", cardPaymentService.findAll());
        // Add the updated claim to the model
        model.addAttribute("cardPayments", updatedCardPayment);

        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        return FOLDER_NAME_CARD_PAYMENT + INDEX;
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String deleteClaim(@PathVariable("id") String id, Model model)  {
        log.info(GeneralConstants.startLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        cardPaymentService.deleteCardPaymentById(id);

        model.addAttribute("activeMenu", MENU_CARD_PAYMENT);
        model.addAttribute("pageTitle", TITLE_CARD_PAYMENT);
        model.addAttribute("pageSubTitle", "List " + TITLE_CARD_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCardPayments", cardPaymentService.findAll());

        log.info(GeneralConstants.finishLog(TITLE_CARD_PAYMENT, this.getClass().getSimpleName()));
        return mainCardPayment(model);
    }
}
