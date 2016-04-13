package service;

import anotations.CustomDateFormat;
import anotations.JasonValue;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Converter {

    public String toJson(Object object) throws IllegalAccessException {

        boolean flag = false;
        boolean flag2 = false;
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
                        flag2 = true;
                    } else{
                        if (!flag2) json += "\"" + fields[i].getName() + "\"" + " : " + "\"";
                    }
                    if (annotation.annotationType().equals(CustomDateFormat.class)) {
                        flag = true;
                        LocalDate date = (LocalDate) fields[i].get(object);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fields[i].getAnnotation(CustomDateFormat.class).format());
                        String format = formatter.format(date);
                        json += format;

                    }
                }
                if (!flag) {
                    json += fields[i].get(object);
                }

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

    public <T> T fromJson(String json, Class<T> param) throws IOException, IllegalAccessException, InstantiationException {

        Map<String, String> mapRes = new ObjectMapper().readValue(
                json, new TypeReference<HashMap<String, String>>() {
                });
        Object object = param.newInstance();

        Field[] fields = param.getDeclaredFields();
        for (Field field : fields) {
            String fieldValue = mapRes.get(field.getName());
            if (fieldValue != null) {
                field.setAccessible(true);
                field.set(object, fieldValue);
                field.setAccessible(false);
            }
        }
        return (T) object;
    }

}
