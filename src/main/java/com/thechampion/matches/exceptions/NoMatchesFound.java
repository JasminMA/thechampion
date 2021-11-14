package com.thechampion.matches.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoMatchesFound extends RuntimeException {
    private String message;
}
