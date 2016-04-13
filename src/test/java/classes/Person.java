package classes;

import java.time.LocalDate;

public class Person {
    public String namePerson;
    public LocalDate dateBirsday;

    public LocalDate getDateBirsday() {
        return dateBirsday;
    }

    public void setDateBirsday(LocalDate dateBirsday) {
        this.dateBirsday = dateBirsday;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }
}
