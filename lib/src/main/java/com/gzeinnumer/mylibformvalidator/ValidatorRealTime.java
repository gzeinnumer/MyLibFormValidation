package com.gzeinnumer.mylibformvalidator;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.gzeinnumer.mylibformvalidator.constant.TypeForm;
import com.gzeinnumer.mylibformvalidator.helper.RemoveSpaceAtFirst;
import com.gzeinnumer.mylibformvalidator.helper.ValidatorCallBack;
import com.gzeinnumer.mylibformvalidator.model.ValidatorModel;

import java.util.List;

public class ValidatorRealTime {

    private static final String TAG = "ValidatorRealTime";

    private final List<ValidatorModel> views;

    public ValidatorRealTime(List<ValidatorModel> views) {
        this.views = views;
    }

    public void build() {
        String errorEmpty;
        String errorFormat;
        int minLength;

        for(int i=0; i<views.size(); i++){
            ValidatorModel view = views.get(i);
            EditText ed = view.getEditText();
            ed.addTextChangedListener(new RemoveSpaceAtFirst(ed));

            minLength = view.getRule().getMinLength();

            errorEmpty = (view.getRule().getErrorEmpty() != null) ? view.getRule().getErrorEmpty() : "Tidak boleh kosong";
            errorFormat = (view.getRule().getErrorFormat() != null) ? view.getRule().getErrorFormat() : "Format salah";

            int finalMinLength = minLength;
            String finalErrorEmpty = errorEmpty;
            String finalErrorFormat = errorFormat;
            int finalI = i;
            ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        if (ed.getText().toString().length() == 0) {
                            ed.setError(finalErrorEmpty);
                        }
                        if (view.getRule().getTypeForm() == TypeForm.EMAIL) {
                            if (!isValidEmail(ed.getText().toString())){
                                ed.setError(finalErrorFormat);
                            }
                        } else if (view.getRule().getTypeForm() == TypeForm.NUMBER) {
                            if (!isValidNumber(ed.getText().toString())) {
                                ed.setError(finalErrorFormat);
                            }
                        } else if (view.getRule().getTypeForm() == TypeForm.PHONE) {
                            if (!isValidPhone(ed.getText().toString())) {
                                ed.setError(finalErrorFormat);
                            }
                        } else if (view.getRule().getTypeForm() == TypeForm.TEXT) {
                            if (ed.getText().toString().length() < finalMinLength) {
                                ed.setError(finalErrorEmpty);
                            }
                        }
                    }
                }
            });
            ed.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (view.getRule().getTypeForm() == TypeForm.EMAIL) {
                        if (!isValidEmail(ed.getText().toString())){
                            views.get(finalI).setDone(false);
                        } else {
                            views.get(finalI).setDone(true);
                        }
                    } else if (view.getRule().getTypeForm() == TypeForm.NUMBER) {
                        if (!isValidNumber(ed.getText().toString())) {
                            views.get(finalI).setDone(false);
                        } else {
                            views.get(finalI).setDone(true);
                        }
                    } else if (view.getRule().getTypeForm() == TypeForm.PHONE) {
                        if (!isValidPhone(ed.getText().toString())) {
                            views.get(finalI).setDone(false);
                        } else {
                            views.get(finalI).setDone(true);
                        }
                    } else if (view.getRule().getTypeForm() == TypeForm.TEXT) {
                        if (ed.getText().toString().length() < finalMinLength) {
                            views.get(finalI).setDone(false);
                        } else {
                            views.get(finalI).setDone(true);
                        }
                    }

                    triggerCallBack();
                }
            });

        }
    }

    private void triggerCallBack() {
        boolean isDone = true;
        for (int i=0; i<views.size(); i++){
            if (!views.get(i).isDone()){
                isDone = false;
                break;
            }
        }
        validatorCallBack.result(isDone);
    }

    public boolean getResult() {
        boolean isDone = true;
        for (int i=0; i<views.size(); i++){
            if (!views.get(i).isDone()){
                isDone = false;
                break;
            }
        }
        return isDone;
    }

    private boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean isValidNumber(String target) {
        for (char c : target.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean isValidPhone(String number) {
        return Patterns.PHONE.matcher(number).matches();
    }

    ValidatorCallBack validatorCallBack;

    public void observer(ValidatorCallBack validatorCallBack) {
        this.validatorCallBack = validatorCallBack;
    }
}
