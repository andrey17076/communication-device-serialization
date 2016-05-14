package by.bsuir.oop.serializers;

import by.bsuir.oop.packer.Packer;

import java.io.*;

public class SerializerProxy implements Serializer {

    Packer packer;
    Serializer serializer;

    public SerializerProxy(Packer packer, Serializer serializer) {
        this.packer = packer;
        this.serializer = serializer;
    }

    @Override
    public void serialize(Object o, OutputStream outputStream) {
        ByteArrayOutputStream tmpOutputStream = new ByteArrayOutputStream();
        serializer.serialize(o, tmpOutputStream);
        try {
            packer.compress(new ByteArrayInputStream(tmpOutputStream.toByteArray()), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(InputStream inputStream) {

        ByteArrayOutputStream tmpOutputStream = new ByteArrayOutputStream();

        try {
            packer.decompress(inputStream, tmpOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return serializer.deserialize(new ByteArrayInputStream(tmpOutputStream.toByteArray()));
    }
}
