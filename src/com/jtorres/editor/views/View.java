package com.jtorres.editor.views;

import com.jtorres.editor.model.ButtonsStatus;
import com.jtorres.editor.model.Filtro;
import com.jtorres.editor.model.RectangleBound;
import com.jtorres.editor.services.ImageConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class View extends Canvas {
    public static final String IMAGEN1 = "IMAGEN1";
    public static final String IMAGEN2 = "IMAGEN2";
    public static final String IMAGEN3 = "IMAGEN3";
    public static final String IMAGEN4 = "IMAGEN4";

    private Map<String, BufferedImage> images = new HashMap<>();
    private Map<String, ButtonsStatus> imageStatuses = new HashMap<>();
    private Map<String, ButtonsStatus> clipStatuses = new HashMap<>();
    private ImageConverter imageConverter;
    
    public View(ImageConverter imageConverter) {
        super();
        this.imageConverter = imageConverter;
    }

    public void paint(Graphics g) {
        if (images.isEmpty()) {
            return;
        }

        if (images.get(IMAGEN1) != null) {
          paintImage(IMAGEN1, 0,0, g);
        }
        if (images.get(IMAGEN2) != null) {
            paintImage(IMAGEN2, getWidth()/2 ,0, g);
        }
        if (images.get(IMAGEN3) != null) {
            paintImage(IMAGEN3, 0,getHeight()/2, g);
        }
        if (images.get(IMAGEN4) != null) {
            paintImage(IMAGEN4, getWidth()/2,getHeight()/2, g);
        }
    }

    public void setAllStatus(ButtonsStatus buttonsStatus) {
        setImageStatus(IMAGEN1,buttonsStatus);
        setImageStatus(IMAGEN2,buttonsStatus);
        setImageStatus(IMAGEN3,buttonsStatus);
        setImageStatus(IMAGEN4,buttonsStatus);
        setClipStatus(IMAGEN1,buttonsStatus);
        setClipStatus(IMAGEN2,buttonsStatus);
        setClipStatus(IMAGEN3,buttonsStatus);
        setClipStatus(IMAGEN4,buttonsStatus);
    }

    public void paintImage(String imageName, int xOffset, int yOffset, Graphics g) {
        BufferedImage imagen = images.get(imageName);
        BufferedImage clip = null;

        if (imageStatuses.get(imageName) != null) {
            RectangleBound rectangleBound = new RectangleBound(0, 0, 0, 0);
            ButtonsStatus status = imageStatuses.get(imageName);
            imagen = applyFilterToImage(status, imagen, rectangleBound);
        }

        if (clipStatuses.get(imageName) != null && clipStatuses.get(imageName).isZona2Btn()) {
            ButtonsStatus clipStatus = clipStatuses.get(imageName);

            int centreX = imagen.getWidth() / 2;
            int centreY = imagen.getHeight() / 2;
            int clipWidth = (imagen.getWidth() * (clipStatus.getTamanoJsl() / 10));
            int clipHeigh = (imagen.getHeight() * (clipStatus.getTamanoJsl() / 10));
            int x1 = centreX - (clipWidth / 2);
            int y1 = centreY - (clipHeigh / 2);
            RectangleBound rectangleBound = new RectangleBound(0, 0, 0, 0);

            clip = imagen.getSubimage(x1, y1, clipWidth, clipHeigh);

            clip = applyFilterToImage(clipStatus, clip, rectangleBound);

            g.drawImage(clip, x1 + xOffset, y1 + yOffset, clipWidth, clipHeigh, this);
        }
        g.drawImage(imagen, xOffset, yOffset, getWidth() / 2, getHeight() / 2, this);
    }

    public BufferedImage applyFilterToImage(ButtonsStatus status, BufferedImage image, RectangleBound rectangleBound) {
        ButtonsStatus buttonsStatus = imageStatuses.get(IMAGEN1);

        if (buttonsStatus.getBrilloTotalJsl() != 0) {
            image = imageConverter.applyBrigthness(image, buttonsStatus.getBrilloTotalJsl(), rectangleBound.getX1(), rectangleBound.getY1(), rectangleBound.getX2(), rectangleBound.getY2());
        }

        if (buttonsStatus.getRojoJsl() != 0) {
            image = imageConverter.applyRed(image, buttonsStatus.getRojoJsl(), rectangleBound.getX1(), rectangleBound.getY1(), rectangleBound.getX2(), rectangleBound.getY2());
        }

        if (buttonsStatus.getVerdeJsl() != 0) {
            image = imageConverter.applyGreen(image, buttonsStatus.getVerdeJsl(), rectangleBound.getX1(), rectangleBound.getY1(), rectangleBound.getX2(), rectangleBound.getY2());
        }

        if (buttonsStatus.getAzulJsl() != 0) {
            image = imageConverter.applyBlue(image, buttonsStatus.getAzulJsl(), rectangleBound.getX1(), rectangleBound.getY1(), rectangleBound.getX2(), rectangleBound.getY2());
        }

        if (buttonsStatus.isGreyBtn()) {
            image = imageConverter.applyGrey(image, rectangleBound.getX1(), rectangleBound.getY1(), rectangleBound.getX2(), rectangleBound.getY2());
        }

        if (buttonsStatus.getFiltrosJsl() != 0) {
            Filtro filtroSharp = new Filtro();
            filtroSharp.filtroSharp();

            Filtro filtroBlur = new Filtro();
            filtroBlur.filtroDifuminado();

            if (buttonsStatus.getFiltrosJsl() > 0) {
                image = imageConverter.applyBlur(image, filtroBlur, buttonsStatus.getFiltrosJsl(), rectangleBound.getX1(), rectangleBound.getY1(), rectangleBound.getX2(), rectangleBound.getY2());
            }

            if (buttonsStatus.getFiltrosJsl() < 0) {
                image = imageConverter.applySharp(image, filtroSharp, buttonsStatus.getFiltrosJsl(), rectangleBound.getX1(), rectangleBound.getY1(), rectangleBound.getX2(), rectangleBound.getY2());
            }
        }
        return image;
    }

    public void addImage(String imageName, BufferedImage image) {
        images.put(imageName, image);
    }

    public ButtonsStatus getImageStatus(String imageName) {
       return imageStatuses.get(imageName);
    }

    public void setImageStatus(String imageName, ButtonsStatus status) {
        imageStatuses.put(imageName, status);
        repaint();
    }

    public ButtonsStatus getClipStatus(String imageName) {
       return imageStatuses.get(imageName);
    }

    public void setClipStatus(String imageName, ButtonsStatus status) {
        clipStatuses.put(imageName, status);
        repaint();
    }

    public void removeImage(String imageName, BufferedImage image) {
        images.remove(imageName, image);
    }

    public void removeImageStatus(String imageName, ButtonsStatus status) {
        imageStatuses.remove(imageName, status);
    }

    public void removeClipStatus(String imageName, ButtonsStatus status) {
        clipStatuses.remove(imageName, status);
    }
}
