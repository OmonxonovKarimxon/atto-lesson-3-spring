package com.company.repository;

import com.company.modul.Profil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class UserRepository {
@Autowired
    private  JdbcTemplate jdbcTemplate;
    public Profil auth(String login, String password) {
        String sql = "Select * from profil where login = '" + login + "' and password ='" + password + "'";
        Profil profil = jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<>(Profil.class));
         return  profil;
    }

    public void adminCard() {

            Profil admin = auth("123", "123");
            String sql = "update profil set admin_card="+(admin.getAdminCard()+1400);

            jdbcTemplate.update(sql);

        };





    public void showAdminCard() {
        String sql = "Select admin_card from profil ;";
        Profil profil = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Profil.class));
        System.out.println(profil);
    }
}
