package service;

import classes.*;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;

public class ConverterTest {

    @Test
    public void testToJson() throws IllegalAccessException {
        Converter converter = new Converter();

        String expectedJson = "{\n\"namePerson\" : \"Evgen\",\n\"dateBirsday\" : \"2016-04-13\"\n}";

        Person person = new Person();
        person.setNamePerson("Evgen");
        person.setDateBirsday(LocalDate.now());

        String actualJson = converter.toJson(person);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testJasonValueAnnotation() throws IllegalAccessException {
        Converter conv = new Converter();

        String expectedJson = "{\n\"Name\" : \"Evgen\",\n\"date\" : \"2016-04-13\"\n}";

        Human human = new Human();
        human.setFirstName("Evgen");
        human.setDate(LocalDate.now());

        String actualJson = conv.toJson(human);


        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testCustomDateAnnotation() throws IllegalAccessException {
        Converter converter = new Converter();

        String expectedJson = "{\n\"name\" : \"Petro\",\n\"date\" : \"13-04-2016\"\n}";

        Student student = new Student();
        student.setName("Petro");
        student.setDate(LocalDate.now());

        String actualJson = converter.toJson(student);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testJsonValueAndCustomDateFormatAnnotation() throws IllegalAccessException {
        Converter converter = new Converter();

        String expectedJson = "{\n\"name\" : \"Bilbo\",\n\"birthday Hobbit\" : \"13-04-2016\"\n}";

        Hobbit hobbit = new Hobbit();
        hobbit.setNamePerson("Bilbo");
        hobbit.setDateBirsday(LocalDate.now());

        String actualJson = converter.toJson(hobbit);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testFromJson() throws IllegalAccessException, IOException, InstantiationException {
        Converter converter = new Converter();

        String expectedJson = "{\n\"brand\" : \"opel\",\n\"type\" : \"3\"\n}";

        Car car = converter.fromJson(expectedJson, Car.class);

        assertEquals(car.getBrand(), "opel");
        assertEquals(car.getType(), "3");
    }

}