import by.bsuir.oop.packer.Packer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipPacker extends Packer {
    private final String ENTRY_NAME = "list";

    public ZipPacker() {
        this.extension = "zip";
    }

    public void compress(InputStream in, OutputStream out) throws IOException {
        byte buffer[] = new byte[BUFFER];
        ZipOutputStream zos = new ZipOutputStream(out);
        zos.putNextEntry(new ZipEntry(ENTRY_NAME));

        int length;
        while ((length = in.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }
        zos.closeEntry();
        zos.close();
    }

    public void decompress(InputStream in, OutputStream out) throws IOException {
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry entry;

        while ((entry = zin.getNextEntry()) != null) {
            if (entry.getName().equals(ENTRY_NAME)) {
                final int BUFFER = 2048;
                byte buffer[] = new byte[BUFFER];

                int size;
                while ((size = zin.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, size);
                }

                break;
            }
        }
    }
}