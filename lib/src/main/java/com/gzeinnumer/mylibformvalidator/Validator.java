package com.gzeinnumer.mylibformvalidator;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.gzeinnumer.mylibformvalidator.constant.TypeForm;
import com.gzeinnumer.mylibformvalidator.helper.RemoveSpaceAtFirst;
import com.gzeinnumer.mylibformvalidator.helper.ValidatorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private List<ValidatorModel> views = new ArrayList<>();

    public Validator() {}

    public void addView(ValidatorModel validatorModel) {
        this.views.add(validatorModel);
    }

    public void addAllView(List<ValidatorModel> views) {
        this.views = views;
    }

    public boolean validate() {
        return toValidate(this.views);
    }

    public static boolean toValidate(List<ValidatorModel> views) {
        String errorEmpty;
        String errorFormat;
        int minLength;
        boolean isValidate = true;

        for (int i = 0; i < views.size(); i++) {
            ValidatorModel view = views.get(i);
            EditText ed = view.getEditText();

            ed.addTextChangedListener(new RemoveSpaceAtFirst(ed));

            minLength = view.getRule().getMinLength();

            errorEmpty = (view.getRule().getErrorEmpty() != null) ? view.getRule().getErrorEmpty() : "Tidak boleh kosong";
            errorFormat = (view.getRule().getErrorFormat() != null) ? view.getRule().getErrorFormat() : "Format salah";

            if (ed.getText().toString().length() < minLength) {
                ed.setError(errorEmpty);
                isValidate = false;
                continue;
            }
            if (view.getRule().getTypeForm() == TypeForm.EMAIL) {
                if (!isValidEmail(ed.getText().toString())){
                    ed.setError(errorFormat);
                    isValidate = false;
                }
            } else if (view.getRule().getTypeForm() == TypeForm.NUMBER) {
                if (!isValidNumber(ed.getText().toString())) {
                    ed.setError(errorFormat);
                    isValidate = false;
                }
            } else if (view.getRule().getTypeForm() == TypeForm.PHONE) {
                if (!isValidPhone(ed.getText().toString())) {
                    ed.setError(errorFormat);
                    isValidate = false;
                }
            }
        }
        return isValidate;
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
        return android.util.Patterns.PHONE.matcher(number).matches();
    }
}
