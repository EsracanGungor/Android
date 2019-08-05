package com.esragungor.landmarkbook;

import android.graphics.Bitmap;

public class Globals {
    private static Globals instance;
    private Bitmap selectedImage;

    private Globals(){

    }
    public void setData(Bitmap selectedImage){
        this.selectedImage=selectedImage;
    }
    public Bitmap getData(){
        return selectedImage;
    }

    public static Globals getInstance(){
        if (instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
