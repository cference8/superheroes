/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Sighting;
import java.util.List;



/**
 *
 * @author board
 */
public interface SightingDao {
    
    public Sighting addSighting(Sighting toAdd);
    
    public void editSighting(Sighting toEdit);
    
    public void deleteSightingById(int id);
    
    public Sighting getSightingById(int id);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsToDisplay();
}
