package com.jtorres.editor.views;

import com.jtorres.editor.model.ButtonsStatus;
import com.jtorres.editor.model.Filtro;
import com.jtorres.editor.services.ImageConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends Canvas {
    private BufferedImage image;

    private ButtonsStatus imageStatus = new ButtonsStatus();
    private ButtonsStatus clipStatus = new ButtonsStatus();
    private ImageConverter imageConverter;

    public View(ImageConverter imageConverter) {
        super();
        this.imageConverter = imageConverter;
    }

    public void paint(Graphics g) {
        if (image == null) {
            return;
        }

        if (image != null) {
            paintImage(g);
        }
    }

    public void paintImage(Graphics g) {
        BufferedImage imagen = image;
        BufferedImage clip = null;

        if (imageStatus != null) {
            ButtonsStatus status = imageStatus;
            imagen = applyFilterToImage(status, imagen);
        }

        if (clipStatus != null && clipStatus.isClipBtn()) {
            if (clipStatus.getTamanoJsl() != 0) {
                ButtonsStatus clipStatus = this.clipStatus;

                int centreX = imagen.getWidth() / 2;
                int centreY = imagen.getHeight() / 2;
                float size = (float) clipStatus.getTamanoJsl() / 10;
                int clipWidth = (int) (imagen.getWidth() * size);
                int clipHeight = (int) (imagen.getHeight() * size);
                int x1 = centreX - (clipWidth / 2);
                int y1 = centreY - (clipHeight / 2);

                clip = imagen.getSubimage(x1, y1, clipWidth, clipHeight);

                clip = applyFilterToImage(clipStatus, clip);

                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

                int centreX2 = getWidth() / 2;
                int centreY2 = getHeight() / 2;
                int clipWidth2 = (int) (getWidth() * size);
                int clipHeight2 = (int) (getHeight() * size);
                int x2 = centreX2 - (clipWidth2 / 2);
                int y2 = centreY2 - (clipHeight2 / 2);

                g.drawImage(clip, x2 , y2 , clipWidth2, clipHeight2, this);

                return;

            }
        }

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }

    public BufferedImage applyFilterToImage(ButtonsStatus buttonsStatus, BufferedImage image) {

        if (buttonsStatus.getBrilloTotalJsl() != 0) {
            image = imageConverter.applyBrigthness(image, buttonsStatus.getBrilloTotalJsl());
        }

        if (buttonsStatus.getRojoJsl() != 0) {
            image = imageConverter.applyRed(image, buttonsStatus.getRojoJsl());
        }

        if (buttonsStatus.getVerdeJsl() != 0) {
            image = imageConverter.applyGreen(image, buttonsStatus.getVerdeJsl());
        }

        if (buttonsStatus.getAzulJsl() != 0) {
            image = imageConverter.applyBlue(image, buttonsStatus.getAzulJsl());
        }

        if (buttonsStatus.isGreyBtn()) {
            image = imageConverter.applyGrey(image);
        }

        if (buttonsStatus.getFiltrosJsl() != 0) {
            Filtro filtroSharp = new Filtro();
            filtroSharp.filtroSharp();

            Filtro filtroBlur = new Filtro();
            filtroBlur.filtroDifuminado();

            if (buttonsStatus.getFiltrosJsl() > 0) {
                image = imageConverter.applyBlur(image, filtroBlur, buttonsStatus.getFiltrosJsl());
            }

            if (buttonsStatus.getFiltrosJsl() < 0) {
                image = imageConverter.applySharp(image, filtroSharp, buttonsStatus.getFiltrosJsl());
            }
        }
        return image;
    }

    public void addImage(BufferedImage image) {
        this.image = image;
    }

    public ButtonsStatus getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(ButtonsStatus status) {
        this.imageStatus = status;
        repaint();
    }

    public ButtonsStatus getClipStatus() {
        return imageStatus;
    }

    public void setClipStatus(ButtonsStatus status) {
        this.clipStatus = status;
        repaint();
    }
}
