package com.company.repository;

import com.company.modul.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TerminalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public boolean isTerminalExists(String terminalNumber) {
        String sql = "Select count(*) from terminal where terminal_number = '" + terminalNumber + "';";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer > 0;

    }

    public void save_terminal(Terminal terminal) {
        String sql = "Insert into terminal(terminal_number,territory) " +
                "values(:terNum, :territory)";
        Map<String, Object> map = new HashMap<>();
        map.put("terNum", terminal.getTerminalNumber());
        map.put("territory", terminal.getTerritory());
        namedParameterJdbcTemplate.update(sql,map);

    }
    public void terminalList() {
      String sql =   "Select * from terminal ";

        List<Terminal> terminalList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terminal.class));
        terminalList.forEach(terminal -> System.out.println(terminal));

    }

    public Terminal getTerminalNumber(String terminalNumber) {

        String sql ="select * from terminal where terminal_number = '" + terminalNumber + "';";
        Terminal terminal = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>( Terminal.class));
        return  terminal;


    }
}
