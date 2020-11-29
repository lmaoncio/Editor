package com.jtorres.editor.model;
public class ButtonsStatus {
    private boolean todoBtn;
    private boolean clipBtn;
    private int tamanoJsl;
    private boolean resetBrilloBtn;
    private int brilloTotalJsl;

    public boolean isTodoBtn() {
        return todoBtn;
    }

    public void setTodoBtn(boolean todoBtn) {
        this.todoBtn = todoBtn;
    }

    public boolean isClipBtn() {
        return clipBtn;
    }

    public void setClipBtn(boolean clipBtn) {
        this.clipBtn = clipBtn;
    }

    private int rojoJsl;
    private int verdeJsl;
    private int azulJsl;
    private boolean greyBtn;
    private int filtrosJsl;

    public boolean isResetBrilloBtn() {
        return resetBrilloBtn;
    }

    public void setResetBrilloBtn(boolean resetBrilloBtn) {
        this.resetBrilloBtn = resetBrilloBtn;
    }


    public int getTamanoJsl() {
        return tamanoJsl;
    }

    public void setTamanoJsl(int tamanoJsl) {
        this.tamanoJsl = tamanoJsl;
    }

    public int getBrilloTotalJsl() {
        return brilloTotalJsl;
    }

    public void setBrilloTotalJsl(int brilloTotalJsl) {
        this.brilloTotalJsl = brilloTotalJsl;
    }

    public int getRojoJsl() {
        return rojoJsl;
    }

    public void setRojoJsl(int rojoJsl) {
        this.rojoJsl = rojoJsl;
    }

    public int getVerdeJsl() {
        return verdeJsl;
    }

    public void setVerdeJsl(int verdeJsl) {
        this.verdeJsl = verdeJsl;
    }

    public int getAzulJsl() {
        return azulJsl;
    }

    public void setAzulJsl(int azulJsl) {
        this.azulJsl = azulJsl;
    }

    public void setGreyBtn(boolean greyBtn) {
        this.greyBtn = greyBtn;
    }

    public int getFiltrosJsl() {
        return filtrosJsl;
    }

    public boolean isGreyBtn() {
        return greyBtn;
    }

    public void setFiltrosJsl(int filtrosJsl) {
        this.filtrosJsl = filtrosJsl;
    }
}
