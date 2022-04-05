/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.models;

import java.util.Objects;

/**
 *
 * @author board
 */
public class Superpower {
    
    private int superpowerId;
    
    private String name;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.superpowerId;
        hash = 43 * hash + Objects.hashCode(this.name);
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
        final Superpower other = (Superpower) obj;
        if (this.superpowerId != other.superpowerId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    

    /**
     * @return the superpowerId
     */
    public int getSuperpowerId() {
        return superpowerId;
    }

    /**
     * @param superpowerId the superpowerId to set
     */
    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
