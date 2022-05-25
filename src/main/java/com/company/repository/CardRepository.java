package com.company.repository;

import com.company.enums.CardStatus;
import com.company.modul.Card;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@Data
@Repository

public class CardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void changeBalans(Card card) {
        String sql = "update card set balans=:balans  Where card_number =:cardNum";

        Map<String, Object> map = new HashMap<>();
        map.put("balans", card.getBalans() - 1400);
        map.put("cardNum", card.getCardNumber());
        namedParameterJdbcTemplate.update(sql, map);

    }

    public void cardList() {
        String sql = "Select * from card ;";
        List<Card> cardList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Card.class));
        cardList.forEach(card -> System.out.println(card));
    }

    public boolean isCardExists(String cardNumber) {
        String sql = "Select count(*) from card where card_number = '" + cardNumber + "';";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer > 0;
    }


    public void save_card(Card card) {

        String sql = "Insert into card(card_number,balans,status) values(:cardNum, :balans, 'ACTIVE')";

        Map<String, Object> map = new HashMap<>();
        map.put("cardNum", card.getCardNumber());
        map.put("balans", card.getBalans());
        namedParameterJdbcTemplate.update(sql, map);
    }


    public Card getCardNumber(String cardNumber) {
        String sql = "select * from card where card_number = '" + cardNumber + "';";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Card.class));

    }

    public void changeStatus(String cardNumber, CardStatus status) {
        String sql = "update card set status=" + status + " where card_number='" + cardNumber + "';";
        jdbcTemplate.update(sql);

    }


    public void payment(Card card, double sum) {
        String sql = "update card set balans=" + (sum+card.getBalans()) + " where card_number='" + card.getCardNumber() + "';";
        jdbcTemplate.update(sql);
    }
}
