package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.dto.InvoiceDTO;
import com.ASWLogistic.dto.UserRegistrationDto;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.Invoice;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.InvoiceRepository;
import com.ASWLogistic.service.CustomerService;
import com.ASWLogistic.service.InvoiceService;
import com.ASWLogistic.service.PODService;
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
@RequestMapping("/invoice")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final PODService podService;
    private final InvoiceRepository invoiceRepository;

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);


    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainPod(Model model) {
        log.info(GeneralConstants.startLog(TITLE_INVOICE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_INVOICE);
        model.addAttribute("pageTitle", TITLE_INVOICE);
        model.addAttribute("pageSubTitle", "List " + TITLE_INVOICE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listInvoices", invoiceRepository.findAll());
        log.info(GeneralConstants.finishLog(TITLE_INVOICE, this.getClass().getSimpleName()));
        return FOLDER_NAME_INVOICE + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addInvoice(Model model) {
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new InvoiceDTO());
        }

        model.addAttribute("activeMenu", MENU_INVOICE);
        model.addAttribute("pageTitle", TITLE_INVOICE);
        model.addAttribute("pageSubTitle", "Create " + TITLE_INVOICE);
        model.addAttribute("listCustomer", customerService.findAll());
        model.addAttribute("listPOD", podService.findAll());
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        return FOLDER_NAME_INVOICE + CREATE;
    }

    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addClaim(@Valid @ModelAttribute("holder") InvoiceDTO invoiceDTO) {
        log.info(GeneralConstants.startLog(TITLE_INVOICE, this.getClass().getSimpleName()));
        invoiceService.addInvoice(invoiceDTO);
        log.info(GeneralConstants.finishLog(TITLE_INVOICE, this.getClass().getSimpleName()));
        return FOLDER_NAME_INVOICE + CREATE;
    }


    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, Model model) {
        log.info(GeneralConstants.startLog(TITLE_INVOICE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new ExtraFeeDTO());
        }

        model.addAttribute("activeMenu", MENU_INVOICE);
        model.addAttribute("pageTitle", TITLE_INVOICE);
        model.addAttribute("pageSubTitle", "Edit " + TITLE_INVOICE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoices", invoice);
        model.addAttribute("editedData", true);
        log.info(GeneralConstants.finishLog(TITLE_INVOICE, this.getClass().getSimpleName()));

        return FOLDER_NAME_INVOICE + EDIT;
    }

    @PostMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, @Valid InvoiceDTO invoiceDTO, Model model) throws NotFoundException, IOException {
        log.info(GeneralConstants.startLog(TITLE_INVOICE, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        Invoice updatedInvoice = invoiceService.updateInvoicesById(id,invoiceDTO);

        model.addAttribute("activeMenu", MENU_INVOICE);
        model.addAttribute("pageTitle", TITLE_INVOICE);
        model.addAttribute("pageSubTitle", "List " + TITLE_INVOICE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listInvoices", invoiceRepository.findAll());
        // Add the updated claim to the model
        model.addAttribute("invoices", updatedInvoice);

        log.info(GeneralConstants.finishLog(TITLE_INVOICE, this.getClass().getSimpleName()));
        return FOLDER_NAME_INVOICE + INDEX;
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

        invoiceService.deleteInvoiceById(id);

        model.addAttribute("activeMenu", MENU_EXTRA_FEE);
        model.addAttribute("pageTitle", TITLE_EXTRA_FEE);
        model.addAttribute("pageSubTitle", "List " + TITLE_EXTRA_FEE);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listInvoices", invoiceService.findAll());

        log.info(GeneralConstants.finishLog(TITLE_EXTRA_FEE, this.getClass().getSimpleName()));
        return FOLDER_NAME_EXTRA_FEE + INDEX;
    }
}
