package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.service.ListingAssembler;
import ase.newportreuseandrecycle.service.ListingDto;
import ase.newportreuseandrecycle.service.ListingService;
import ase.newportreuseandrecycle.service.UserDto;
import ase.newportreuseandrecycle.service.UserService;
import ase.newportreuseandrecycle.service.message.ListingRequest;
import ase.newportreuseandrecycle.service.message.ListingResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class ReuseAndRecycleController {

    private final UserService userService;
    private final ListingService listingService;

    public ReuseAndRecycleController(UserService userSvc, ListingService listingSvc) {
        this.userService = userSvc;
        this.listingService = listingSvc;
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

    @GetMapping("user/{id}")
    public ModelAndView userProfile(Model model, @PathVariable Integer id, HttpServletResponse response) throws IOException {
        ModelAndView mv;
        ListingRequest listingRequest = ListingRequest.of().build();
        Optional<UserDto> userDto = userService.getUserById(id);
        
        ListingResponse listingResponse = listingService.getListingsByUserId(listingRequest, id);
        List<ListingDto> listings = listingResponse.getListings();

        if (userDto.isPresent()) {
            model.addAttribute("listings", listings);
            model.addAttribute("user", userDto.get());
            mv = new ModelAndView("user/profile", model.asMap());
        } else {
            response.sendError(0, String.format("User with ID '%d' doesn't exist", id));
            mv = new ModelAndView("listings", model.asMap());
        }
        return mv;
    }
}

