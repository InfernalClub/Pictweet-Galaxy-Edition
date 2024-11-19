package cl.cosmic.utils;

import cl.cosmic.TheMain;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@UtilityClass
public class FileUtils {

    /** Read all the bytes from a file.*/
    public byte[] readAllBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            // More specific exception or logging can be added here
            throw new RuntimeException("Can't read the file: " + file.getAbsolutePath(), e);
        }
    }

    /** Get the File from the resources. */
    public File getResourceFile(@NonNull String name) {
        // Fetch the resource as a URL and convert it to a File
        var resource = TheMain.class.getClassLoader().getResource(name);

        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + name);
        }

        return new File(resource.getFile());
    }

    /** Read a file from resources as byte array */
    public byte[] readResourceFile(@NonNull String resourceName) {
        File file = getResourceFile(resourceName);
        return readAllBytes(file);
    }
}
