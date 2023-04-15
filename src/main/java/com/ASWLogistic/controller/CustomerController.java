package com.ASWLogistic.controller;


import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.CustomerDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.exception.throwable.BadRequestException;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Customer;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.ClaimRepository;
import com.ASWLogistic.repository.CustomerRepository;
import com.ASWLogistic.service.ClaimService;
import com.ASWLogistic.service.CustomerService;
import com.ASWLogistic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;

import static com.ASWLogistic.constant.GeneralConstants.*;
import static com.ASWLogistic.constant.OASConstants.*;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    private final UserService userService;

    @GetMapping()
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String mainCustomer(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }
        model.addAttribute("activeMenu", MENU_CUSTOMER);
        model.addAttribute("pageTitle", TITLE_CUSTOMER);
        model.addAttribute("pageSubTitle", "List " + TITLE_CUSTOMER);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCustomers", customerService.findAll());
        log.info(GeneralConstants.finishLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        return FOLDER_NAME_CUSTOMER + INDEX;
    }
    @GetMapping(path = "/add")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String addClaim(Model model) {
        log.info(GeneralConstants.startLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new CustomerDTO());
        }

        model.addAttribute("activeMenu", MENU_CUSTOMER);
        model.addAttribute("pageTitle", TITLE_CUSTOMER);
        model.addAttribute("pageSubTitle", "create " + TITLE_CUSTOMER);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        log.info(GeneralConstants.finishLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));

        return FOLDER_NAME_CUSTOMER + CREATE;
    }

    @PostMapping(path = "/add")
    @PreAuthorize(("@securityService.hasPrivilege('READ_USERS')"))
    public String addClaim(@Valid @ModelAttribute("holder") CustomerDTO customerDTO, Model model) throws BadRequestException {
        log.info(GeneralConstants.startLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        customerService.addCustomer(customerDTO);
        log.info(GeneralConstants.finishLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        return mainCustomer(model);
    }

    @GetMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editCustomer(@PathVariable("id") String id, Model model) {
        log.info(GeneralConstants.startLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new CustomerDTO());
        }

        model.addAttribute("activeMenu", MENU_CUSTOMER);
        model.addAttribute("pageTitle", TITLE_CUSTOMER);
        model.addAttribute("pageSubTitle", "Edit " + TITLE_CUSTOMER);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customers", customer);
        model.addAttribute("editedData", true);
        log.info(GeneralConstants.finishLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));

        return FOLDER_NAME_CUSTOMER + EDIT;
    }

    @PostMapping(path = "/edit/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String editClaim(@PathVariable("id") String id, @Valid CustomerDTO customerDTO, Model model) throws NotFoundException, IOException {
        log.info(GeneralConstants.startLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        Customer updatCustomer = customerService.updateCustomerById(id,customerDTO);

        model.addAttribute("activeMenu", MENU_CUSTOMER);
        model.addAttribute("pageTitle", TITLE_CUSTOMER);
        model.addAttribute("pageSubTitle", "List " + TITLE_CUSTOMER);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCustomers", customerService.findAll());
        // Add the updated claim to the model
        model.addAttribute("customers", updatCustomer);

        log.info(GeneralConstants.finishLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        return FOLDER_NAME_CUSTOMER + INDEX;
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("@securityService.hasPrivilege('READ_USERS')")
    public String deleteClaim(@PathVariable("id") String id, Model model)  {
        log.info(GeneralConstants.startLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new User());
        }

        customerService.deleteCustomerById(id);

        model.addAttribute("activeMenu", MENU_CUSTOMER);
        model.addAttribute("pageTitle", TITLE_CUSTOMER);
        model.addAttribute("pageSubTitle", "List " + TITLE_CUSTOMER);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        //============================================
        model.addAttribute("listCustomers", customerService.findAll());

        log.info(GeneralConstants.finishLog(TITLE_CUSTOMER, this.getClass().getSimpleName()));
        return mainCustomer(model);
    }
}
