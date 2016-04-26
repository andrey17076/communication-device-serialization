package by.bsuir.oop.serializers;

import by.bsuir.oop.helpers.FieldHelper;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextSerializer implements Serializer {

    private static HashMap<Class<?>, String> PRIMITIVE_REPRESENTATION = new HashMap<>();

    static {
        PRIMITIVE_REPRESENTATION.put(boolean.class, Boolean.class.getName());
        PRIMITIVE_REPRESENTATION.put(int.class, Integer.class.getName());
    }

    private static int nestingLevel;

    @Override
    public void serialize(Object o, OutputStream outputStream) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(outputStream, "UTF-8");
            nestingLevel = 0;
            writeObject(o, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String inputString = scanner.next();
        inputString = inputString.replaceAll("\\s", " ");
        StringTokenizer stringTokenizer = new StringTokenizer(inputString, " ");
        String token = stringTokenizer.nextToken();

        if (token.equals("ARRAY")) {
            int quantity = Integer.parseInt(stringTokenizer.nextToken());
            Object[] deviceArray = new Object[quantity];

            for (int i = 0; i < quantity; i++) {
                deviceArray[i] = readObject(stringTokenizer);
            }

            return deviceArray;
        } else {
            return null;
        }
    }

    public Object readObject(StringTokenizer stringTokenizer) {

        Object object = null;

        try {
            String className = stringTokenizer.nextToken();
            Class cls = Class.forName(className);
            object = cls.newInstance();
            Field[] fields = cls.getFields();

            for (Field field : fields) {
                String fieldName = stringTokenizer.nextToken();
                FieldHelper fieldHelper = new FieldHelper(object, field);

                Object fieldValue = null;

                if (fieldHelper.isPrimitive()) {
                    String fieldTypeName = stringTokenizer.nextToken();
                    Class<?> fieldClass = Class.forName(fieldTypeName);
                    String fieldValueString = stringTokenizer.nextToken();
                    if (!fieldValueString.equals("null")) {
                        if (fieldHelper.isStringField()) {
                            fieldValue = fieldValueString;
                        } else {
                            Method valueOf = fieldClass.getMethod("valueOf", String.class);
                            fieldValue = valueOf.invoke(null, fieldValueString);
                        }
                    }
                } else {
                    fieldValue = readObject(stringTokenizer);
                }

                fieldHelper.setFieldValue(fieldValue);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return object;
    }


    public static void writeObject(Object object, OutputStreamWriter out) {

        Class objectType = object.getClass();

        if (objectType.isArray()) {
            appendFormat("ARRAY  " + Array.getLength(object) + '\n', out);
            nestingLevel++;
            for (int i = 0; i < Array.getLength(object); i++) {
                writeObject(Array.get(object, i), out);
            }
            nestingLevel--;
        } else {
            Field[] fields = objectType.getFields();
            appendFormat(objectType.getName() + '\n', out);
            nestingLevel++;
            for (Field field : fields) {
                FieldHelper fieldHelper = new FieldHelper(object, field);
                appendFormat(field.getName(), out);

                Object fieldValue = fieldHelper.getFieldValue();

                if (fieldValue == null)
                    fieldValue = "null";

                String fieldTypeString = (fieldHelper.getFieldType().isPrimitive())
                        ? PRIMITIVE_REPRESENTATION.get(fieldHelper.getFieldType())
                        : fieldHelper.getFieldType().getName();

                if (fieldHelper.isPrimitive()) {
                    appendFormat(fieldTypeString + "  " + fieldValue.toString() + '\n', out);
                } else {
                    writeObject(fieldValue, out);
                }
            }
            nestingLevel--;
        }
    }


    public static void appendFormat(String line, OutputStreamWriter out) {
        try {
            for (int i = 0; i < nestingLevel; i++) { out.append('\t'); }
            out.append(line).append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
