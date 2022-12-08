package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.service.ListingDto;
import ase.newportreuseandrecycle.service.ListingService;
import ase.newportreuseandrecycle.service.UserDto;
import ase.newportreuseandrecycle.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.AuthenticationFailedException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ReuseAndRecycleController {

    private final UserService userService;

    public ReuseAndRecycleController(UserService svc) {
        this.userService = svc;
    }

    @GetMapping("")
    public String index(Authentication authentication) {
        var authorities = (authentication.getAuthorities().stream().toList());
        if (authorities.get(0).getAuthority().equalsIgnoreCase("admin"))
            return "admin/admin";
        return "index";
    }

    @GetMapping("hello")
    public ModelAndView helloWorld(Model model) {
        var mv = new ModelAndView("hello", model.asMap());
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

    @GetMapping("contact-us")
    public ModelAndView contactus(Model model) {
        var mv = new ModelAndView("contact-us", model.asMap());
        return mv;
    }

    @GetMapping("map")
    public ModelAndView map(Model model) {
        var mv = new ModelAndView("map", model.asMap());
        return mv;

    }

    @GetMapping("donations")
    public ModelAndView donations(Model model) {
        var mv = new ModelAndView("donations", model.asMap());
        return mv;

    }

}
