package com.jtorres.editor.views;

import com.jtorres.editor.model.ButtonsStatus;
import com.jtorres.editor.model.Filter;
import com.jtorres.editor.services.ImageConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends Canvas {
    private BufferedImage image;
    private ButtonsStatus allStatus;
    private ButtonsStatus clipStatus;
    private final ImageConverter imageConverter;

    public View(ImageConverter imageConverter) {
        super();
        this.imageConverter = imageConverter;
    }

    public void paint(Graphics g) {
        if (image == null) {
            return;
        }
            paintImage(g);
    }

    public void paintImage(Graphics g) {
        BufferedImage image = this.image;
        BufferedImage clip;

        if (allStatus != null) {
            ButtonsStatus status = allStatus;
            image = applyFilterToImage(status, image);
        }

        if (clipStatus != null && clipStatus.isClipBtn()) {
            if (clipStatus.getSizeJsl() != 0) {
                ButtonsStatus clipStatus = this.clipStatus;

                int centreX = image.getWidth() / 2;
                int centreY = image.getHeight() / 2;
                float size = (float) clipStatus.getSizeJsl() / 10;
                int clipWidth = (int) (image.getWidth() * size);
                int clipHeight = (int) (image.getHeight() * size);
                int x1 = centreX - (clipWidth / 2);
                int y1 = centreY - (clipHeight / 2);

                clip = image.getSubimage(x1, y1, clipWidth, clipHeight);

                clip = applyFilterToImage(clipStatus, clip);

                int centreX2 = getWidth() / 2;
                int centreY2 = getHeight() / 2;
                int clipWidth2 = (int) (getWidth() * size);
                int clipHeight2 = (int) (getHeight() * size);
                int x2 = centreX2 - (clipWidth2 / 2);
                int y2 = centreY2 - (clipHeight2 / 2);

                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                g.drawImage(clip, x2 , y2 , clipWidth2, clipHeight2, this);

                return;
            }
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    public BufferedImage applyFilterToImage(ButtonsStatus buttonsStatus, BufferedImage image) {

        if (buttonsStatus.getBrightness() != 0) {
            image = imageConverter.applyBrightness(image, buttonsStatus.getBrightness());
        }

        if (buttonsStatus.getRedJsl() != 0) {
            image = imageConverter.applyRed(image, buttonsStatus.getRedJsl());
        }

        if (buttonsStatus.getGreenJsl() != 0) {
            image = imageConverter.applyGreen(image, buttonsStatus.getGreenJsl());
        }

        if (buttonsStatus.getBlueJsl() != 0) {
            image = imageConverter.applyBlue(image, buttonsStatus.getBlueJsl());
        }

        if (buttonsStatus.isGreyBtn()) {
            image = imageConverter.applyGrey(image);
        }

        if (buttonsStatus.getFilterJsl() != 0) {
            Filter sharpFilter = new Filter();
            sharpFilter.sharpFilter();

            Filter blurFilter = new Filter();
            blurFilter.blurFilter();

            if (buttonsStatus.getFilterJsl() > 0) {
                image = imageConverter.applyBlur(image, blurFilter, buttonsStatus.getFilterJsl());
            }

            if (buttonsStatus.getFilterJsl() < 0) {
                image = imageConverter.applySharp(image, sharpFilter, buttonsStatus.getFilterJsl());
            }
        }
        return image;
    }

    public void addImage(BufferedImage image) {
        this.image = image;
    }

    public void setAllStatus(ButtonsStatus status) {
        this.allStatus = status;
        repaint();
    }

    public void setClipStatus(ButtonsStatus status) {
        this.clipStatus = status;
        repaint();
    }
}
