package com.jtorres.editor.views;

import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends Canvas {
    private BufferedImage bufferedImage1;
    private BufferedImage bufferedImage2;
    private BufferedImage bufferedImage3;
    private BufferedImage bufferedImage4;

    public void paint(Graphics g) {
        if (bufferedImage1 == null || bufferedImage2 == null || bufferedImage3 == null || bufferedImage4 == null) {
            return;
        }

        g.drawImage(bufferedImage1, 0, 0, getWidth() / 2, getHeight() / 2, this);
        g.drawImage(bufferedImage2, getWidth() / 2, 0, getWidth() / 2, getHeight() / 2, this);
        g.drawImage(bufferedImage3, 0, getHeight() / 2, getWidth() / 2, getHeight() / 2, this);
        g.drawImage(bufferedImage4, getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2, this);

    }


    public BufferedImage getBufferedImage1() {
        return bufferedImage1;
    }

    public void setBufferedImage1(BufferedImage bufferedImage1) {
        this.bufferedImage1 = bufferedImage1;
    }

    public BufferedImage getBufferedImage2() {
        return bufferedImage2;
    }

    public void setBufferedImage2(BufferedImage bufferedImage2) {
        this.bufferedImage2 = bufferedImage2;
    }

    public BufferedImage getBufferedImage3() {
        return bufferedImage3;
    }

    public void setBufferedImage3(BufferedImage bufferedImage3) {
        this.bufferedImage3 = bufferedImage3;
    }

    public BufferedImage getBufferedImage4() {
        return bufferedImage4;
    }

    public void setBufferedImage4(BufferedImage bufferedImage4) {
        this.bufferedImage4 = bufferedImage4;
    }
}
