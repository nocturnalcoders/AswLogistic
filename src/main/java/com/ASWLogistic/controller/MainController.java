package com.ASWLogistic.controller;

import com.ASWLogistic.constant.GeneralConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.ASWLogistic.constant.OASConstants.*;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root(Model model) {
        log.info(GeneralConstants.startLog(TITLE_DASHBOARD, this.getClass().getSimpleName()));
        if (model.asMap().containsKey("formBindingResult")) {
            model.addAttribute("org.springframework.validation.BindingResult.holder",
                    model.asMap().get("formBindingResult"));
        }

        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new ClaimDTO());
        }

        model.addAttribute("activeMenu", MENU_DASHBOARD);
        model.addAttribute("pageSubTitle", "create " + TITLE_DASHBOARD);
        model.addAttribute("firstName", userService.getCurrentUser().getFirstName());
        log.info(GeneralConstants.finishLog(TITLE_CLAIM, this.getClass().getSimpleName()));
        return "dashboard";
    }

//    @GetMapping("/user")
//    public String userIndex() {
//        return "user/index";
//    }
//
//    @GetMapping("user/appointments")
//    public String userApp(){
//        return "user/appointments";
//    }
}
