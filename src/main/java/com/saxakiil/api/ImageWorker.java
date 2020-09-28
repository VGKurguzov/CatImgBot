package com.saxakiil.api;

import com.saxakiil.EnvResources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageWorker {

    protected String saveImage(URL urlString) throws IOException {
        BufferedImage image = ImageIO.read(urlString);
        if (image != null) {
            ImageIO.write(image, "jpg", new File(EnvResources.directory + urlString.getPath()));
            return EnvResources.directory + urlString.getPath();
        }
        return null;
    }

        protected void deleteImage (String path) throws IOException {
            Files.delete(Paths.get(path));

        }


}
