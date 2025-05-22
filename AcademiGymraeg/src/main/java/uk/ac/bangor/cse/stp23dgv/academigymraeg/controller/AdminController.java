package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;
/**
 * AdminController
 * Manages user administration: listing, creating, editing, deleting users,
 * and resetting passwords.
 * 
 * Secured so only users with ADMIN role can access.
 * 
 * Written by Steph Parry.
 * 
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.User;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.UserRepository;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "userManagement";
    }
    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());  
        model.addAttribute("action", "create");
        return "userManagement";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{username}")
    public String editUserForm(@PathVariable String username, Model model) {
        User user = userRepo.findByUsername(username).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("action", "edit");
        return "userManagement";
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user) {
        User existing = userRepo.findByUsername(user.getUsername()).orElseThrow();
        existing.setAdmin(user.isAdmin());
        existing.setInstructor(user.isInstructor());
        existing.setStudent(user.isStudent());
        userRepo.save(existing);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userRepo.deleteById(username);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/reset-password")
    public String resetPassword(@RequestParam String username, @RequestParam String newPassword) {
        User user = userRepo.findByUsername(username).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        return "redirect:/admin/users";
    }

  }

