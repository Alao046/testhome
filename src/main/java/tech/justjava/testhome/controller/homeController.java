package tech.justjava.testhome.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.justjava.testhome.model.Testimonial;
import tech.justjava.testhome.repository.TestimonialRepository;

import java.util.List;


@Controller
public class homeController {

//    FOR TWO ENDPOINTS
    @GetMapping("/public")
    public String publicPage(Model model) {
        List<Testimonial> testimonials = testimonialRepo.findAll();
        model.addAttribute("testimonials", testimonials);
        model.addAttribute("heroText", heroText);

        // Set flag to false for public view
        model.addAttribute("isAdminView", false);
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Testimonial> testimonials = testimonialRepo.findAll();
        model.addAttribute("testimonials", testimonials);
        model.addAttribute("heroText", heroText);

        // Set flag to true for admin view
        model.addAttribute("isAdminView", true);
        return "index";
    }




    @GetMapping("/about")
    public String aboutPage() {
        return "aboutpage";
    }

    @GetMapping("/designos")
    public String designos() {
        return "designos";
    }


    private String heroText =
            "We help start-ups and scale-ups build, launch, and maintain reliable software with dedicated engineering pods and a structured delivery process.";

    private final TestimonialRepository testimonialRepo;

    public homeController(TestimonialRepository testimonialRepo) {
        this.testimonialRepo = testimonialRepo;

        // Seed database if empty
        if (testimonialRepo.count() == 0) {
            testimonialRepo.save(new Testimonial(
                    "We burned months with freelancers who ghosted us halfway through...",
                    "Simon Claw", "CEO", "Company"));

            testimonialRepo.save(new Testimonial(
                    "JustJava helped us scale faster than expected.",
                    "Jane Doe", "CTO", "StartupX"));

            testimonialRepo.save(new Testimonial(
                    "Reliable engineering with clear delivery process everyday.",
                    "Mark Stone", "Founder", "TechCorp"));
        }
    }

// REDiRECT TO PUBLIC WHEN USER ENTERS LOCALHOST:8081, BUT redirects to admin if the admin page is logged in first.
//    @GetMapping("/")
//    public String home(Model model) {
//
//        List<Testimonial> testimonials = testimonialRepo.findAll();
//
//        model.addAttribute("testimonials", testimonials);
//        model.addAttribute("heroText", heroText);
//
//
//        return "index";
//    }

 // REDERICT TO PUBLIC WHEN USER ENTERS LOCALHOST:8081
    @GetMapping("/")
    public String index() {
        return "redirect:/public";
    }


    @PostMapping("/save-text")
    public String saveText(@RequestParam String heroText) {

        System.out.println("==== TEXT FROM FRONTEND ====");
        System.out.println(heroText);
        System.out.println("===========================");

        this.heroText = heroText;

//        return "redirect:/";
        return "redirect:/admin";
    }
}





