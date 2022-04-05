/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSights.daos;

import com.sg.SuperHeroSights.models.Location;
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
public class LocationJDBC implements LocationDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Location addLocation(Location toAdd) {

        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Locations (name, description, address, longitude, latitude) VALUES (?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    
                    ps.setString(1, toAdd.getName());
                    ps.setString(2, toAdd.getDescription());
                    ps.setString(3, toAdd.getAddress());
                    ps.setBigDecimal(4, toAdd.getLongitude());
                    ps.setBigDecimal(5, toAdd.getLatitude());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setId(generatedId);

        return toAdd;
    }

    @Override
    public void editLocation(Location toEdit) {
        
        template.update(
                "UPDATE Locations SET name = ?, description = ?, address = ?, "
                        + "longitude = ?, latitude = ? WHERE locationId = ?"
                , toEdit.getName(), toEdit.getDescription(), toEdit.getAddress()
                , toEdit.getLongitude(), toEdit.getLatitude(), toEdit.getId());               
    }

    @Override
    public void deleteLocationById(int id) {
        template.update("DELETE FROM Locations WHERE LocationId = ?", id);
    }

    @Override
    public Location getLocationById(int id) {
        return template.queryForObject("SELECT * FROM Locations WHERE LocationId = ?", new LocationMapper(), id);
    }

    @Override
    public List<Location> getAllLocations() {
       return template.query("SELECT * FROM Locations", new LocationMapper());
    }
    

    public static class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet row, int i) throws SQLException {
            Location toReturn = new Location();
            
            toReturn.setId(row.getInt("LocationId"));
            toReturn.setName(row.getString("name"));
            toReturn.setDescription(row.getString("description"));
            toReturn.setAddress(row.getString("address"));
            toReturn.setLongitude(row.getBigDecimal("Longitude"));
            toReturn.setLatitude(row.getBigDecimal("Latitude"));
            
            return toReturn;
                    
        }

    }
}
