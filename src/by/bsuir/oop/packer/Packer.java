package by.bsuir.oop.packer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Packer {
    protected static int BUFFER = 2048;
    protected String extension = "none"; // default value

    public void compress(InputStream in, OutputStream out) throws IOException {

        byte buffer[] = new byte[BUFFER];

        int size;
        while ((size = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, size);
        }
    }

    public void decompress(InputStream in, OutputStream out) throws IOException {
        byte buffer[] = new byte[BUFFER];

        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        out.close();
    }

    public String getExtension() {
        if (extension.equals("none"))
            return "";
        else
            return "." + extension;
    }

    public String toString() {
        return Character.toUpperCase(extension.charAt(0)) + extension.substring(1);
    }
}
