package service;

import anotations.CustomDateFormat;
import anotations.JasonValue;

import java.time.LocalDate;

public class Human {

    @JasonValue(name = "FullName")
    public String name;

    @JasonValue(name = "BirthDate")
    @CustomDateFormat(format = "dd-MM-yyyy")
    public LocalDate date;

    public Human(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }
}