package org.messageReceiverService.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Message {

    private BigDecimal number;
    private String message;

    public Message(BigDecimal number, String message) {
        this.number = number;
        this.message = message;
    }

}
