/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Superpower;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SuperPowerJDBC implements SuperPowerDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Superpower addSuperPower(Superpower toAdd) {

        KeyHolder kh = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO SuperPowers (name) VALUES (?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, toAdd.getName());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setSuperpowerId(generatedId);

        return toAdd;

    }

    @Override
    public void editSuperPower(Superpower toEdit) {
        template.update("UPDATE SuperPowers SET name = ? WHERE superpowerId = ?"
                , toEdit.getName(), toEdit.getSuperpowerId());
    }

    @Override
    public void deleteSuperPowerById(int id) {
        
        template.update("UPDATE Heroes SET superpowerId = ? WHERE superpowerId = ?",null, id);
        
        template.update("DELETE FROM SuperPowers WHERE superpowerId = ?", id);
    }

    @Override
    public Superpower getSuperPowerById(int id) {

        Superpower retrieved = template.queryForObject(
                "SELECT * FROM SuperPowers WHERE SuperpowerId = ?", new SuperPowerMapper(), id);

        return retrieved;
    }

    @Override
    public List<Superpower> getAllSuperPowers() {
        return template.query("SELECT * FROM SuperPowers", new SuperPowerMapper());
    }

    public static class SuperPowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower superPower = new Superpower();
            superPower.setSuperpowerId(rs.getInt("superpowerId"));
            superPower.setName(rs.getString("name"));

            return superPower;
        }

    }

}
