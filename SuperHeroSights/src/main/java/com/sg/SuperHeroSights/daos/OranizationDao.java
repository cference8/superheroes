/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Organization;
import java.util.List;

/**
 *
 * @author board
 */
public interface OranizationDao {

    Organization addOrganization(Organization toAdd);

    void editOrganization(Organization toEdit);
    
    void deleteOrganizationById(int id);
    
    Organization getOrganizationById(int id);
    
    List<Organization> getAllOrganizations();
    
}
