/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.daos.SuperPowerJDBC.SuperPowerMapper;
import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Superpower;
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

/**
 *
 * @author board
 */
@Repository
public class HeroJDBC implements HeroDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Hero addHero(Hero toAdd) {

        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO heroes (name, description, superpowerId) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, toAdd.getName());
                    ps.setString(2, toAdd.getDescription());
                    ps.setInt(3, toAdd.getPower().getSuperpowerId());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setHeroId(generatedId);

        return toAdd;
    }

    @Override
    public void editHero(Hero toEdit) {
        template.update("Update Heroes SET "
                + "name = ?, description = ?, superpowerId = ? WHERE heroId = ?"
                , toEdit.getName(), toEdit.getDescription()
                , toEdit.getPower().getSuperpowerId(),toEdit.getHeroId());

    }

    @Override
    public void deleteHeroById(int heroId) {
        template.update("DELETE FROM heroes WHERE heroId = ?", heroId);
    }

    @Override
    public Hero getHeroById(int heroId) {
        try {
            Hero retrieved = template.queryForObject("SELECT * FROM Heroes WHERE heroId = ?", new HeroMapper(), heroId);
            retrieved.setPower(getSuperPowerForHero(heroId));
            return retrieved;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> toReturn = template.query("SELECT * FROM Heroes", new HeroMapper());

        for (Hero hero : toReturn) {
            hero.setPower(getSuperPowerForHero(hero.getHeroId()));
        }

        return toReturn;
    }

    private Superpower getSuperPowerForHero(int heroId) {
        return template.queryForObject("SELECT sp.* FROM superpowers sp JOIN heroes h ON h.superpowerId = sp.SuperPowerId WHERE h.heroId = ?", new SuperPowerMapper(), heroId);
    }

    public static class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet row, int i) throws SQLException {
            Hero toReturn = new Hero();

            toReturn.setHeroId(row.getInt("heroId"));
            toReturn.setName(row.getString("name"));
            toReturn.setDescription(row.getString("description"));

            return toReturn;
        }

    }

}
