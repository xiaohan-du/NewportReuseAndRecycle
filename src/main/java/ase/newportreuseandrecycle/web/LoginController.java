package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.service.UserDto;
import ase.newportreuseandrecycle.service.UserService;
import ase.newportreuseandrecycle.web.forms.LoginForm;
import ase.newportreuseandrecycle.web.forms.UserSignupForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.expression.Fields;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService svc) {
        this.userService = svc;
    }

    @GetMapping("register")
    public ModelAndView register(Model model) {
        model.addAttribute("signupForm", new UserSignupForm());
        var mv = new ModelAndView("login/signup-form", model.asMap());
        return mv;
    }

    @PostMapping("process_register")
    public ModelAndView processRegister(@ModelAttribute("signupForm") @Valid UserSignupForm signupForm, BindingResult bindingResult, ModelAndView model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (bindingResult.hasErrors()) {    
            model.setViewName("login/signup-form");
        } else {
            UserDto userDto = new UserDto(signupForm.getId(),
                    signupForm.getUsername(),
                    passwordEncoder.encode(signupForm.getPassword()),
                    signupForm.getRole(),
                    Boolean.FALSE
            );
            userService.addNewUser(userDto);
            model.setViewName("login/register-success");
        }
        return model;
    }

    @GetMapping("login")
    public ModelAndView getLogin(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return new ModelAndView("/login/login-form", model.asMap());
    }

}

