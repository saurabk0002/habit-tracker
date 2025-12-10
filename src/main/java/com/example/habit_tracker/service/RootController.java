package com.example.habit_tracker.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RootController {

    @GetMapping("/")
    public RedirectView redirectToSwagger() {
        // Do first try to new springdoc path, fallback to old.
        RedirectView rv = new RedirectView("/swagger-ui/index.html");
        rv.setExposeModelAttributes(false);
        return rv;
    }
}
