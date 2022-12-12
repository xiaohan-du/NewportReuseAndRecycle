package ase.newportreuseandrecycle.web;

import ase.newportreuseandrecycle.api.ListingRestController;
import ase.newportreuseandrecycle.data.ListingRepository;
import ase.newportreuseandrecycle.data.ReportRepository;
import ase.newportreuseandrecycle.service.*;
import ase.newportreuseandrecycle.web.forms.ListingForm;
import ase.newportreuseandrecycle.web.forms.ReportForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("{id}")
    public ModelAndView getListing(Model model, @PathVariable Integer id) {
        ModelAndView mv;

        Optional<ListingDto> listingDto = listingService.getAListingById(id);

        if (listingDto.isPresent()) {
            model.addAttribute( "listing", listingDto.get());
            mv = new ModelAndView("products/individual-listing", model.asMap());
        } else {
            mv = new ModelAndView("error", model.asMap());
        }
        return mv;
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

            model.addAttribute("methodType", "post");
            model.addAttribute("submitURL", "/api/listings/add");
            model.addAttribute("listingForm", listingForm);
            mv = new ModelAndView("products/add-listing", model.asMap());
        } else {
            // TODO: ADD ERROR FALLBACK
            mv = new ModelAndView("products/listing", model.asMap());
        }

        return mv;
    }

    @GetMapping("edit/{id}")
    public ModelAndView editListing(Model model, @PathVariable Integer id) {
        ListingDto listingDto = listingService.getAListingById(id).get();

        ListingForm editForm = new ListingForm(
                listingDto.getId(),
                listingDto.getUserId(),
                listingDto.getTitle(),
                listingDto.getDescription(),
                listingDto.getPrice(),
                listingDto.getImageUrl(),
                listingDto.getCategory(),
                listingDto.getCollectionOrDelivery(),
                listingDto.getLatitude(),
                listingDto.getLongitude());
                
        model.addAttribute("methodType", "put");
        model.addAttribute("listingForm", editForm);
        model.addAttribute("submitURL", String.format("/api/listings/edit/%s", id));
        var mv = new ModelAndView("products/add-listing", model.asMap());

        return mv;
    }

    @PostMapping("addReport")
    public ModelAndView addNewReport(ReportForm newReport) {
        ReportDto reportDto = new ReportDto(newReport.getId(), newReport.getUserId(), newReport.getListingId(), newReport.getReason());
        reportService.addReport(reportDto);

        var mv = new ModelAndView("redirect:/listings");
        return mv;
    }

}

