/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author board
 */

public class Sighting {
    
    private int id;
    
    private Hero hero;
    
    private Location location;
    
    private List<Hero> heroSighted;
    
    private List<Location> locationSighted;
    
    private String dateSighted;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.hero);
        hash = 47 * hash + Objects.hashCode(this.location);
        hash = 47 * hash + Objects.hashCode(this.heroSighted);
        hash = 47 * hash + Objects.hashCode(this.locationSighted);
        hash = 47 * hash + Objects.hashCode(this.dateSighted);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.heroSighted, other.heroSighted)) {
            return false;
        }
        if (!Objects.equals(this.locationSighted, other.locationSighted)) {
            return false;
        }
        if (!Objects.equals(this.dateSighted, other.dateSighted)) {
            return false;
        }
        return true;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Hero> getHeroSighted() {
        return heroSighted;
    }

    public void setHeroSighted(List<Hero> heroSighted) {
        this.heroSighted = heroSighted;
    }

    public List<Location> getLocationSighted() {
        return locationSighted;
    }

    public void setLocationSighted(List<Location> locationSighted) {
        this.locationSighted = locationSighted;
    }

    public String getDateSighted() {
        return dateSighted;
    }

    public void setDateSighted(String dateSighted) {
        this.dateSighted = dateSighted;
    }

    
}
