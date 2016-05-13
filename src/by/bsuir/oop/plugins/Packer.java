package by.bsuir.oop.plugins;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Packer {
    void compress(InputStream in, OutputStream out) throws IOException;
    void decompress(InputStream in, OutputStream out) throws IOException;
}
