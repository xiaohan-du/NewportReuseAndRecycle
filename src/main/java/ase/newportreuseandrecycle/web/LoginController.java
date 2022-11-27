package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.service.UserDto;
import ase.newportreuseandrecycle.service.UserService;
import ase.newportreuseandrecycle.web.forms.LoginForm;
import ase.newportreuseandrecycle.web.forms.UserSignupForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class LoginController {
    private final UserService userService;
    public LoginController(UserService svc) {
        this.userService = svc;
    }

    @GetMapping("register")
    public ModelAndView register(Model model) {
        model.addAttribute("user", new UserSignupForm());
        var mv = new ModelAndView("login/signup-form", model.asMap());
        return mv;
    }

    @PostMapping("process_register")
    public ModelAndView processRegister(UserSignupForm signupForm, BindingResult bindingResult, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ModelAndView("login/signup-form", model.asMap());
        } else {
            UserDto userDto = new UserDto(signupForm.getId(),
                    signupForm.getEmail(),
                    passwordEncoder.encode(signupForm.getPassword()),
                    signupForm.getFirstName(),
                    signupForm.getLastName()
            );
            userService.addNewUser(userDto);
            var mv = new ModelAndView("login/register-success", model.asMap());
            return mv;
        }
    }

    @GetMapping("login")
    public ModelAndView getLogin(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return new ModelAndView("/login/login-form", model.asMap());
    }

    @PostMapping("login")
    public ModelAndView postLogin(Model model, LoginForm loginForm) {
        Optional<UserDto> aUser;
        System.out.println("in 1");
        if (loginForm.getEmail() != null) {
            System.out.println("in 2");
            aUser = userService.getAUserByEmail(loginForm.getEmail());
            model.addAttribute("user", aUser);
            var mv = new ModelAndView("user/user-list", model.asMap());
            return mv;
        } else {
            System.out.println("in 3");
            model.addAttribute("loginForm", new LoginForm());
            return new ModelAndView("/login/login-form", model.asMap());
        }
    }
}
