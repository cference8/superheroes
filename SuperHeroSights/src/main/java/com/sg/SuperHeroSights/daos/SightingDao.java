/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Sighting;
import java.util.List;

/**
 *
 * @author board
 */
public interface SightingDao {
    
    Sighting addSighting(Sighting toAdd);
    
    void editSighting(Sighting toEdit);
    
    void deleteSightingById(int id);
    
    Sighting getSightingById(int id);
    
    List<Sighting> getAllSightings();
    
    List<Sighting> getAllSightingsToDisplay();
}
