package classes;

import anotations.JasonValue;

import java.time.LocalDate;

public class Human {

    @JasonValue(name = "Name")
    public String firstName;
    public LocalDate date;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
