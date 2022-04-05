/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.controller;

import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Superpower;
import com.sg.SuperHeroSights.service.ServiceLayer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author board
 */
@Controller
public class HeroController {

    @Autowired
    ServiceLayer service;

    @GetMapping("hero")
    public String displayHeroPage(Model m) {

        m.addAttribute("heroes", service.getAllHeroes());
        m.addAttribute("superpowers", service.getAllSuperPowers());
        return "hero";
    }

    @PostMapping("addHero")
    public String addHero(Hero toAdd, HttpServletRequest request) {

        String superpowerId = request.getParameter("superpowerId");

        toAdd.setPower(service.getSuperPowerById(Integer.parseInt(superpowerId)));

        service.addHero(toAdd);

        return "redirect:/hero";
    }

    @GetMapping("editHero")
    public String displayEditHeroPage(Model m, Integer id) {

        Hero toEdit = service.getHeroById(id);
        List<Superpower> superpowers = service.getAllSuperPowers();
        m.addAttribute("hero", toEdit);
        m.addAttribute("superpowers", superpowers);
        
        return "editHero";

    }
    
    @PostMapping("editHero")
    public String editHero(Hero toEdit, HttpServletRequest request) {
        String superpowerId = request.getParameter("superpowerId");
        
        toEdit.setPower(service.getSuperPowerById(Integer.parseInt(superpowerId)));
        
        service.editHero(toEdit);
        
        return "redirect:/hero";
    }
    
    @GetMapping("deleteHero")
    public String deleteHero(Integer id) {
        service.deleteHeroById(id);
        return "redirect:/hero";
    }

}
