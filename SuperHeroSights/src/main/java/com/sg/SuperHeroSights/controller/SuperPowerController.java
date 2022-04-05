/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.controller;

import com.sg.SuperHeroSights.models.Superpower;
import com.sg.SuperHeroSights.service.ServiceLayer;
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
public class SuperPowerController {
    
    @Autowired
    ServiceLayer service;   
    
    @GetMapping("superpower")
    public String displaySuperpowers(Model m) {
        m.addAttribute("superpowers", service.getAllSuperPowers());
        return "superpower";
    }
    
    @PostMapping("addSuperpower")
    public String addSuperpower(HttpServletRequest request) {
        String superName = request.getParameter("name");
        
        Superpower toAdd = new Superpower();
        toAdd.setName(superName);
        
        service.addSuperPower(toAdd);
        
        return "redirect:/superpower";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Integer id) {
        
        service.deleteSuperPowerById(id);
        
        return "redirect:/superpower";
    }
    
    @GetMapping("editSuperpower")
    public String displayEditSuperpower(Integer id, Model m) {
        Superpower superpower = service.getSuperPowerById(id);
        
        m.addAttribute("superpower", superpower);
        return "editSuperpower";
    }
    
    @PostMapping("editSuperpower")
    public String editSuperpower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Superpower superpower = service.getSuperPowerById(id);
        
        superpower.setName(request.getParameter("name"));
        
        service.editSuperPower(superpower);
        
        return "redirect:/superpower";
    }
    
}
