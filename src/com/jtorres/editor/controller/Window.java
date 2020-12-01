package com.jtorres.editor.controller;

import com.jtorres.editor.model.ImageStatus;
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
    private final Control control = new Control();
    private final View view1;
    private final View view2;
    private final View view3;
    private final View view4;

    private final ImageStatus imageStatus1 = new ImageStatus();
    private final ImageStatus imageStatus2 = new ImageStatus();
    private final ImageStatus imageStatus3 = new ImageStatus();

    private final GridBagConstraints gbc = new GridBagConstraints();
    private BufferedImage originalImage;
    private final ImageConverter imageConverter = new ImageConverter();

    public Window() {

        view1 = new View(imageConverter);
        view2 = new View(imageConverter);
        view3 = new View(imageConverter);
        view4 = new View(imageConverter);

        imageStatus1.setAllStatus(control.getButtonStatus());
        imageStatus2.setAllStatus(control.getButtonStatus());
        imageStatus3.setAllStatus(control.getButtonStatus());
        imageStatus1.setClipStatus(control.getButtonStatus());
        imageStatus2.setClipStatus(control.getButtonStatus());
        imageStatus3.setClipStatus(control.getButtonStatus());


        setWindowLayout();
        setControlLayout();
        setVisible(true);

        control.getLoadImageBtn().addActionListener(e -> {

            setImageData();

            setViews();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize.width, screenSize.height);
        });

        control.getImage1Btn().addActionListener(e -> {
            if (imageStatus1.isStatus()) {
                control.paintSelected(imageStatus1.getClipStatus(), true);
            }
            if (!imageStatus1.isStatus()) {

                control.paintSelected(imageStatus1.getAllStatus(), false);
            }
        });

        control.getImage2Btn().addActionListener(e -> {
            if (imageStatus2.isStatus()) {
                control.paintSelected(imageStatus2.getClipStatus(), true);
            }
            if (!imageStatus2.isStatus()) {
                control.paintSelected(imageStatus2.getAllStatus(), false);
            }
        });

        control.getImage3Btn().addActionListener(e -> {
            if (imageStatus1.isStatus()) {
                control.paintSelected(imageStatus3.getClipStatus(), true);
            }
            if (!imageStatus1.isStatus()) {
                control.paintSelected(imageStatus3.getAllStatus(), false);
            }
        });

        control.getAllBtn().addActionListener(e -> {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            ButtonModel buttonModel = abstractButton.getModel();

            if (buttonModel.isSelected()) {
                if (control.getImage1Btn().isSelected()) {
                    control.paintSelected(imageStatus1.getAllStatus(), false);
                }
                if (control.getImage2Btn().isSelected()) {
                    control.paintSelected(imageStatus2.getAllStatus(), false);
                }
                if (control.getImage3Btn().isSelected()) {
                    control.paintSelected(imageStatus3.getAllStatus(), false);
                }
            }
        });

        control.getClipBtn().addActionListener(e -> {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            ButtonModel buttonModel = abstractButton.getModel();

            if (buttonModel.isSelected()) {
                if (control.getImage1Btn().isSelected()) {
                    control.paintSelected(imageStatus1.getClipStatus(), true);
                }
                if (control.getImage2Btn().isSelected()) {
                    control.paintSelected(imageStatus2.getClipStatus(), true);
                }
                if (control.getImage3Btn().isSelected()) {
                    control.paintSelected(imageStatus3.getClipStatus(), true);
                }
            }
        });

        control.getSizeJsl().addChangeListener(e -> imageSelector(control));

        control.getResetBrightnessBtn().addActionListener(e -> {
            resetBrightnessStatus();
            imageSelector(control);
        });

        control.getTotalBrightnessJsl().addChangeListener(e -> imageSelector(control));

        control.getRedJsl().addChangeListener(e -> imageSelector(control));

        control.getGreenJsl().addChangeListener(e -> imageSelector(control));

        control.getBlueJsl().addChangeListener(e -> imageSelector(control));

        control.getGreyBtn().addActionListener(e -> imageSelector(control));

        control.getFilterJsl().addChangeListener(e -> imageSelector(control));
    }

    private void imageSelector(Control control) {
        if (control.getImage1Btn().isSelected()) {
            ModeSelector(control, view2, imageStatus1);
        }
        if (control.getImage2Btn().isSelected()) {
            ModeSelector(control, view3, imageStatus2);
        }
        if (control.getImage3Btn().isSelected()) {
            ModeSelector(control, view4, imageStatus3);
        }
    }

    private void ModeSelector(Control control, View view2, ImageStatus imageStatus1) {
        if (control.getAllBtn().isSelected()) {
            view2.setAllStatus(control.getButtonStatus());
            imageStatus1.setAllStatus(control.getButtonStatus());
            imageStatus1.setStatus(false);

        }
        if (control.getClipBtn().isSelected()) {
            view2.setClipStatus(control.getButtonStatus());
            imageStatus1.setClipStatus(control.getButtonStatus());
            imageStatus1.setStatus(true);
        }
    }

    private void resetBrightnessStatus() {
        control.getTotalBrightnessJsl().setValue(0);
        control.getRedJsl().setValue(0);
        control.getGreenJsl().setValue(0);
        control.getBlueJsl().setValue(0);
        control.getResetBrightnessBtn().setSelected(false);
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

    private void setImageData() {
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
    }

    private void setViews() {
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
    }

    public void setWindowLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(control.getWidth(), control.getHeight());
        setLayout(new GridBagLayout());
        setResizable(true);
    }

}
