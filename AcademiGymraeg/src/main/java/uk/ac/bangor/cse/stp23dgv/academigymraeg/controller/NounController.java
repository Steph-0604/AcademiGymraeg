package uk.ac.bangor.cse.stp23dgv.academigymraeg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.ac.bangor.cse.stp23dgv.academigymraeg.model.Noun;
import uk.ac.bangor.cse.stp23dgv.academigymraeg.repo.NounRepository;

@Controller
@RequestMapping("/admin/nouns")
public class NounController {

    @Autowired
    private NounRepository nounRepo;

    @GetMapping
    public String listNouns(Model model) {
        model.addAttribute("nouns", nounRepo.findAll());
        model.addAttribute("noun", new Noun());
        model.addAttribute("editing", false);
        return "nounManagement";
    }

    @PostMapping("/create")
    public String createNoun(@ModelAttribute Noun noun) {
        nounRepo.save(noun);
        return "redirect:/admin/nouns";
    }

    @GetMapping("/edit/{id}")
    public String editNoun(@PathVariable Integer id, Model model) {
        model.addAttribute("noun", nounRepo.findById(id).orElseThrow());
        model.addAttribute("nouns", nounRepo.findAll());
        model.addAttribute("editing", true);
        return "nounManagement";
    }

    @PostMapping("/edit")
    public String updateNoun(@ModelAttribute Noun noun) {
        nounRepo.save(noun);
        return "redirect:/admin/nouns";
    }

    @GetMapping("/delete/{id}")
    public String deleteNoun(@PathVariable Integer id) {
        nounRepo.deleteById(id);
        return "redirect:/admin/nouns";
    }
}
