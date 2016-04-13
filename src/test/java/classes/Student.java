package classes;

import anotations.CustomDateFormat;

import java.time.LocalDate;

public class Student {
    public String name;
    @CustomDateFormat(format = "dd-MM-yyyy")
    public LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
