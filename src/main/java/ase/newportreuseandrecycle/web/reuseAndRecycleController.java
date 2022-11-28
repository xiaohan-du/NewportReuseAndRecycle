package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.domain.User;
import ase.newportreuseandrecycle.service.UserAssembler;
import ase.newportreuseandrecycle.service.UserDto;
import ase.newportreuseandrecycle.service.UserService;
import ase.newportreuseandrecycle.web.forms.UserSignupForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class reuseAndRecycleController {

    private final UserService userService;
    public reuseAndRecycleController(UserService svc) {
        this.userService = svc;
    }

    @GetMapping("")
    public ModelAndView index(Model model) {
        var mv = new ModelAndView("index", model.asMap());
        return mv;
    }

    @GetMapping("contact-us")
    public ModelAndView contactUs(Model model) {
        var mv = new ModelAndView("contact-us", model.asMap());
        return mv;
    }

    @GetMapping("hello")
    public ModelAndView helloWorld(Model model) {
        var mv = new ModelAndView("hello", model.asMap());
        return mv;
    }

    @GetMapping("register")
    public ModelAndView register(Model model) {
        model.addAttribute("user", new UserSignupForm());
        var mv = new ModelAndView("login/signup-form", model.asMap());
        return mv;
    }

    @GetMapping("user-list")
    public ModelAndView getUsers(Model model) {
        List<UserDto> users;
        users = userService.getUsers();
        model.addAttribute("users", users);
        var mv = new ModelAndView("user/user-list");
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
}
