package com.gzeinnumer.mylibformvalidator.model;

import android.widget.EditText;

import com.gzeinnumer.mylibformvalidator.constant.TypeForm;

public class FormBase {
    private FormInput formInput;
    private Rule rule;
    private boolean isDone = false;

    public FormBase(FormInput formInput) {
        this.formInput = formInput;
        this.rule = new Rule(TypeForm.TEXT);
    }

    public FormBase(FormInput formInput, Rule rule) {
        this.formInput = formInput;
        this.rule = rule;
    }

    public FormBase(EditText formInput, Rule rule) {
        this.formInput = new FormInput(null, formInput);
        this.rule = rule;
    }

    public FormInput getFormInput() {
        return formInput;
    }

    public void setFormInput(FormInput formInput) {
        this.formInput = formInput;
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
