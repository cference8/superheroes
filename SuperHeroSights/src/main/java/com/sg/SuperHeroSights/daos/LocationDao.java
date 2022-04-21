/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Location;
import java.util.List;


/**
 *
 * @author board
 */
public interface LocationDao {
    
    Location addLocation(Location toAdd);
    
    void editLocation(Location toEdit);
    
    void deleteLocationById(int id);
    
    Location getLocationById(int id);
    
    List<Location> getAllLocations();
    
}
