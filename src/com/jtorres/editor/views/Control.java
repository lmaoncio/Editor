package com.jtorres.editor.views;

import com.jtorres.editor.model.ButtonsStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Control extends JPanel {

    private JButton loadImageBtn;
    String[][] data;
    private JToggleButton image1Btn;
    private JToggleButton image2Btn;
    private JToggleButton image3Btn;
    private JToggleButton allBtn;
    private JToggleButton clipBtn;
    private JSlider sizeJsl;
    private JToggleButton resetBrightnessBtn;
    private JSlider totalBrightnessJsl;
    private JSlider redJsl;
    private JSlider greenJsl;
    private JSlider blueJsl;
    private JToggleButton greyBtn;
    private JSlider filterJsl;

    public Control() {
        drawPanel();
    }

    public void drawPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(500, screenSize.height);
        Color backgroundColor = new Color(255, 255, 255);
        setBackground(backgroundColor);

        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(15, 15, 15, 15));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;

        JTextArea loadImageTxt = new JTextArea("FILE");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(loadImageTxt, gbc);

        loadImageBtn = new JButton("LOAD");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // El área de texto ocupa dos columnas.
        gbc.gridheight = 1;
        add(loadImageBtn, gbc);

        data = new String[5][5];
        data[0][0] = "KB Total";
        data[1][0] = "Height";
        data[2][0] = "Width";
        data[3][0] = "Type";
        data[4][0] = "Alpha";
        String[] column = {"Description", "Valor"};
        JTable dataTable = new JTable(data, column);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        add(dataTable, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        add(dataTable.getTableHeader(), gbc);

        JTextArea imageTxt = new JTextArea("IMAGE ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        imageTxt.setEnabled(true);
        add(imageTxt, gbc);

        image1Btn = new JToggleButton("1");
        image1Btn.setSelected(true);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        image1Btn.setEnabled(true);
        add(image1Btn, gbc);

        image2Btn = new JToggleButton("2");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        image2Btn.setEnabled(true);
        add(image2Btn, gbc);

        image3Btn = new JToggleButton("3");
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        image3Btn.setEnabled(true);
        add(image3Btn, gbc);

        ButtonGroup groupImgBtn = new ButtonGroup();
        groupImgBtn.add(image1Btn);
        groupImgBtn.add(image2Btn);
        groupImgBtn.add(image3Btn);


        JTextArea zoneTxt = new JTextArea("MODE");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(zoneTxt, gbc);

        allBtn = new JToggleButton("ALL");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        allBtn.setSelected(true);
        add(allBtn, gbc);

        clipBtn = new JToggleButton("CLIP");
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(clipBtn, gbc);

        ButtonGroup groupZoneBtn = new ButtonGroup();
        groupZoneBtn.add(allBtn);
        groupZoneBtn.add(clipBtn);

        JTextArea sizeTxt = new JTextArea("Size");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(sizeTxt, gbc);

        sizeJsl = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        sizeJsl.setPaintLabels(true);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        add(sizeJsl, gbc);

        JTextArea brightnessTxt = new JTextArea("BRIGHTNESS");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(brightnessTxt, gbc);

        resetBrightnessBtn = new JToggleButton("RESET BRIGHTNESS");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(resetBrightnessBtn, gbc);

        JTextArea totalBrightnessTxt = new JTextArea("Total");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(totalBrightnessTxt, gbc);

        totalBrightnessJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        totalBrightnessJsl.setPaintLabels(true);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(totalBrightnessJsl, gbc);

        JTextArea redTxt = new JTextArea("Red");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(redTxt, gbc);

        redJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        redJsl.setPaintLabels(true);
        redJsl.setBackground(Color.red);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(redJsl, gbc);

        JTextArea greenTxt = new JTextArea("Green");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(greenTxt, gbc);

        greenJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        greenJsl.setPaintLabels(true);
        greenJsl.setBackground(Color.green);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(greenJsl, gbc);

        JTextArea blueTxt = new JTextArea("Blue");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(blueTxt, gbc);

        blueJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        blueJsl.setPaintLabels(true);
        blueJsl.setBackground(Color.blue);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(blueJsl, gbc);

        JTextArea greyTxt = new JTextArea("COLOR");
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(greyTxt, gbc);

        greyBtn = new JToggleButton("GREY CONVERTER");
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(greyBtn, gbc);

        JTextArea filterTxt = new JTextArea("FILTERS");
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(filterTxt, gbc);

        JTextArea sharpTxt = new JTextArea("Blur");
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(sharpTxt, gbc);

        filterJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        filterJsl.setPaintLabels(true);
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        add(filterJsl, gbc);

        JTextArea blurTxt = new JTextArea("Sharp");
        gbc.gridx = 3;
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(blurTxt, gbc);
    }

    public ButtonsStatus getButtonStatus() {
        ButtonsStatus buttonsStatus = new ButtonsStatus();

        if (this.getAllBtn().isSelected()) {
            buttonsStatus.setAllBtn(true);
        }

        if (this.getClipBtn().isSelected()) {
            buttonsStatus.setClipBtn(true);
        }

        if (this.getSizeJsl().getValue() != 0) {
            buttonsStatus.setSizeJsl(this.getSizeJsl().getValue());
        }

        if (this.getResetBrightnessBtn().isSelected()) {
            buttonsStatus.setResetBrightness(true);
        }

        if (this.getTotalBrightnessJsl().getValue() != 0) {
            buttonsStatus.setBrightness(this.getTotalBrightnessJsl().getValue());
        }

        if (this.getRedJsl().getValue() != 0) {
            buttonsStatus.setRedJsl(this.getRedJsl().getValue());
        }

        if (this.getGreenJsl().getValue() != 0) {
            buttonsStatus.setGreenJsl(this.getGreenJsl().getValue());
        }

        if (this.getBlueJsl().getValue() != 0) {
            buttonsStatus.setBlueJsl(this.getBlueJsl().getValue());
        }

        if (this.getGreyBtn().isSelected()) {
            buttonsStatus.setGreyBtn(true);
        }

        if (this.getFilterJsl().getValue() != 0) {
            buttonsStatus.setFilterJsl(this.getFilterJsl().getValue());
        }

        return buttonsStatus;
    }

    public void paintSelected(ButtonsStatus buttonsStatus, boolean activated) {
        if (activated) {
            clipBtn.setSelected(true);
            allBtn.setSelected(false);
        } else {
            clipBtn.setSelected(false);
            allBtn.setSelected(true);
        }
        sizeJsl.setValue(buttonsStatus.getSizeJsl());
        resetBrightnessBtn.setSelected(buttonsStatus.isResetBrightness());
        totalBrightnessJsl.setValue(buttonsStatus.getBrightness());
        redJsl.setValue(buttonsStatus.getRedJsl());
        greenJsl.setValue(buttonsStatus.getGreenJsl());
        blueJsl.setValue(buttonsStatus.getBlueJsl());
        greyBtn.setSelected(buttonsStatus.isGreyBtn());
        filterJsl.setValue(buttonsStatus.getFilterJsl());

        repaint();
    }

    public JButton getLoadImageBtn() {
        return loadImageBtn;
    }

    public String[][] getData() {
        return data;
    }

    public JToggleButton getImage1Btn() {
        return image1Btn;
    }

    public JToggleButton getImage2Btn() {
        return image2Btn;
    }

    public JToggleButton getImage3Btn() {
        return image3Btn;
    }

    public JToggleButton getAllBtn() {
        return allBtn;
    }

    public JToggleButton getClipBtn() {
        return clipBtn;
    }

    public JToggleButton getResetBrightnessBtn() {
        return resetBrightnessBtn;
    }

    public JSlider getTotalBrightnessJsl() {
        return totalBrightnessJsl;
    }

    public JSlider getRedJsl() {
        return redJsl;
    }

    public JSlider getGreenJsl() {
        return greenJsl;
    }

    public JSlider getBlueJsl() {
        return blueJsl;
    }

    public JToggleButton getGreyBtn() {
        return greyBtn;
    }

    public JSlider getFilterJsl() {
        return filterJsl;
    }

    public JSlider getSizeJsl() {
        return sizeJsl;
    }

}

