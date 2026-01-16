package tech.justjava.testhome.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.justjava.testhome.model.Testimonial;
import tech.justjava.testhome.repository.TestimonialRepository;

import java.util.List;

@Controller
public class homeController {

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

    @GetMapping("/")
    public String home(Model model) {

        List<Testimonial> testimonials = testimonialRepo.findAll();

        model.addAttribute("testimonials", testimonials);
        model.addAttribute("heroText", heroText);

        return "index";
    }



    @PostMapping("/save-text")
    public String saveText(@RequestParam String heroText) {

        System.out.println("==== TEXT FROM FRONTEND ====");
        System.out.println(heroText);
        System.out.println("===========================");

        this.heroText = heroText;

        return "redirect:/";
    }
}




//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import tech.justjava.testhome.model.Testimonial;
//import tech.justjava.testhome.repository.TestimonialRepository;
//
//import java.util.List;
//
//@Controller
//public class homeController {
//
//    private String heroText =
//            "We help start-ups and scale-ups build, launch, and maintain reliable software with dedicated engineering pods and a structured delivery process.";
//
//    private final TestimonialRepository testimonialRepo;
//
//    public homeController(TestimonialRepository testimonialRepo) {
//        this.testimonialRepo = testimonialRepo;
//
//        // Seed database only if empty
//        if (testimonialRepo.count() == 0) {
//            testimonialRepo.save(new Testimonial(
//                    "We burned months with freelancers who ghosted us halfway through...",
//                    "Simon Claw", "CEO", "Company"));
//
//            testimonialRepo.save(new Testimonial(
//                    "JustJava helped us scale faster than expected.",
//                    "Jane Doe", "CTO", "StartupX"));
//
//            testimonialRepo.save(new Testimonial(
//                    "Reliable engineering with clear delivery process everyday.",
//                    "Mark Stone", "Founder", "TechCorp"));
//        }
//    }
//
//    // Load homepage
//    @GetMapping("/")
//    public String home(Model model) {
//
//        List<Testimonial> testimonials = testimonialRepo.findAll();
//
//        model.addAttribute("testimonials", testimonials);
//        model.addAttribute("heroText", heroText);
//
//        return "index";
//    }
//
//    // Save hero text from frontend
//    @PostMapping("/save-text")
//    public String saveText(@RequestParam String heroText) {
//
//        System.out.println("==== TEXT FROM FRONTEND ====");
//        System.out.println(heroText);
//        System.out.println("===========================");
//
//        this.heroText = heroText;
//
//        return "redirect:/";
//    }
//
//    // Add a new testimonial (test endpoint)
//    @GetMapping("/add-testimonial")
//    @ResponseBody
//    public String addTestimonial() {
//
//        testimonialRepo.save(new Testimonial(
//                "Added from browser",
//                "Alex",
//                "CEO",
//                "TechCorp"
//        ));
//
//        return "New testimonial added!";
//    }
//}


