package tech.justjava.testhome.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.justjava.testhome.model.Testimonial;
import tech.justjava.testhome.repository.TestimonialRepository;
import tech.justjava.testhome.service.TestimonialService;


import java.util.List;


@Controller
public class homeController {

//    FOR TWO ENDPOINTS

    @GetMapping("/public")
    public String publicPage(Model model) {
        model.addAttribute("testimonials", testimonialService.getAllTestimonials());
        model.addAttribute("heroText", heroText);
        model.addAttribute("isAdminView", false);
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("testimonials", testimonialService.getAllTestimonials());
        model.addAttribute("heroText", heroText);
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

    private final TestimonialService testimonialService;

    public homeController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
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

    // ADD
    @PostMapping("/admin/testimonials/add")
    public String addTestimonial(
            @RequestParam String text,
            @RequestParam String name,
            @RequestParam String role,
            @RequestParam String company) {

        testimonialService.addTestimonial(
                new Testimonial(text, name, role, company)
        );
        return "redirect:/admin";
    }

    // EDIT
    @PostMapping("/admin/testimonials/edit/{id}")
    public String editTestimonial(
            @PathVariable Long id,
            @RequestParam String text,
            @RequestParam String name,
            @RequestParam String role,
            @RequestParam String company) {

        testimonialService.updateTestimonial(
                id,
                new Testimonial(text, name, role, company)
        );
        return "redirect:/admin";
    }

    // DELETE
    @PostMapping("/admin/testimonials/delete/{id}")
    public String deleteTestimonial(@PathVariable Long id) {
        testimonialService.deleteTestimonial(id);
        return "redirect:/admin";
    }
}








