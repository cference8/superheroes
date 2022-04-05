/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Hero;
import java.util.List;

/**
 *
 * @author board
 */
public interface HeroDao {
    
    public Hero addHero(Hero toAdd);
    
    public void editHero(Hero toEdit);
    
    public void deleteHeroById(int id);
    
    public Hero getHeroById(int id);
    
    public List<Hero> getAllHeroes();
       
}
