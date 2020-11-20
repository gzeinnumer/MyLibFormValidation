package com.gzeinnumer.mylibformvalidator.model;

import android.widget.EditText;

import com.gzeinnumer.mylibformvalidator.constant.TypeForm;

public class ValidatorModel {
    private EditText editText;

    private Rule rule;

    private boolean isDone = false;

    public ValidatorModel(EditText editText) {
        this.editText = editText;
        rule = new Rule(TypeForm.TEXT);
    }

    public ValidatorModel(EditText editText, TypeForm typeForm) {
        this.editText = editText;
        this.rule = new Rule(typeForm);
    }


    public ValidatorModel(EditText editText, TypeForm typeForm, int minLength) {
        this.editText = editText;
        this.rule = new Rule(typeForm, minLength);
    }

    public ValidatorModel(EditText editText, TypeForm typeForm, int minLength, String errorEmpty) {
        this.editText = editText;
        Rule r = new Rule(typeForm, minLength, errorEmpty);
        if (errorEmpty.length()==0 || errorEmpty==null){
            r.setErrorEmpty("Tidak boleh kosong");
        }
        this.rule = r;
    }

    public ValidatorModel(EditText editText, TypeForm typeForm, int minLength, String errorEmpty, String errorFormat) {
        this.editText = editText;
        Rule r = new Rule(typeForm, minLength, errorEmpty, errorFormat);
        if (errorEmpty.length()==0 || errorEmpty==null){
            r.setErrorEmpty("Tidak boleh kosong");
        }
        this.rule = r;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
