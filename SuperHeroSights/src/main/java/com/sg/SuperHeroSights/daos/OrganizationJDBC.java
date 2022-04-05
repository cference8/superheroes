/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.daos.HeroJDBC.HeroMapper;
import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Organization;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author board
 */
@Repository
public class OrganizationJDBC implements OranizationDao {

    @Autowired
    JdbcTemplate template;

    @Override
    @Transactional
    public Organization addOrganization(Organization toAdd) {

        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Organizations (name, description, address, phone) VALUES (?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, toAdd.getName());
                    ps.setString(2, toAdd.getDescription());
                    ps.setString(3, toAdd.getAddress());
                    ps.setString(4, toAdd.getPhone());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setId(generatedId);

        insertOrgHero(toAdd);

        return toAdd;
    }

    private void insertOrgHero(Organization toAdd) {
        for (Hero hero : toAdd.getHeroes()) {
            template.update("INSERT INTO organization_hero(OrganizationId, HeroId) VALUES (?,?)", toAdd.getId(), hero.getHeroId());
        }
    }

    @Override
    @Transactional
    public void editOrganization(Organization toEdit) {
        template.update("UPDATE organizations SET name = ?, description = ?, address = ?, phone = ? WHERE organizationId = ?", 
                toEdit.getName(), toEdit.getDescription(), toEdit.getAddress(), toEdit.getPhone(), toEdit.getId());
        
        template.update("DELETE FROM Organization_hero WHERE organizationId = ?", toEdit.getId());
        insertOrgHero(toEdit);
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        //delete from bridge table
        template.update("DELETE FROM organization_hero WHERE organizationId = ?", id);
        //delete from org table
        template.update("DELETE FROM Organizations WHERE OrganizationId = ?", id);
        
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            Organization toReturn = template.queryForObject(
                    "SELECT * FROM Organizations WHERE OrganizationId = ?",
                    new OrganizationMapper(), id);
            
            toReturn.setHeroes(getHeroByOrganizationId(id));            
            return toReturn;
        } catch (DataAccessException ex) {
            return null;
        }

    }

    private List<Hero> getHeroByOrganizationId(int id) {
        List<Hero> toReturn = template.query("SELECT * FROM heroes h JOIN superpowers sp ON sp.superpowerId = h.superpowerId \n" +
"WHERE h.heroId IN (SELECT oh.heroId FROM organization_hero oh WHERE oh.organizationId = ?) ",
                new HeroMapper(), id);

        return toReturn;

    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> toReturn = template.query("SELECT * FROM Organizations", new OrganizationMapper());
        for (Organization org : toReturn) {
            org.setHeroes(getHeroByOrganizationId(org.getId()));
        }
        return toReturn;
    }

    private static class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet row, int i) throws SQLException {
            Organization toReturn = new Organization();

            toReturn.setId(row.getInt("OrganizationId"));
            toReturn.setName(row.getString("name"));
            toReturn.setDescription(row.getString("description"));
            toReturn.setAddress(row.getString("address"));
            toReturn.setPhone(row.getString("phone"));

            return toReturn;
        }

    }

}
