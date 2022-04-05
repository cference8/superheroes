/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.daos.HeroJDBC.HeroMapper;
import com.sg.SuperHeroSights.daos.LocationJDBC.LocationMapper;
import com.sg.SuperHeroSights.models.Hero;
import com.sg.SuperHeroSights.models.Location;
import com.sg.SuperHeroSights.models.Sighting;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SightingJDBC implements SightingDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Sighting addSighting(Sighting toAdd) {

        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Sightings (heroId, locationId, date) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setInt(1, toAdd.getHero().getHeroId());
                    ps.setInt(2, toAdd.getLocation().getId());
                    ps.setString(3, toAdd.getDateSighted());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setId(generatedId);

        return toAdd;

    }

    @Override
    public void editSighting(Sighting toEdit) {
        template.update("Update Sightings SET "
                + "HeroId = ?, LocationId = ?, Date = ? WHERE SightingId = ?",
                 toEdit.getHero().getHeroId(), toEdit.getLocation().getId(),
                 toEdit.getDateSighted(), toEdit.getId());
    }

    @Override
    public void deleteSightingById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            Sighting toReturn = template.queryForObject("SELECT * FROM Sightings WHERE sightingId = ?", new SightingMapper());
            toReturn.setHeroSighted(getHeroesForSighting(id));
            return toReturn;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> toReturn = template.query("SELECT * FROM Sightings", new SightingMapper());
        for (Sighting sighting : toReturn) {
            sighting.setHeroSighted(getHeroesForSighting(sighting.getId()));
            sighting.setLocationSighted(getLocationsForSighting(sighting.getId()));
        }
        return toReturn;
    }

    private List<Hero> getHeroesForSighting(int id) {
        return template.query("SELECT h.* FROM heroes h"
                + "JOIN Sightings s ON s.heroId = h.heroId WHERE s.SightingId = ?", new HeroMapper(), id);
    }

    private List<Location> getLocationsForSighting(int id) {
        return template.query("SELECT l.* FROM locations l JOIN sightings s ON s.locationId = l.locationId WHERE s.SightingId = ?", new LocationMapper(), id);
    }

    @Override
    public List<Sighting> getAllSightingsToDisplay() {
        List<Sighting> toReturn = template.query("SELECT * FROM Sightings", new SightingMapper());

        for (Sighting sighting : toReturn) {
            sighting.setHero(getHeroForSightings(sighting.getId()));
            sighting.setLocation(getLocationForSightings(sighting.getId()));

        }
        return toReturn;
    }

    private Location getLocationForSightings(int id) {
        return template.queryForObject("SELECT l.* FROM Locations l JOIN sightings s ON s.LocationId = l.LocationId WHERE s.sightingId = ?", new LocationMapper(), id);
    }

    private Hero getHeroForSightings(int id) {
        try {
            Hero toReturn = template.queryForObject("SELECT h.* FROM heroes h JOIN sightings s ON s.heroId = h.heroId WHERE s.sightingId = ?", new HeroMapper(), id);

            return toReturn;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    private static class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet row, int i) throws SQLException {
            Sighting toReturn = new Sighting();

            toReturn.setId(row.getInt("SightingId"));
            toReturn.setDateSighted(row.getString("date"));

            return toReturn;
        }

    }

}
