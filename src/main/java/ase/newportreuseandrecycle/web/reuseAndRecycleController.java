package ase.newportreuseandrecycle.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class reuseAndRecycleController {
    @GetMapping("hello")
    public ModelAndView helloWorld(Model model) {
        var mv = new ModelAndView("hello", model.asMap());
        return mv;
    }

    @GetMapping("login")
    public ModelAndView login(Model model) {
        var mv = new ModelAndView("login/login", model.asMap());
        return mv;
    }
}
