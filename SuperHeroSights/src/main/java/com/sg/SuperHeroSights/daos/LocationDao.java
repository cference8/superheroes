/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Location;
import java.util.List;


/**
 *
 * @author board
 */
public interface LocationDao {
    
    public Location addLocation(Location toAdd);
    
    public void editLocation(Location toEdit);
    
    public void deleteLocationById(int id);
    
    public Location getLocationById(int id);
    
    public List<Location> getAllLocations();
    
}
