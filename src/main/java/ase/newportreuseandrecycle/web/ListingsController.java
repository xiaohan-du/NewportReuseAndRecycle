package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.api.ListingRestController;
import ase.newportreuseandrecycle.data.ListingRepository;
import ase.newportreuseandrecycle.data.ReportRepository;
import ase.newportreuseandrecycle.domain.Listing;
import ase.newportreuseandrecycle.domain.Report;
import ase.newportreuseandrecycle.service.*;
import ase.newportreuseandrecycle.web.forms.ListingForm;
import ase.newportreuseandrecycle.web.forms.ReportForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("listings")
public class ListingsController {

    private final ListingService listingService;

    private final ListingRepository listingRepository;
    private final ReportRepository reportRepository;

    private final ReportService reportService;

    private final UserService userService;

    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    public ListingsController(ListingService listingSvc, ListingRepository listingRepository, UserService userSvc, ReportRepository reportRepository, ReportService reportSvc) {
        this.listingService = listingSvc;
        this.listingRepository = listingRepository;
        this.userService = userSvc;
        this.reportRepository = reportRepository;
        this.reportService = reportSvc;
    }

    @GetMapping("add")
    public ModelAndView getNewListing(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Optional<UserDto> userDto = this.userService.getUserByUsername(user.getUsername());

        ModelAndView mv;

        if (userDto.isPresent()) {
            ListingForm listingForm = new ListingForm();
            listingForm.setUserId(userDto.get().getId());

            ListingRestController restController = new ListingRestController(listingService);

            model.addAttribute("listingForm", listingForm);
            mv = new ModelAndView("products/add-listing", model.asMap());
        } else {
            mv = new ModelAndView("products/listing", model.asMap());
        }

        return mv;
    }

    @PostMapping("add")
    public ModelAndView postNewListing(ListingForm newListing, @RequestParam("image") String url, Model model) {
        String imageUrl = "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png";
        if (!url.isEmpty()) {
            imageUrl = url;
        }

        ListingDto listingDto = new ListingDto(newListing.getId(), newListing.getUserId(), newListing.getTitle(),
                newListing.getDescription(), newListing.getPrice(), imageUrl, newListing.getCategory(),
                newListing.getCollectionOrDelivery(), newListing.getLatitude(), newListing.getLongitude());
        listingService.addListing(listingDto);
        model.addAttribute("submitURL", "add");
        var mv = new ModelAndView("redirect:/listings");
        return mv;
    }

    @PostMapping("addReport")
    public ModelAndView addNewReport(ReportForm newReport) {
        ReportDto reportDto = new ReportDto(newReport.getId(), newReport.getUserid(), newReport.getListingid(), newReport.getReason());
        reportService.addReport(reportDto);

        var mv = new ModelAndView("redirect:/listings");
        return mv;
    }

}

