package by.bsuir.oop.serializers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLSerializer implements Serializer {

    private static XMLSerializer instance;

    private XMLSerializer() {}

    public static XMLSerializer getInstance() {
        if (instance == null) {
            instance = new XMLSerializer();
        }
        return instance;
    }

    @Override
    public void serialize(Object o, OutputStream outputStream){
        XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
        xmlEncoder.writeObject(o);
        xmlEncoder.close();
    }

    @Override
    public Object deserialize(InputStream inputStream) {
        XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
        Object o = xmlDecoder.readObject();
        xmlDecoder.close();
        return o;
    }
}
