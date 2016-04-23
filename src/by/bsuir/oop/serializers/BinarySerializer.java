package by.bsuir.oop.serializers;

import java.io.*;

public class BinarySerializer implements Serializer {

    @Override
    public void serialize(Object o, OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }

    @Override
    public Object deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        Object o;
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        o = objectInputStream.readObject();
        objectInputStream.close();
        return o;
    }
}
