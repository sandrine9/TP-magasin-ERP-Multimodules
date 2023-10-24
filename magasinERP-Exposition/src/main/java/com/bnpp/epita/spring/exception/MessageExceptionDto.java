package com.bnpp.epita.spring.exception;

import java.time.LocalDate;

public class MessageExceptionDto {
    private String code;
    private String message;
    private LocalDate date;

    public MessageExceptionDto(String code, String message, LocalDate date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public MessageExceptionDto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
