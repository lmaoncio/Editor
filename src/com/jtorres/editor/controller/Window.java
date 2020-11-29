package com.jtorres.editor.controller;

import com.jtorres.editor.model.ButtonsStatus;
import com.jtorres.editor.services.ImageConverter;
import com.jtorres.editor.views.Control;
import com.jtorres.editor.views.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    private Control control = new Control();
    private View view1;
    private View view2;
    private View view3;
    private View view4;

    private GridBagConstraints gbc = new GridBagConstraints();
    private BufferedImage originalImage;
    private ImageConverter imageConverter;


    public Window() {

        imageConverter = new ImageConverter();

        view1 = new View(imageConverter);
        view2 = new View(imageConverter);
        view3 = new View(imageConverter);
        view4 = new View(imageConverter);

        setWindowLayout();
        setControlLayout();
        setVisible(true);

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

            view1.addImage(originalImage);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            add(view1, gbc);

            view2.addImage(imageConverter.copyImage(originalImage));

            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            add(view2, gbc);

            view3.addImage(imageConverter.copyImage(originalImage));

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            add(view3, gbc);

            view4.addImage(imageConverter.copyImage(originalImage));

            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            add(view4, gbc);
        });

        control.getImage1Btn().addActionListener(e -> {

        });

        control.getImage2Btn().addActionListener(e -> {

        });

        control.getImage3Btn().addActionListener(e -> {

        });

        control.getTodoBtn().addActionListener(e -> {


        });

        control.getRectanguloBtn().addActionListener(e -> {

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

    private void imageSelector(Control control) {
        if (control.getImage1Btn().isSelected()) {
            if (control.getTodoBtn().isSelected()) {
                ButtonsStatus buttonsStatus = control.getButtonStatus();
                view2.setImageStatus(buttonsStatus);
            }
            if (control.getRectanguloBtn().isSelected()) {
                ButtonsStatus buttonsStatus = control.getButtonStatus();
                view2.setClipStatus(buttonsStatus);
            }

        }
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
        gbc.gridheight = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 1;

        add(control, gbc);
    }

    public void setWindowLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLayout(new GridBagLayout());
        setResizable(true);
    }

}
