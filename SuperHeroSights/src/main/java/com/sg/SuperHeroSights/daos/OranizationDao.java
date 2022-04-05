/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Organization;
import java.util.List;

/**
 *
 * @author board
 */
public interface OranizationDao {

    public Organization addOrganization(Organization toAdd);

    public void editOrganization(Organization toEdit);
    
    public void deleteOrganizationById(int id);
    
    public Organization getOrganizationById(int id);
    
    public List<Organization> getAllOrganizations();
    
}
