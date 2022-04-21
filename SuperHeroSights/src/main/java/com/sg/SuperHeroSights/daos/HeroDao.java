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
    
   Hero addHero(Hero toAdd);
    
    void editHero(Hero toEdit);
    
    void deleteHeroById(int id);
    
    Hero getHeroById(int id);

    List<Hero> getAllHeroes();

}
