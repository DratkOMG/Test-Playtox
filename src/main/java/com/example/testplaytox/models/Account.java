package com.example.testplaytox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author DratkOMG
 * @created 16:20 - 20/03/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    private int money;

}
