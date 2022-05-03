package com.maksim.rasporski.filestorage.controller;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.exception.UserAlreadyExistsException;
import com.maksim.rasporski.filestorage.model.UserData;
import com.maksim.rasporski.filestorage.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Controller
public class RegistrationController {
    private AppUserService userService;

    public RegistrationController(final AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userData);
            return "register";
        }
        try {
            userService.register(userData);
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "register";
        }
        return "redirect:/home";
    }
}
