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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @Autowired
    JdbcTemplate template;

    @Override
    public Sighting addSighting(Sighting toAdd) {

        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        try {
            Date date = format.parse(toAdd.getDateSighted());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());


        KeyHolder kh = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Sightings (heroId, locationId, date) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setInt(1, toAdd.getHero().getHeroId());
                    ps.setInt(2, toAdd.getLocation().getId());
                    ps.setDate(3, sqlDate);

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setId(generatedId);

        } catch (ParseException e) {

        }

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
        template.update("DELETE FROM Sightings WHERE sightingId = ?", id);
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            Sighting toReturn = template.queryForObject("SELECT * FROM Sightings WHERE sightingId = ?", new SightingMapper(),id);
            toReturn.setHero(getHeroForSightings(toReturn.getId()));
            toReturn.setLocation(getLocationForSightings(toReturn.getId()));
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
        return template.query("SELECT h.* FROM Heroes h JOIN Sightings s ON s.heroId = h.heroId WHERE s.SightingId = ?", new HeroMapper(), id);
    }

    private List<Location> getLocationsForSighting(int id) {
        return template.query("SELECT l.* FROM Locations l JOIN Sightings s ON s.locationId = l.locationId WHERE s.SightingId = ?", new LocationMapper(), id);
    }

    @Override
    public List<Sighting> getAllSightingsToDisplay() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Sighting> toReturn = template.query("SELECT * FROM Sightings", new SightingMapper());
        for (Sighting sighting : toReturn) {
            try {
                Date date = formatter.parse(sighting.getDateSighted());
                sighting.setDateSighted(format.format(date));
            } catch (ParseException e) {

            }
            sighting.setHero(getHeroForSightings(sighting.getId()));
            sighting.setLocation(getLocationForSightings(sighting.getId()));
        }
        return toReturn;
    }

    private Location getLocationForSightings(int id) {
        return template.queryForObject("SELECT l.* FROM Locations l JOIN Sightings s ON s.LocationId = l.LocationId WHERE s.sightingId = ?", new LocationMapper(), id);
    }

    private Hero getHeroForSightings(int id) {
        try {
            Hero toReturn = template.queryForObject("SELECT h.* FROM Heroes h JOIN Sightings s ON s.heroId = h.heroId WHERE s.sightingId = ?", new HeroMapper(), id);

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
