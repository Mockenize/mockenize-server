package com.mockenize.model;

import java.io.Serializable;

/**
 * Created by rodrigo on 10/26/15.
 */
public class Message implements Serializable {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
