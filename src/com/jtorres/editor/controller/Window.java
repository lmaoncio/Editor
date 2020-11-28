package com.jtorres.editor.controller;

import com.jtorres.editor.model.ButtonsStatus;
import com.jtorres.editor.model.Filtro;
import com.jtorres.editor.services.ImageConverter;
import com.jtorres.editor.views.Control;
import com.jtorres.editor.views.Imagen;
import com.jtorres.editor.views.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    private final Control control = new Control();
    private final View view = new View();
    private final GridBagConstraints gbc = new GridBagConstraints();
    private BufferedImage originalImage;
    private final ImageConverter imageConverter;

    private Imagen img2;
    private Imagen img3;
    private Imagen img4;

    private ButtonsStatus button1Status;
    private ButtonsStatus button2Status;
    private ButtonsStatus button3Status;


    public Window() {
        setWindowLayout();
        setControlLayout();
        setViewLayout();
        setVisible(true);

        button1Status = control.getButtonStatus();
        button2Status = control.getButtonStatus();
        button3Status = control.getButtonStatus();

        imageConverter = new ImageConverter();

        control.getLoadImageBtn().addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String path = f.getAbsolutePath();

            try {
                originalImage = ImageIO.read(new File(path));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            control.getData()[0][1] = f.length() / 100 + " KB";
            control.getData()[1][1] = originalImage.getHeight() + "";
            control.getData()[2][1] = originalImage.getWidth() + "";
            control.getData()[3][1] = originalImage.getType() + "";

            String alpha;
            if (originalImage.getAlphaRaster() == null) {
                alpha = "NO";
            } else {
                alpha = "YES";
            }
            control.getData()[4][1] = alpha;

            view.setBufferedImage1(originalImage);
            view.setBufferedImage2(originalImage);
            view.setBufferedImage3(originalImage);
            view.setBufferedImage4(originalImage);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;

            add(view, gbc);
        });

        control.getImage1Btn().addActionListener(e -> {
            control.paintSelected(button1Status);
        });

        control.getImage2Btn().addActionListener(e -> {
            control.paintSelected(button2Status);
        });

        control.getImage3Btn().addActionListener(e -> {
            control.paintSelected(button3Status);
        });

        control.getTodoBtn().addActionListener(e -> {
            imageSelector(control);
        });

        control.getRectanguloBtn().addActionListener(e -> {
            imageSelector(control);
        });

        control.getTamanoJsl().addChangeListener(e -> {
            imageSelector(control);
        });

        control.getResetBrilloBtn().addActionListener(e -> {
            resetBrightnessStatus();
            imageSelector(control);
        });

        control.getBrilloTotalJsl().addChangeListener(e -> {
            imageSelector(control);
        });

        control.getRojoJsl().addChangeListener(e -> {
            imageSelector(control);
        });

        control.getVerdeJsl().addChangeListener(e -> {
            imageSelector(control);
        });

        control.getAzulJsl().addChangeListener(e -> {
            imageSelector(control);
        });

        control.getGreyBtn().addActionListener(e -> {
            imageSelector(control);
        });

        control.getFiltrosJsl().addChangeListener(e -> {
            imageSelector(control);
        });

    }

    private BufferedImage filterApply() {

        ButtonsStatus buttonsStatus = control.getButtonStatus();
        BufferedImage copia = imageConverter.copyImage(originalImage);

        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;

        if (buttonsStatus.isZona2Btn()) {
            x1 = copia.getWidth() / buttonsStatus.getTamanoJsl();
            x2 = (copia.getWidth() / buttonsStatus.getTamanoJsl()) * (buttonsStatus.getTamanoJsl() - 1);
            y1 = copia.getHeight() / buttonsStatus.getTamanoJsl();
            y2 = (copia.getHeight() / buttonsStatus.getTamanoJsl()) * (buttonsStatus.getTamanoJsl() - 1);
        }

        if (buttonsStatus.isResetBrilloBtn()) {

            if (buttonsStatus.isGreyBtn()) {
                copia = imageConverter.applyGrey(copia, x1, y1, x2, y2);
            }

            if (buttonsStatus.getFiltrosJsl() != 0) {
                Filtro filtroSharp = new Filtro();
                filtroSharp.filtroSharp();

                Filtro filtroBlur = new Filtro();
                filtroBlur.filtroDifuminado();

                if (buttonsStatus.getFiltrosJsl() > 0) {
                    copia = imageConverter.applyBlur(copia, filtroBlur, buttonsStatus.getFiltrosJsl(), x1, y1, x2, y2);
                }

                if (buttonsStatus.getFiltrosJsl() < 0) {
                    copia = imageConverter.applySharp(copia, filtroSharp, buttonsStatus.getFiltrosJsl(), x1, y1, x2, y2);
                }
            }

            control.getResetBrilloBtn().setSelected(false);
            return copia;
        }

        if (buttonsStatus.getBrilloTotalJsl() != 0) {
            copia = imageConverter.applyBrigthness(copia, buttonsStatus.getBrilloTotalJsl(), x1, y1, x2, y2);
        }

        if (buttonsStatus.getRojoJsl() != 0) {
            copia = imageConverter.applyRed(copia, buttonsStatus.getRojoJsl(), x1, y1, x2, y2);
        }

        if (buttonsStatus.getVerdeJsl() != 0) {
            copia = imageConverter.applyGreen(copia, buttonsStatus.getVerdeJsl(), x1, y1, x2, y2);
        }

        if (buttonsStatus.getAzulJsl() != 0) {
            copia = imageConverter.applyBlue(copia, buttonsStatus.getAzulJsl(), x1, y1, x2, y2);

        }

        if (buttonsStatus.isGreyBtn()) {
                copia = imageConverter.applyGrey(copia, x1, y1, x2, y2);
        }

        if (buttonsStatus.getFiltrosJsl() != 0) {
            Filtro filtroSharp = new Filtro();
            filtroSharp.filtroSharp();

            Filtro filtroBlur = new Filtro();
            filtroBlur.filtroDifuminado();

            if (buttonsStatus.getFiltrosJsl() > 0) {
                copia = imageConverter.applyBlur(copia, filtroBlur, buttonsStatus.getFiltrosJsl(), x1, y1, x2, y2);
            }

            if (buttonsStatus.getFiltrosJsl() < 0) {
                copia = imageConverter.applySharp(copia, filtroSharp, buttonsStatus.getFiltrosJsl(), x1, y1, x2, y2);
            }
        }

        return copia;


    }

    private void imageSelector(Control control) {
        if (control.getImage1Btn().isSelected()) {
            button1Status = control.getButtonStatus();
            BufferedImage imagenConverted = filterApply();
            view.setBufferedImage2(imagenConverted);
        }

        if (control.getImage2Btn().isSelected()) {
            button2Status = control.getButtonStatus();
            BufferedImage imagenConverted = filterApply();
            view.setBufferedImage3(imagenConverted);
        }

        if (control.getImage3Btn().isSelected()) {
            button3Status = control.getButtonStatus();
            BufferedImage imagenConverted = filterApply();
            view.setBufferedImage4(imagenConverted);
        }

        view.repaint();

    }

    private void resetBrightnessStatus() {
        control.getBrilloTotalJsl().setValue(0);
        control.getRojoJsl().setValue(0);
        control.getVerdeJsl().setValue(0);
        control.getAzulJsl().setValue(0);
        control.getResetBrilloBtn().setSelected(false);

    }

    public void setControlLayout() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;

        add(control, gbc);
    }

    public void setViewLayout() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;

        add(view, gbc);
    }

    public void setWindowLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLayout(new GridBagLayout());
        setResizable(true);
    }

}
