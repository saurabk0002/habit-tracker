package com.example.habit_tracker.controller;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Hidden
@RestController
public class RootController {

    @GetMapping("/")
    public RedirectView redirectToSwagger() {
        RedirectView rv = new RedirectView("/swagger-ui/index.html");
        rv.setExposeModelAttributes(false);
        return rv;
    }
}