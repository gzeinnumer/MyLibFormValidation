package com.gzeinnumer.mylibformvalidator.constant;

public class BaseMessage {
    public String empty = "Tidak boleh kosong";
    public String format = "Format salah";

    public BaseMessage() {
    }

    public BaseMessage(String empty, String format) {
        this.empty = empty;
        this.format = format;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
