package com.thechampion.participants.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidParticipantsNumber extends RuntimeException {
    private String message;
}
