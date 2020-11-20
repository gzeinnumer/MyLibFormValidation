package com.gzeinnumer.mylibformvalidator.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EmailValidate implements TextWatcher {
    EditText editText;

    public EmailValidate(EditText ed) {
        this.editText = ed;
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

    }
}
