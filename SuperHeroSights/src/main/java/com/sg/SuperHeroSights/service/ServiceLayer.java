/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sg.SuperHeroSights.daos.HeroDao;
import com.sg.SuperHeroSights.daos.LocationDao;
import com.sg.SuperHeroSights.daos.OranizationDao;
import com.sg.SuperHeroSights.daos.SightingDao;
import com.sg.SuperHeroSights.daos.SuperPowerDao;
import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Location;
import com.sg.SuperHeroSights.models.Organization;
import com.sg.SuperHeroSights.models.Sighting;
import com.sg.SuperHeroSights.models.Superpower;
import java.util.List;

/**
 *
 * @author board
 */
@Service
public class ServiceLayer {

    @Autowired
    HeroDao hero;

    @Autowired
    LocationDao location;

    @Autowired
    OranizationDao organization;

    @Autowired
    SightingDao sighting;

    @Autowired
    SuperPowerDao superpower;
    
    public Superpower getSuperPowerById(int id) {
        return superpower.getSuperPowerById(id);
    } 
    
    public Hero addHero(Hero toAdd) {
        return hero.addHero(toAdd);
    }
    
    public List<Hero> getAllHeroes() {
        return hero.getAllHeroes();
    } 
    
    public List<Superpower> getAllSuperPowers() {
        return superpower.getAllSuperPowers();
    }
    
    public Hero getHeroById(int heroId) {
        return hero.getHeroById(heroId);
    }
    
    public void editHero(Hero toEdit) {
        hero.editHero(toEdit);
    }
    
    public void deleteHeroById(int heroId) {
        hero.deleteHeroById(heroId);
    }
    
    public Superpower addSuperPower(Superpower toAdd) {
        return superpower.addSuperPower(toAdd);
    }
    
    public void deleteSuperPowerById(int id) {
        superpower.deleteSuperPowerById(id);
    }
    
    public void editSuperPower(Superpower toEdit) {
        superpower.editSuperPower(toEdit);
    }
    
    public List<Location> getAllLocations() {
        return location.getAllLocations();
    }
    
    public Location addLocation(Location toAdd) {
        return location.addLocation(toAdd);
    }
    
    public List<Organization> getAllOrganizations() {
        return organization.getAllOrganizations();
    }
    
    public Organization addOrganization(Organization toAdd) {
        return organization.addOrganization(toAdd);
    }
    
    public Organization getOrganizationById(int id) {
        return organization.getOrganizationById(id);
    }
    
    public void editOrganization(Organization toEdit) {
        organization.editOrganization(toEdit);
    }
    
    public void deleteOrganizationById(int id) {
        organization.deleteOrganizationById(id);
    }
    
    public void deleteLocationById(int id) {
        location.deleteLocationById(id);
    }
    
    public void editLocation(Location toEdit) {
        location.editLocation(toEdit);
    }
    
    public Location getLocationById(int id) {
        return location.getLocationById(id);
    }
    
    public List<Sighting> getAllSightings() {
        return sighting.getAllSightings();
    }
    
    public Sighting addSighting(Sighting toAdd) {
        return sighting.addSighting(toAdd);
    }
    
    public List<Sighting> getAllSightingsToDisplay() {
        return sighting.getAllSightingsToDisplay();
    }
    
    public void editSighting(Sighting toEdit) {
        sighting.editSighting(toEdit);
    }
    
    public void deleteSightingById(int id) {
        sighting.deleteSightingById(id);
    }
    
    public Sighting getSightingById(int id) {
        return sighting.getSightingById(id);
    }
    
    
    
}
