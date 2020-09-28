package com.saxakiil.api;

import java.io.IOException;

public class ImageCat extends ImageWorker{
    private String path;

    private CatObject getCatObj() throws IOException {
        return ConnectionCatApi.getNewCatObject();
    }

    private void setCatPath() throws IOException {
        path = this.saveImage(getCatObj().getUrl());
    }

    public String getPath() {
        if(path == null) {
            try {
                setCatPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    public void deleteFile(){
        if(path != null) {
            try {
                this.deleteImage(this.path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
