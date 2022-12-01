package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.service.ListingDto;
import ase.newportreuseandrecycle.service.ListingService;
import ase.newportreuseandrecycle.service.UserDto;
import ase.newportreuseandrecycle.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReuseAndRecycleController {

    private final UserService userService;
    private final ListingService listingService;
    public ReuseAndRecycleController(UserService svc, ListingService listingService) {
        this.userService = svc;
        this.listingService = listingService;
    }

    @GetMapping("")
    public ModelAndView index(Model model) {
        var mv = new ModelAndView("index", model.asMap());
        return mv;
    }

    @GetMapping("hello")
    public ModelAndView helloWorld(Model model) {
        var mv = new ModelAndView("hello", model.asMap());
        return mv;
    }

    @GetMapping("listings")
    public ModelAndView getListings(Model model) {
        List<ListingDto> listings;
        listings = listingService.getListings();
        model.addAttribute("products", listings);
        var mv = new ModelAndView("products/listings");
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

}