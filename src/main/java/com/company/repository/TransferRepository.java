package com.company.repository;

import com.company.modul.Transfer;
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
import java.util.PrimitiveIterator;

@Repository
public class TransferRepository {
    @Autowired
private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public boolean saveTransfer(Transfer transfer) {

        String sql = "Insert into transfer(terminal_number,sum,card_number,pay_territory) " +
                "values(:terminalNum, :sum, :cardNum, :payTerritory)";
        Map<String,Object> map =new HashMap<>();
        map.put("terminalNum", transfer.getTerminalNumber());
        map.put("sum", transfer.getSum());
        map.put("cardNum", transfer.getCardNumber());
        map.put("payTerritory", transfer.getPayTerritory());
        int update = namedParameterJdbcTemplate.update(sql, map);

        if(update>0){
            return true;
        }
        return false;
    }

    public void transferList() {

        String sql = "Select * from transfer ;";
        List<Transfer> transferList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transfer.class));

        for (Transfer transfer: transferList){
            System.out.println(transfer);
        }
    }

    public void transferListByCard(String cardNumber) {
        String sql ="Select * from transfer where card_number='"+ cardNumber+"';";

        List<Transfer> transferList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transfer.class));

        for (Transfer transfer: transferList){
            System.out.println(transfer);
        }

    }

    public void transferListByTerminal(String terminalNumber) {
        String sql ="Select * from transfer where terminal_number='"+ terminalNumber+"';";

        List<Transfer> transferList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transfer.class));

        for (Transfer transfer: transferList){
            System.out.println(transfer);
        }


    }
}
