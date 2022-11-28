package com.ubivashka.openstack.api.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtil {
    private ResourceUtil() {
    }

    public static void createResource(String resourceName) {
        if (!resourceName.startsWith("/"))
            resourceName = "/" + resourceName;
        try (InputStream resourceStream = ResourceUtil.class.getResourceAsStream(resourceName)) {
            if (resourceStream == null)
                throw new IllegalArgumentException("Resource with name " + resourceName + " not exists!");
            Path jarFolder = Paths.get("." + resourceName.replace("/", File.separator));
            if (!Files.exists(jarFolder))
                Files.copy(resourceStream, jarFolder);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
