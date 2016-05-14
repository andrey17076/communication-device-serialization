package by.bsuir.oop.packer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackerLoader {

    public static String pluginsDir = "plugins";

    public static ArrayList<Packer> getPackers() {
        File pluginsFolder = new File(pluginsDir);
        File[] jarFiles = pluginsFolder.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
        ArrayList<Packer> packers = new ArrayList<>();

        for (File file : jarFiles) {
            try {
                JarFile jarFile = new JarFile(file.getPath());
                Enumeration<JarEntry> entries = jarFile.entries();

                URL[] urls = { new URL("jar:file:" + file.getPath() +"!/") };
                URLClassLoader cl = URLClassLoader.newInstance(urls);

                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();

                    String entryName = entry.getName();
                    String className = entryName.replace(".class", ""); //TODO
                    className = className.replace('/', '.');
                    Class c = cl.loadClass(className);
                    Packer instance = (Packer) c.newInstance();
                    packers.add(instance);
                }

            } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return packers;
    }
}
