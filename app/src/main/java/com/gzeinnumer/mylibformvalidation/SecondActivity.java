package com.gzeinnumer.mylibformvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.gzeinnumer.mylibformvalidator.ValidatorRealTime;
import com.gzeinnumer.mylibformvalidator.constant.TypeForm;
import com.gzeinnumer.mylibformvalidator.helper.ValidatorCallBack;
import com.gzeinnumer.mylibformvalidator.helper.ValidatorModel;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextInputEditText formUserName, formPass;
    Button btn1, btn2;

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        formUserName = findViewById(R.id.form1);
        formPass = findViewById(R.id.form2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setEnabled(true);
        validateData();
    }

    private void validateData() {
        List<ValidatorModel> views = new ArrayList<>();
        views.add(new ValidatorModel(formUserName, TypeForm.EMAIL));
        views.add(new ValidatorModel(formPass, TypeForm.TEXT, 8, "Password tidak boleh kosong", "Minimal 8 karakter"));

        ValidatorRealTime validatorRealTime = new ValidatorRealTime(views);

        validatorRealTime.build();

        validatorRealTime.observer(new ValidatorCallBack() {
            @Override
            public void result(boolean isDone) {
                Log.d(TAG, "result: "+isDone);
//                btn1.setEnabled(isDone);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
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