package by.bsuir.oop.serializers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLSerializer implements Serializer {

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
