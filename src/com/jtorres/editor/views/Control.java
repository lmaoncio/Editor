package com.jtorres.editor.views;

import com.jtorres.editor.model.ButtonsStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Control extends JPanel {

    private JButton loadImageBtn;
    private JTextArea loadImageTxt;

    private JTable dataTable;
    String[][] data;

    private ButtonGroup grupoImgBtn;
    private JTextArea imageTxt;
    private JToggleButton image1Btn;
    private JToggleButton image2Btn;
    private JToggleButton image3Btn;

    private ButtonGroup grupoZonaBtn;
    private JTextArea zonaTxt;
    private JToggleButton todoBtn;
    private JToggleButton rectanguloBtn;

    private JTextArea tamanoTxt;
    private JSlider tamanoJsl;

    private JTextArea brilloTxt;
    private JToggleButton resetBrilloBtn;

    private JTextArea brilloTotalTxt;
    private JSlider brilloTotalJsl;

    private JTextArea rojoTxt;
    private JSlider rojoJsl;

    private JTextArea verdeTxt;
    private JSlider verdeJsl;

    private JTextArea azulTxt;
    private JSlider azulJsl;

    private JTextArea greyTxt;
    private JToggleButton greyBtn;

    private JTextArea filtrosTxt;
    private JSlider filtrosJsl;
    private JTextArea focusTxt;
    private JTextArea unfocusTxt;

    private GridBagConstraints gbc;

    public Control() {
        dibujarPanel();
    }


    public void dibujarPanel() {
        gbc = new GridBagConstraints();
        setBounds(10, 10, 500, 825);
        Color backgroundColor = new Color(255, 255, 255);
        setBackground(backgroundColor);

        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(15, 15, 15, 15));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;


        loadImageTxt = new JTextArea("ARCHIVO");
        gbc.gridx = 0; // Posicion en el grid
        gbc.gridy = 0; // Posicion en el grid
        gbc.gridwidth = 1; // Ocupa dos columnas
        gbc.gridheight = 1; // Ocupa una fila
        add(loadImageTxt, gbc);

        loadImageBtn = new JButton("CARGAR");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // El área de texto ocupa dos columnas.
        gbc.gridheight = 1;
        add(loadImageBtn, gbc);

        data = new String[5][5];
        data[0][0] = "KB Totales";
        data[1][0] = "Alto";
        data[2][0] = "Ancho";
        data[3][0] = "Tipo";
        data[4][0] = "Alpha";
        String[] column = {"Descripcion", "Valor"};
        dataTable = new JTable(data, column);
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

        imageTxt = new JTextArea("IMAGEN ");
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

        grupoImgBtn = new ButtonGroup();
        grupoImgBtn.add(image1Btn);
        grupoImgBtn.add(image2Btn);
        grupoImgBtn.add(image3Btn);


        zonaTxt = new JTextArea("ZONA");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(zonaTxt, gbc);

        todoBtn = new JToggleButton("TODO");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        add(todoBtn, gbc);

        rectanguloBtn = new JToggleButton("RECUADRO");
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(rectanguloBtn, gbc);

        grupoZonaBtn = new ButtonGroup();
        grupoZonaBtn.add(todoBtn);
        grupoZonaBtn.add(rectanguloBtn);

        tamanoTxt = new JTextArea("Tamaño");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(tamanoTxt, gbc);

        tamanoJsl = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        tamanoJsl.setPaintLabels(true);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        add(tamanoJsl, gbc);

        brilloTxt = new JTextArea("BRILLO");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(brilloTxt, gbc);

        resetBrilloBtn = new JToggleButton("RESET BRILLO");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(resetBrilloBtn, gbc);

        brilloTotalTxt = new JTextArea("Total");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(brilloTotalTxt, gbc);

        brilloTotalJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        brilloTotalJsl.setPaintLabels(true);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(brilloTotalJsl, gbc);

        rojoTxt = new JTextArea("Canal Rojo");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(rojoTxt, gbc);

        rojoJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        rojoJsl.setPaintLabels(true);
        rojoJsl.setBackground(Color.red);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(rojoJsl, gbc);

        verdeTxt = new JTextArea("Canal Verde");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(verdeTxt, gbc);

        verdeJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        verdeJsl.setPaintLabels(true);
        verdeJsl.setBackground(Color.green);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(verdeJsl, gbc);

        azulTxt = new JTextArea("Canal Azul");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(azulTxt, gbc);

        azulJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        azulJsl.setPaintLabels(true);
        azulJsl.setBackground(Color.blue);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(azulJsl, gbc);

        greyTxt = new JTextArea("COLOR");
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(greyTxt, gbc);

        greyBtn = new JToggleButton("CONVERTIR A GRIS");
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(greyBtn, gbc);

        filtrosTxt = new JTextArea("FILTROS");
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(filtrosTxt, gbc);

        focusTxt = new JTextArea("focus");
        gbc.gridx = 0; // Posicion en el grid
        gbc.gridy = 13; // Posicion en el grid
        gbc.gridwidth = 1; // Ocupa dos columnas
        gbc.gridheight = 1; // Ocupa una fila
        add(focusTxt, gbc);

        filtrosJsl = new JSlider(JSlider.HORIZONTAL, -10, 10, 0);
        filtrosJsl.setPaintLabels(true);
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        add(filtrosJsl, gbc);

        unfocusTxt = new JTextArea("unfocus");
        gbc.gridx = 3; // Posicion en el grid
        gbc.gridy = 13; // Posicion en el grid
        gbc.gridwidth = 1; // Ocupa dos columnas
        gbc.gridheight = 1; // Ocupa una fila
        add(unfocusTxt, gbc);
    }

    public ButtonsStatus getButtonStatus() {
        ButtonsStatus buttonsStatus = new ButtonsStatus();

        if (this.getTodoBtn().isSelected()) {
            buttonsStatus.setTodoBtn(true);
        }

        if (this.getRectanguloBtn().isSelected()) {
            buttonsStatus.setClipBtn(true);
        }

        if (this.getTamanoJsl().getValue() != 0) {
            buttonsStatus.setTamanoJsl(this.getTamanoJsl().getValue());
        }

        if (this.getResetBrilloBtn().isSelected()) {
            buttonsStatus.setResetBrilloBtn(true);
        }

        if (this.getBrilloTotalJsl().getValue() != 0) {
            buttonsStatus.setBrilloTotalJsl(this.getBrilloTotalJsl().getValue());
        }

        if (this.getRojoJsl().getValue() != 0) {
            buttonsStatus.setRojoJsl(this.getRojoJsl().getValue());
        }

        if (this.getVerdeJsl().getValue() != 0) {
            buttonsStatus.setVerdeJsl(this.getVerdeJsl().getValue());
        }

        if (this.getAzulJsl().getValue() != 0) {
            buttonsStatus.setAzulJsl(this.getAzulJsl().getValue());
        }

        if (this.getGreyBtn().isSelected()) {
            buttonsStatus.setGreyBtn(true);
        }

        if (this.getFiltrosJsl().getValue() != 0) {
            buttonsStatus.setFiltrosJsl(this.getFiltrosJsl().getValue());
        }

        return buttonsStatus;
    }

    public void paintSelected(ButtonsStatus buttonsStatus) {
        todoBtn.setSelected(buttonsStatus.isTodoBtn());
        rectanguloBtn.setSelected(buttonsStatus.isClipBtn());
        tamanoJsl.setValue(buttonsStatus.getTamanoJsl());
        resetBrilloBtn.setSelected(buttonsStatus.isResetBrilloBtn());
        brilloTotalJsl.setValue(buttonsStatus.getBrilloTotalJsl());
        rojoJsl.setValue(buttonsStatus.getRojoJsl());
        verdeJsl.setValue(buttonsStatus.getVerdeJsl());
        azulJsl.setValue(buttonsStatus.getAzulJsl());
        greyBtn.setSelected(buttonsStatus.isGreyBtn());
        filtrosJsl.setValue(buttonsStatus.getFiltrosJsl());
        this.repaint();

    }

    public JButton getLoadImageBtn() {
        return loadImageBtn;
    }

    public void setLoadImageBtn(JButton loadImageBtn) {
        this.loadImageBtn = loadImageBtn;
    }

    public JTextArea getLoadImageTxt() {
        return loadImageTxt;
    }

    public void setLoadImageTxt(JTextArea loadImageTxt) {
        this.loadImageTxt = loadImageTxt;
    }

    public JTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(JTable dataTable) {
        this.dataTable = dataTable;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public ButtonGroup getGrupoImgBtn() {
        return grupoImgBtn;
    }

    public void setGrupoImgBtn(ButtonGroup grupoImgBtn) {
        this.grupoImgBtn = grupoImgBtn;
    }

    public JTextArea getImageTxt() {
        return imageTxt;
    }

    public void setImageTxt(JTextArea imageTxt) {
        this.imageTxt = imageTxt;
    }

    public JToggleButton getImage1Btn() {
        return image1Btn;
    }

    public void setImage1Btn(JToggleButton image1Btn) {
        this.image1Btn = image1Btn;
    }

    public JToggleButton getImage2Btn() {
        return image2Btn;
    }

    public void setImage2Btn(JToggleButton image2Btn) {
        this.image2Btn = image2Btn;
    }

    public JToggleButton getImage3Btn() {
        return image3Btn;
    }

    public void setImage3Btn(JToggleButton image3Btn) {
        this.image3Btn = image3Btn;
    }

    public ButtonGroup getGrupoZonaBtn() {
        return grupoZonaBtn;
    }

    public void setGrupoZonaBtn(ButtonGroup grupoZonaBtn) {
        this.grupoZonaBtn = grupoZonaBtn;
    }

    public JTextArea getZonaTxt() {
        return zonaTxt;
    }

    public void setZonaTxt(JTextArea zonaTxt) {
        this.zonaTxt = zonaTxt;
    }

    public JToggleButton getTodoBtn() {
        return todoBtn;
    }

    public void setTodoBtn(JToggleButton todoBtn) {
        this.todoBtn = todoBtn;
    }

    public JToggleButton getRectanguloBtn() {
        return rectanguloBtn;
    }

    public void setRectanguloBtn(JToggleButton rectanguloBtn) {
        this.rectanguloBtn = rectanguloBtn;
    }

    public JTextArea getTamanoTxt() {
        return tamanoTxt;
    }

    public void setTamanoTxt(JTextArea tamanoTxt) {
        this.tamanoTxt = tamanoTxt;
    }

    public JSlider getTamañoJsl() {
        return tamanoJsl;
    }

    public void setTamañoJsl(JSlider tamañoJsl) {
        this.tamanoJsl = tamañoJsl;
    }

    public JTextArea getBrilloTxt() {
        return brilloTxt;
    }

    public void setBrilloTxt(JTextArea brilloTxt) {
        this.brilloTxt = brilloTxt;
    }

    public JToggleButton getResetBrilloBtn() {
        return resetBrilloBtn;
    }

    public void setResetBrilloBtn(JToggleButton resetBrilloBtn) {
        this.resetBrilloBtn = resetBrilloBtn;
    }

    public JTextArea getBrilloTotalTxt() {
        return brilloTotalTxt;
    }

    public void setBrilloTotalTxt(JTextArea brilloTotalTxt) {
        this.brilloTotalTxt = brilloTotalTxt;
    }

    public JSlider getBrilloTotalJsl() {
        return brilloTotalJsl;
    }

    public void setBrilloTotalJsl(JSlider brilloTotalJsl) {
        this.brilloTotalJsl = brilloTotalJsl;
    }

    public JTextArea getRojoTxt() {
        return rojoTxt;
    }

    public void setRojoTxt(JTextArea rojoTxt) {
        this.rojoTxt = rojoTxt;
    }

    public JSlider getRojoJsl() {
        return rojoJsl;
    }

    public void setRojoJsl(JSlider rojoJsl) {
        this.rojoJsl = rojoJsl;
    }

    public JTextArea getVerdeTxt() {
        return verdeTxt;
    }

    public void setVerdeTxt(JTextArea verdeTxt) {
        this.verdeTxt = verdeTxt;
    }

    public JSlider getVerdeJsl() {
        return verdeJsl;
    }

    public void setVerdeJsl(JSlider verdeJsl) {
        this.verdeJsl = verdeJsl;
    }

    public JTextArea getAzulTxt() {
        return azulTxt;
    }

    public void setAzulTxt(JTextArea azulTxt) {
        this.azulTxt = azulTxt;
    }

    public JSlider getAzulJsl() {
        return azulJsl;
    }

    public void setAzulJsl(JSlider azulJsl) {
        this.azulJsl = azulJsl;
    }

    public JTextArea getGreyTxt() {
        return greyTxt;
    }

    public void setGreyTxt(JTextArea greyTxt) {
        this.greyTxt = greyTxt;
    }

    public JToggleButton getGreyBtn() {
        return greyBtn;
    }

    public void setGreyBtn(JToggleButton greyBtn) {
        this.greyBtn = greyBtn;
    }

    public JTextArea getFiltrosTxt() {
        return filtrosTxt;
    }

    public void setFiltrosTxt(JTextArea filtrosTxt) {
        this.filtrosTxt = filtrosTxt;
    }

    public JSlider getFiltrosJsl() {
        return filtrosJsl;
    }

    public void setFiltrosJsl(JSlider filtrosJsl) {
        this.filtrosJsl = filtrosJsl;
    }

    public JTextArea getFocusTxt() {
        return focusTxt;
    }

    public void setFocusTxt(JTextArea focusTxt) {
        this.focusTxt = focusTxt;
    }

    public JTextArea getUnfocusTxt() {
        return unfocusTxt;
    }

    public void setUnfocusTxt(JTextArea unfocusTxt) {
        this.unfocusTxt = unfocusTxt;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public void setGbc(GridBagConstraints gbc) {
        this.gbc = gbc;
    }

    public JSlider getTamanoJsl() {
        return tamanoJsl;
    }

    public void setTamanoJsl(JSlider tamanoJsl) {
        this.tamanoJsl = tamanoJsl;
    }

}

