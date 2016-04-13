package classes;


import anotations.CustomDateFormat;
import anotations.JasonValue;
import org.junit.experimental.categories.Category;

import java.time.LocalDate;

public class Hobbit {
    public String name;
    @JasonValue(name = "birthday Hobbit")
    @CustomDateFormat(format = "dd-MM-yyyy")
    public LocalDate dateBirsday;

    public String getNamePerson() {
        return name;
    }

    public void setNamePerson(String namePerson) {
        this.name = namePerson;
    }

    public LocalDate getDateBirsday() {
        return dateBirsday;
    }

    public void setDateBirsday(LocalDate dateBirsday) {
        this.dateBirsday = dateBirsday;
    }
}
