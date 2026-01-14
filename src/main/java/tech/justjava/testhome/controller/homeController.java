package tech.justjava.testhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller

public class homeController {

    // Store hero text in memory
    private String heroText =
            "We help start-ups and scale-ups build, launch, and maintain reliable software with dedicated engineering pods and a structured delivery process.";

    // Testimonials (static, not editable)
    private final String testimonial1 =
            "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.";

    private final String testimonial2 =
            "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.";

    private final String testimonial3 =
            "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.";

    // Load page with all data
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("testimonial1", testimonial1);
        model.addAttribute("testimonial2", testimonial2);
        model.addAttribute("testimonial3", testimonial3);

        model.addAttribute("heroText", heroText);

        return "index";
    }

    // Save edited hero text
    @PostMapping("/save-text")
    public String saveText(@RequestParam String heroText) {

        System.out.println("==== TEXT FROM FRONTEND ====");
        System.out.println(heroText);
        System.out.println("===========================");

        this.heroText = heroText;   // store new value

        return "redirect:/";       // reload page properly
    }
}



//public class homeController {
//    @GetMapping("/")
//    public String home(Model model) {
//
//        // This part is for the three testimonials.
//        model.addAttribute("testimonial1",
//                "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.");
//
//        model.addAttribute("testimonial2",
//                "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.");
//
//        model.addAttribute("testimonial3",
//                "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.");
//
//     // This part is for "we help startups".
//        model.addAttribute("heroText",
//                "We help start-ups and scale-ups build, launch, and maintain reliable software with dedicated engineering pods and a structured delivery process."
//        );
//
//
//// This part is for "we help startups" and testimonials.
//        return "index";
//    }
//
//    // This part is for "we help startups" also.
//    @PostMapping("/save-text")
//    public String saveText(@RequestParam String heroText, Model model) {
//
//        System.out.println("==== TEXT FROM FRONTEND ====");
//        System.out.println(heroText);
//        System.out.println("===========================");
//
//        model.addAttribute("heroText", heroText);
//
//        return "index";
//    }


//  }
//

//    @PostMapping("/save-text")
//    public String saveText(@RequestParam Map<String, String> params) {
//
//        String heroText = params.get("heroText");
//
//        System.out.println("==== TEXT FROM FRONTEND ====");
//        System.out.println(heroText);
//        System.out.println("===========================");
//
//        return "redirect:/";
//    }


