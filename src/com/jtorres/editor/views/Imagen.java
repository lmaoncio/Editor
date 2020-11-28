package com.jtorres.editor.views;

import com.jtorres.editor.model.ButtonsStatus;

import java.awt.image.BufferedImage;

public class Imagen {

    private BufferedImage originalImage;
    private ButtonsStatus myButtonStatus;

    private boolean all;
    private boolean rectangle;
    private boolean brightness;
    private boolean grey;
    private boolean blue;
    private boolean green;
    private boolean red;
    private boolean filter;

    public Imagen(BufferedImage originalImage, ButtonsStatus myButtonStatus, boolean all, boolean rectangle, boolean brightness, boolean grey, boolean blue, boolean green, boolean red, boolean filter) {
        this.originalImage = originalImage;
        this.myButtonStatus = myButtonStatus;
        this.all = all;
        this.rectangle = rectangle;
        this.brightness = brightness;
        this.grey = grey;
        this.blue = blue;
        this.green = green;
        this.red = red;
        this.filter = filter;
    }
}
