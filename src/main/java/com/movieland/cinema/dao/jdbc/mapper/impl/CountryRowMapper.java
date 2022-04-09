package com.movieland.cinema.dao.jdbc.mapper.impl;

import com.movieland.cinema.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Country country = new Country();

        long country_id = resultSet.getLong("country_id");
        String countryName = resultSet.getString("country_name");

        country.setCountryId(country_id);
        country.setCountryName(countryName);

        return country;
    }
}