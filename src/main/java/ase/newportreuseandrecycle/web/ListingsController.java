package ase.newportreuseandrecycle.web;


import ase.newportreuseandrecycle.api.ListingRestController;
import ase.newportreuseandrecycle.data.UserRepository;
import ase.newportreuseandrecycle.service.ListingDto;
import ase.newportreuseandrecycle.service.ListingService;
import ase.newportreuseandrecycle.service.UserDto;
import ase.newportreuseandrecycle.service.UserService;
import ase.newportreuseandrecycle.web.forms.ListingForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("listings")
public class ListingsController {

    private final ListingService listingService;
    private final UserService userService;

    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    public ListingsController(ListingService listingSvc, UserService userSvc) {
        this.listingService = listingSvc;
        this.userService = userSvc;
    }

    @GetMapping("add")
    public ModelAndView getNewListing(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Optional<UserDto> userDto = this.userService.getAUserByUsername(user.getUsername());

        ModelAndView mv;

        if (userDto.isPresent()) {
            ListingForm listingForm = new ListingForm();
            listingForm.setUserId(userDto.get().getId());

            ListingRestController restController = new ListingRestController(listingService);

            model.addAttribute("listingForm", listingForm);
            mv = new ModelAndView("products/add-listing", model.asMap());
        }
        else {
            mv = new ModelAndView("products/listing", model.asMap());
        }

        return mv;
    }

    @PostMapping("add")
    public ModelAndView postNewListing(ListingForm newListing, @RequestParam("image") String url) {
        String imageUrl = "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png";
        if (!url.isEmpty()) {
            imageUrl = url;
        }
        ListingDto listingDto = new ListingDto(newListing.getId(), newListing.getUserId(), newListing.getTitle(), newListing.getDescription(), newListing.getPrice(), imageUrl, newListing.getCategory());
        listingService.addListing(listingDto);

        var mv = new ModelAndView("redirect:/listings");
        return mv;
    }

    @GetMapping("edit/{id}")
    public ModelAndView editListing(Model model, @PathVariable Integer id) {
        ListingDto listingDto = listingService.getAListingById(id);
        ListingForm editForm = new ListingForm(
                listingDto.getId(),
                listingDto.getUserId(),
                listingDto.getTitle(),
                listingDto.getDescription(),
                listingDto.getPrice(),
                listingDto.getImageUrl(),
                listingDto.getCategory()
        );
        model.addAttribute("listingForm", editForm);
        var mv = new ModelAndView("products/add-listing", model.asMap());
        return mv;
    }
}
