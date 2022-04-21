/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Superpower;
import java.util.List;


/**
 *
 * @author board
 */

public interface SuperPowerDao {
    
    Superpower addSuperPower(Superpower toAdd);
    
    void editSuperPower(Superpower toEdit);
    
    void deleteSuperPowerById(int id);
    
    Superpower getSuperPowerById(int id);
    
    List<Superpower> getAllSuperPowers();
    
}
