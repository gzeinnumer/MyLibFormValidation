package com.gzeinnumer.mylibformvalidator;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.gzeinnumer.mylibformvalidator.constant.BaseMessage;
import com.gzeinnumer.mylibformvalidator.constant.TypeForm;
import com.gzeinnumer.mylibformvalidator.helper.RemoveSpaceAtFirst;
import com.gzeinnumer.mylibformvalidator.model.FormInput;
import com.gzeinnumer.mylibformvalidator.model.FormBase;
import com.gzeinnumer.mylibformvalidator.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String TAG = "Validator";

    BaseMessage baseMessage;

    private final List<FormBase> views = new ArrayList<>();

    public Validator() {
        baseMessage = new BaseMessage();
    }

    public boolean validate() {
        return toValidate(this.views);
    }

    private boolean toValidate(List<FormBase> views) {
        String errorEmpty;
        String errorFormat;
        int minLength;
        boolean isValidate = true;

        for (int i = 0; i < views.size(); i++) {
            FormBase view = views.get(i);
            EditText ed = view.getFormInput().getEditText();
            TextInputLayout parent = view.getFormInput().getParent();

            ed.addTextChangedListener(new RemoveSpaceAtFirst(ed, parent));

            minLength = view.getRule().getMinLength();

            errorEmpty = (view.getRule().getErrorEmpty() != null) ? view.getRule().getErrorEmpty() : baseMessage.getEmpty();
            errorFormat = (view.getRule().getErrorFormat() != null) ? view.getRule().getErrorFormat() : baseMessage.getFormat();

            if (ed.getText().toString().length() == 0) {
                viewError(parent, ed, errorEmpty);
                isValidate = false;
                continue;
            }
            if (view.getRule().getTypeForm() == TypeForm.EMAIL) {
                if (!isValidEmail(ed.getText().toString())) {
                    viewError(parent, ed, errorFormat);
                    isValidate = false;
                }
            } else if (view.getRule().getTypeForm() == TypeForm.NUMBER) {
                if (!isValidNumber(ed.getText().toString())) {
                    viewError(parent, ed, errorFormat);
                    isValidate = false;
                }
            } else if (view.getRule().getTypeForm() == TypeForm.PHONE) {
                if (!isValidPhone(ed.getText().toString())) {
                    viewError(parent, ed, errorFormat);
                    isValidate = false;
                }
            } else if (view.getRule().getTypeForm() == TypeForm.TEXT) {
                if (ed.getText().toString().length() < minLength) {
                    viewError(parent, ed, errorFormat);
                    isValidate = false;
                }
            } else if (view.getRule().getTypeForm() == TypeForm.TEXT_NO_SYMBOL) {
                if (isValidNoSymbol(ed.getText().toString())) {
                    viewError(parent, ed, errorFormat);
                    isValidate = false;
                }
            }
        }
        return isValidate;
    }

    private void viewError(TextInputLayout parent, EditText ed, String msg) {
        if (parent!=null)
            parent.setError(msg);
        else
            ed.setError(msg);
    }

    private void goneError(TextInputLayout parent) {
        if (parent != null){
            parent.setError(null);
            parent.setErrorEnabled(false);
        }
    }

    private static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private static boolean isValidNumber(String target) {
        for (char c : target.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static boolean isValidPhone(String number) {
        return Patterns.PHONE.matcher(number).matches();
    }

    private static boolean isValidNoSymbol(String s) {
        if (s == null || s.trim().isEmpty()) {
            Log.d(TAG, "Incorrect format of string");
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);

        return m.find();
    }

    public void addView(EditText view) {
        views.add(new FormBase(new FormInput(null, view)));
    }

    public void addView(FormInput formInput) {
        views.add(new FormBase(formInput));
    }

    public void addView(FormInput formInput, Rule rules) {
        views.add(new FormBase(formInput, rules));
    }

    public void addView(EditText formInput, Rule rules) {
        views.add(new FormBase(formInput, rules));
    }
}
