package service;


import anotations.CustomDateFormat;
import anotations.JasonValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
    public String toJson(Object object) throws IllegalAccessException {
        boolean flag = false;
        Class ob = object.getClass();
        Field[] fields = ob.getDeclaredFields();
        String json = "{\n";
        for (int i = 0; i < fields.length; i++) {
            Annotation[] annotations = fields[i].getAnnotations();
            if (annotations.length == 0) {
                json += "\"" + fields[i].getName() + "\"" + " : " + "\"" + fields[i].get(object);
                if (i == fields.length - 1) {
                    json += "\"\n";
                } else {
                    json += "\",\n";
                }
            } else {

                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().equals(JasonValue.class)) {
                        JasonValue jasonValue = fields[i].getAnnotation(JasonValue.class);
                        json += "\"" + jasonValue.name() + "\"" + " : " + "\"";
                    }
                    if (annotation.annotationType().equals(CustomDateFormat.class)) {
                        LocalDate date = (LocalDate) fields[i].get(object);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fields[i].getAnnotation(CustomDateFormat.class).format());
                        String format = formatter.format(date);
                        json += format;
                        flag = true;
                    }
                }
                if (!flag) json += fields[i].get(object);

                if (i == fields.length - 1) {
                    json += "\"\n";
                } else {
                    json += "\",\n";
                }
            }
        }
        json += "}";
        return json;
    }



    public static void main(String[] args) throws IllegalAccessException {
        Human human = new Human("1", LocalDate.now());
        Converter converter = new Converter();
        String json = converter.toJson(human);
        System.out.println(json);
    }
}
