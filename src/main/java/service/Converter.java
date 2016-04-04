package service;


import anotations.JasonValue;
import sun.net.www.http.Hurryable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Converter {
    public String toJson(Object object) throws IllegalAccessException {
        Class ob = object.getClass();
        Field[] fields = ob.getDeclaredFields();
        //Annotation[] annotations = ob.getAnnotations();
        String string = "{\n";

        for (int i=0; i< fields.length; i++) {
            string += "\"" + fields[i].getName() + "\"" + " : " + "\"" + fields[i].get(object);
            if (i == fields.length-1) {
                string +="\"\n";
            } else {
                string += "\",\n";
            }

        }
        string += "}";
        System.out.println(string);
        return "";
    }

    public static void main(String[] args) throws IllegalAccessException {
        Human human = new Human("1", "2");
        Converter converter = new Converter();
        converter.toJson(human);
    }
}
