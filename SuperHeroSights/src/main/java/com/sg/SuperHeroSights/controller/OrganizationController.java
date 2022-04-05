/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.controller;

import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Organization;
import com.sg.SuperHeroSights.service.ServiceLayer;
import java.util.ArrayList;
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
public class OrganizationController {

    @Autowired
    ServiceLayer service;

    @GetMapping("organization")
    public String displayOrganizationPage(Model m) {

        m.addAttribute("organizations", service.getAllOrganizations());
        m.addAttribute("heroes", service.getAllHeroes());

        return "organization";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Organization toAdd, HttpServletRequest request) {
        String[] heroIds = request.getParameterValues("heroId");

        List<Hero> heroes = new ArrayList<>();
        for (String heroId : heroIds) {
            heroes.add(service.getHeroById(Integer.parseInt(heroId)));
        }
        toAdd.setHeroes(heroes);
        service.addOrganization(toAdd);

        return "redirect:/organization";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model m) {
        Organization toGet = service.getOrganizationById(id);
        m.addAttribute("organization", toGet);
        return "organizationDetail";
    }

    @GetMapping("editOrganization")
    public String displayEditOrganization(Integer id, Model m) {
        Organization organization = service.getOrganizationById(id);
        List<Hero> heroes = service.getAllHeroes();
        m.addAttribute("organization", organization);
        m.addAttribute("heroes", heroes);

        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String editOrganization(Organization toEdit, HttpServletRequest request) {
        String[] heroIds = request.getParameterValues("heroId");
        List<Hero> heroes = new ArrayList<>();
        for (String id : heroIds) {
            heroes.add(service.getHeroById(Integer.parseInt(id)));
        }

        toEdit.setHeroes(heroes);
        service.editOrganization(toEdit);

        return "redirect:/organization";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        service.deleteOrganizationById(id);
        return "redirect:/organization";
    }
}
