/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.controller;

import com.sg.SuperHeroSights.models.Location;
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
public class LocationController {

    @Autowired
    ServiceLayer service;
    
    @GetMapping("location")
    public String displayLocationPage(Model m) {
        m.addAttribute("locations", service.getAllLocations());
        return "location";
    }
    
    @PostMapping("addLocation")
    public String addLocation(Location toAdd) {
        
        service.addLocation(toAdd);
        
        return "redirect:/location";
    }
    
    @GetMapping("editLocation")
    public String displayEditLocation(HttpServletRequest request, Model m) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location toEdit = service.getLocationById(id);
        
        m.addAttribute("location", toEdit);
        
        return "editLocation";
    }
    
    @PostMapping("editLocation")
    public String editLocation(Location toEdit, HttpServletRequest request){
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        toEdit.setId(id);
                
        service.editLocation(toEdit);
        
        return "redirect:/location";
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id){
        service.deleteLocationById(id);
        return "redirect:/location";
    }
}
