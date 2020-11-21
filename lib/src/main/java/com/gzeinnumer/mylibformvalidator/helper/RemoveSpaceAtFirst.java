package com.gzeinnumer.mylibformvalidator.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class RemoveSpaceAtFirst implements TextWatcher {
    EditText editText;
    TextInputLayout parent;

    public RemoveSpaceAtFirst(EditText ed, TextInputLayout parent) {
        this.editText = ed;
        this.parent = parent;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String text = editText.getText().toString();
        if (text.startsWith(" ")) {
            editText.setText(text.trim());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (parent != null){
            parent.setError(null);
            parent.setErrorEnabled(false);
        }
    }
}
