package com.codecool.krk;

import java.util.Date;

public class Visitor {

    private String name;
    private String surname;
    private String message;
    private Date date;

    public Visitor(String name, String surname, String message, Date date) {
        this.name = name;
        this.surname = surname;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
