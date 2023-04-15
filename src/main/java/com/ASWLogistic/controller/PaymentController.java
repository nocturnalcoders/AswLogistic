package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;

import com.ASWLogistic.dto.PaymentDTO;
import com.ASWLogistic.dto.UserRegistrationDto;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Payment;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.InvoiceRepository;
import com.ASWLogistic.repository.PaymentRepository;
import com.ASWLogistic.service.PaymentService;
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
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    private final UserService userService;



    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainPayment(Model model) {
        log.info(GeneralConstants.startLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_PAYMENT);
        model.addAttribute("pageTitle", TITLE_PAYMENT);
        model.addAttribute("pageSubTitle", "List " + TITLE_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listPayments", paymentRepository.findAll());
        log.info(GeneralConstants.finishLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        return FOLDER_NAME_PAYMENT + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addPayment(Model model) {
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new PaymentDTO());
        }

        model.addAttribute("activeMenu", MENU_PAYMENT);
        model.addAttribute("pageTitle", TITLE_PAYMENT);
        model.addAttribute("listInvoices", invoiceRepository.findAll());
        model.addAttribute("pageSubTitle", "Create " + TITLE_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        return FOLDER_NAME_PAYMENT + CREATE;
    }

    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addPayment(@Valid @ModelAttribute("holder") PaymentDTO paymentDTO, Model model) {
        log.info(GeneralConstants.startLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        paymentService.addPayment(paymentDTO);
        log.info(GeneralConstants.finishLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        return mainPayment(model);
    }

    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editPayment(@PathVariable("id") String id, Model model) {
        log.info(GeneralConstants.startLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new PaymentDTO());
        }

        model.addAttribute("activeMenu", MENU_PAYMENT);
        model.addAttribute("pageTitle", TITLE_PAYMENT);
        model.addAttribute("pageSubTitle", "Edit " + TITLE_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payments", payment);
        model.addAttribute("editedData", true);
        log.info(GeneralConstants.finishLog(TITLE_PAYMENT, this.getClass().getSimpleName()));

        return FOLDER_NAME_PAYMENT + EDIT;
    }

    @PostMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editPayment(@PathVariable("id") String id, @Valid PaymentDTO paymentDTO, Model model) throws NotFoundException, IOException {
        log.info(GeneralConstants.startLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        Payment updatedPayment = paymentService.updatePaymentById(id,paymentDTO);

        model.addAttribute("activeMenu", MENU_PAYMENT);
        model.addAttribute("pageTitle", TITLE_PAYMENT);
        model.addAttribute("pageSubTitle", "List " + TITLE_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listPayment", paymentService.findAll());
        // Add the updated claim to the model
        model.addAttribute("payments", updatedPayment);

        log.info(GeneralConstants.finishLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        return FOLDER_NAME_PAYMENT + INDEX;
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String deletePayment(@PathVariable("id") String id, Model model)  {
        log.info(GeneralConstants.startLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        paymentService.deletePaymentById(id);

        model.addAttribute("activeMenu", MENU_PAYMENT);
        model.addAttribute("pageTitle", TITLE_PAYMENT);
        model.addAttribute("pageSubTitle", "List " + TITLE_PAYMENT);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listPayments", paymentService.findAll());

        log.info(GeneralConstants.finishLog(TITLE_PAYMENT, this.getClass().getSimpleName()));
        return mainPayment(model);
    }
}
