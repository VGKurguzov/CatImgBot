package com.saxakiil.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageCat {
    private InputStream file;
    private String nameFile;

    private CatObject getCatObj() throws IOException {
        return ConnectionCatApi.getNewCatObject();
    }

    private void setData() throws IOException {
        URL url = getCatObj().getUrl();
        this.setNewInputStream(url);
        this.setNewNameImage(url);
    }

    private void setNewInputStream (URL urlString) throws IOException {
        file = new URL(urlString.toString()).openStream();
    }

    private void setNewNameImage (URL urlString) {
        String path = urlString.toString();
        nameFile = path.substring(path.lastIndexOf("/") + 1);
    }

    public void updateImage() {
        try {
            setData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getFile() {
        if(file == null && nameFile == null)
            updateImage();
        return file;
    }

    public String getNameFile() {
        if(file == null && nameFile == null)
            updateImage();
        return nameFile;
    }

    public void closeStream() {
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
