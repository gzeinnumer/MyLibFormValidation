package com.gzeinnumer.mylibformvalidator.model;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class FormInput {
    private TextInputLayout parent;
    private EditText editText;

    public FormInput(TextInputLayout parent, EditText editText) {
        this.parent = parent;
        this.editText = editText;
    }

    public TextInputLayout getParent() {
        return parent;
    }

    public void setParent(TextInputLayout parent) {
        this.parent = parent;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}
