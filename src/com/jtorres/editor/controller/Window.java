package com.jtorres.editor.controller;

import com.jtorres.editor.model.ButtonsStatus;
import com.jtorres.editor.model.Filtro;
import com.jtorres.editor.services.ImageConverter;
import com.jtorres.editor.views.Control;
import com.jtorres.editor.views.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Window extends JFrame {

    private Control control = new Control();
    private View view;
    private GridBagConstraints gbc = new GridBagConstraints();
    private BufferedImage originalImage;
    private ImageConverter imageConverter;


    public Window() {
        imageConverter = new ImageConverter();

        view = new View(imageConverter);

        ButtonsStatus buttonsStatus = control.getButtonStatus();

        view.setAllStatus(buttonsStatus);

        setWindowLayout();
        setControlLayout();
        setViewLayout();
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

            view.addImage(View.IMAGEN1, originalImage);
            view.addImage(View.IMAGEN2, originalImage);
            view.addImage(View.IMAGEN3, originalImage);
            view.addImage(View.IMAGEN4, originalImage);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;

            add(view, gbc);
        });

        control.getImage1Btn().addActionListener(e -> {
            restoreControl(View.IMAGEN1);
        });

        control.getImage2Btn().addActionListener(e -> {
            restoreControl(View.IMAGEN2);
        });

        control.getImage3Btn().addActionListener(e -> {
            restoreControl(View.IMAGEN3);
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

    private void imageSelector(Control control) {
        if (control.getImage1Btn().isSelected()) {
            ButtonsStatus buttonsStatus = control.getButtonStatus();
            if (control.getRectanguloBtn().isSelected()) {
                view.setClipStatus(View.IMAGEN1, buttonsStatus);
            }
            if (control.getTodoBtn().isSelected()) {
                view.setImageStatus(View.IMAGEN1, buttonsStatus);
            }
        }
        if (control.getImage2Btn().isSelected()) {
            ButtonsStatus buttonsStatus = control.getButtonStatus();
            if (control.getRectanguloBtn().isSelected()) {
                view.setClipStatus(View.IMAGEN2, buttonsStatus);
            }
            if (control.getTodoBtn().isSelected()) {
                view.setImageStatus(View.IMAGEN2, buttonsStatus);
            }
        }
        if (control.getImage1Btn().isSelected()) {
            ButtonsStatus buttonsStatus = control.getButtonStatus();
            if (control.getRectanguloBtn().isSelected()) {
                view.setClipStatus(View.IMAGEN3, buttonsStatus);
            }
            if (control.getTodoBtn().isSelected()) {
                view.setImageStatus(View.IMAGEN3, buttonsStatus);
            }
        }
    }

    private void restoreControl(String imageName) {
        if (control.getTodoBtn().isSelected()) {
            control.paintSelected(view.getImageStatus(imageName));
        }
        if (control.getRectanguloBtn().isSelected()) {
            control.paintSelected(view.getClipStatus(imageName));
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
