package com.company.modul;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Terminal {
    private Integer id;
    private  String terminalNumber;
    private  String territory;
}
