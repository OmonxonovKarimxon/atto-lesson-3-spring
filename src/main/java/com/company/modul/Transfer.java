package com.company.modul;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfer {
    private Integer id;
    private String cardNumber;
    private String terminalNumber;
    private double sum;
    private LocalDateTime payDate;
    private  String payTerritory;
}
