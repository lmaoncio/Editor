package com.jtorres.editor.model;

public class ImageStatus {
    private boolean status;
    private ButtonsStatus allStatus;
    private ButtonsStatus clipStatus;

    public ButtonsStatus getAllStatus() {
        return allStatus;
    }

    public void setAllStatus(ButtonsStatus allStatus) {
        this.allStatus = allStatus;
    }

    public ButtonsStatus getClipStatus() {
        return clipStatus;
    }

    public void setClipStatus(ButtonsStatus clipStatus) {
        this.clipStatus = clipStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
