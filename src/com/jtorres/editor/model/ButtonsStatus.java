package com.jtorres.editor.model;
public class ButtonsStatus {
    private boolean allBtn;
    private boolean clipBtn;
    private int sizeJsl;
    private boolean resetBrightness;
    private int brightness;
    private int redJsl;
    private int greenJsl;
    private int blueJsl;
    private boolean greyBtn;
    private int filterJsl;

    public void setAllBtn(boolean allBtn) {
        this.allBtn = allBtn;
    }

    public boolean isClipBtn() {
        return clipBtn;
    }

    public void setClipBtn(boolean clipBtn) {
        this.clipBtn = clipBtn;
    }

    public boolean isResetBrightness() {
        return resetBrightness;
    }

    public void setResetBrightness(boolean resetBrightness) {
        this.resetBrightness = resetBrightness;
    }


    public int getSizeJsl() {
        return sizeJsl;
    }

    public void setSizeJsl(int sizeJsl) {
        this.sizeJsl = sizeJsl;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getRedJsl() {
        return redJsl;
    }

    public void setRedJsl(int redJsl) {
        this.redJsl = redJsl;
    }

    public int getGreenJsl() {
        return greenJsl;
    }

    public void setGreenJsl(int greenJsl) {
        this.greenJsl = greenJsl;
    }

    public int getBlueJsl() {
        return blueJsl;
    }

    public void setBlueJsl(int blueJsl) {
        this.blueJsl = blueJsl;
    }

    public void setGreyBtn(boolean greyBtn) {
        this.greyBtn = greyBtn;
    }

    public int getFilterJsl() {
        return filterJsl;
    }

    public boolean isGreyBtn() {
        return greyBtn;
    }

    public void setFilterJsl(int filterJsl) {
        this.filterJsl = filterJsl;
    }
}
