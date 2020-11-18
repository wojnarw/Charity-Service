package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/donation")
@Controller
public class DonationController {
    private DonationService donationService;
    private CategoryService categoryService;
    private InstitutionService institutionService;

    @Autowired
    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/add")
    public String addDonation(Model model) {
        Donation donation = new Donation();
        model.addAttribute(donation);
        return "donation";
    }

    @PostMapping("/save")
    public String saveDonation(Model model, @Valid Donation donation, BindingResult validation) {
        if(validation.hasErrors()) {
            return "donation";
        }
        this.donationService.save(donation);
        return "donation/saved";
    }

    /* Model attributes */

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return this.institutionService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return this.categoryService.findAll();
    }
}
