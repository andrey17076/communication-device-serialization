package by.bsuir.oop.serializers;

import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {
    void serialize(Object o, OutputStream outputStream);
    Object deserialize(InputStream inputStream);
}
