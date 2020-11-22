package com.gzeinnumer.mylibformvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gzeinnumer.mylibformvalidator.ValidatorRealTime;
import com.gzeinnumer.mylibformvalidator.constant.TypeForm;
import com.gzeinnumer.mylibformvalidator.helper.ValidatorCallBack;
import com.gzeinnumer.mylibformvalidator.model.FormInput;
import com.gzeinnumer.mylibformvalidator.model.Rule;

public class SecondActivity extends AppCompatActivity {

    TextInputEditText formUserName, formPass;
    TextInputLayout formUserNameParent, formPassParent;
    Button btnSubmit, btnValidate;

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        formUserName = findViewById(R.id.form_username);
        formUserNameParent = findViewById(R.id.form_username_p);

        formPass = findViewById(R.id.form_password);
        formPassParent = findViewById(R.id.form_password_p);

        btnSubmit = findViewById(R.id.submit);
        btnValidate = findViewById(R.id.validate);

        validateData();
    }

    private void validateData() {
        ValidatorRealTime validatorRealTime = new ValidatorRealTime();

        validatorRealTime.addView(
                formUserName,
                new Rule(TypeForm.EMAIL)
        );
        validatorRealTime.addView(
                new FormInput(formPassParent, formPass),
                new Rule(TypeForm.TEXT_NO_SYMBOL, 8, "Minimal 8 karakter", "Format salah")
        );

        validatorRealTime.build();

        validatorRealTime.observer(new ValidatorCallBack() {
            @Override
            public void result(boolean isDone) {
                Log.d(TAG, "result: "+isDone);
                btnSubmit.setEnabled(isDone);
            }
        });

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatorRealTime.getResult()){
                    Toast.makeText(SecondActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SecondActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}