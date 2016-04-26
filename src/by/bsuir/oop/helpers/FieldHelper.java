package by.bsuir.oop.helpers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FieldHelper {
    private String fieldName;
    private Class fieldType;
    private Object object;

    private Method getMethod, setMethod;

    public FieldHelper (Object object, Field field) {
        this.fieldName = field.getName();
        this.fieldType = field.getType();
        this.object = object;

        try {
            String methodNameBody = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            this.getMethod = object.getClass().getMethod("get" + methodNameBody);
            this.setMethod = object.getClass().getMethod("set" + methodNameBody, fieldType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public Class getFieldType() {
        return fieldType;
    }

    public Object getFieldValue() {
        Object o = null;

        try {
            o = getMethod.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return o;
    }

    public void setFieldValue(Object arg) {
        try {
            setMethod.invoke(object, arg);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public boolean isIntField() {
        return fieldType.equals(int.class);
    }

    public boolean isStringField() {
        return fieldType.equals(String.class);
    }

    public boolean isBooleanField() {
        return fieldType.equals(boolean.class);
    }

    public boolean isEnumField() {
        return fieldType.isEnum();
    }

    public boolean isPrimitive() {
        return isBooleanField() | isIntField() | isEnumField() | isStringField();
    }
}