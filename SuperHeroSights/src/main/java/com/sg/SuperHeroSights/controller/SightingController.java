/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.controller;

import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Location;
import com.sg.SuperHeroSights.models.Sighting;
import com.sg.SuperHeroSights.service.ServiceLayer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingController {
    
    @Autowired
    ServiceLayer service;    
    
    @GetMapping("sighting")
    public String displaySightingPage(Model m){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        List<Sighting> sightings = service.getAllSightingsToDisplay();
        for(Sighting dateToFormat : sightings) {
            LocalDate date = LocalDate.parse(dateToFormat.getDateSighted());
            dateToFormat.setDateSighted(date.format(formatter));
        }
        List<Hero> heroes = service.getAllHeroes();
        List<Location> locations = service.getAllLocations();
        
        m.addAttribute("sightings", sightings);
        m.addAttribute("heroes", heroes);
        m.addAttribute("locations", locations);
        
        return "sighting";
    }
    
    @PostMapping("addSighting")
    public String addSighting(Sighting toAdd, HttpServletRequest request) {
        
        String heroId = request.getParameter("heroId");
        String locationId = request.getParameter("locationId");
            
        Hero hero = service.getHeroById(Integer.parseInt(heroId));
        toAdd.setHero(hero);
        Location location = service.getLocationById(Integer.parseInt(locationId));
        toAdd.setLocation(location);
        
        service.addSighting(toAdd);

        return "redirect:/sighting";
        
    }
    
    @GetMapping("editSighting")
    public String displayEditSightingPage(Model m, Integer id){
        Sighting toEdit = service.getSightingById(id);
        List<Hero> heroes = service.getAllHeroes();
        List<Location> locations = service.getAllLocations();
        m.addAttribute("sighting", toEdit);
        m.addAttribute("heroes", heroes);
        m.addAttribute("locations", locations);
        return "editSighting";
    }

    @GetMapping("deleteSighting")
    public String deleteLocation(Integer id){
        service.deleteSightingById(id);
        return "redirect:/sighting";
    }
}
