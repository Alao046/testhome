package tech.justjava.testhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller

public class homeController {
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("testimonial1",
                "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.");

        model.addAttribute("testimonial2",
                "who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.");

        model.addAttribute("testimonial3",
                "We burned months with freelancers who ghosted us halfway through. JustJava came in, rebuilt the backend, and delivered our platform on a real timeline. Their structure and communication were a breath of fresh air—we finally felt like we had a real engineering team.");

        return "index";
    }

    @PostMapping("/save-text")
    public String saveText(@RequestParam Map<String, String> params) {

        String heroText = params.get("heroText");

        System.out.println("==== TEXT FROM FRONTEND ====");
        System.out.println(heroText);
        System.out.println("===========================");

        return "redirect:/";
    }

}
