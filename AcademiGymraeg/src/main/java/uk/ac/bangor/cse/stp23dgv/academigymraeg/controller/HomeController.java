package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminHome() {
        return "admin";
    }

    @GetMapping("/instructor") 
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public String instructorHome() {
        return "instructor";
    }

    @GetMapping("/user")
    public String userHome() {
        return "user";
    }}