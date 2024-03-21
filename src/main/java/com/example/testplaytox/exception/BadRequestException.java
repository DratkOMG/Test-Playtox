package com.example.testplaytox.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author DratkOMG
 * @created 17:22 - 21/03/2024
 */
@Data
@Builder
@AllArgsConstructor
public class BadRequestException extends RuntimeException {

    private String message;
    private int code;
}
