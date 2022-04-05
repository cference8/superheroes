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
    
    public Superpower addSuperPower(Superpower toAdd);
    
    public void editSuperPower(Superpower toEdit);
    
    public void deleteSuperPowerById(int id);
    
    public Superpower getSuperPowerById(int id);
    
    public List<Superpower> getAllSuperPowers();
    
}
