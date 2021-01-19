package com.gzeinnumer.mylibformvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gzeinnumer.mylibformvalidator.Validator;
import com.gzeinnumer.mylibformvalidator.constant.TypeForm;
import com.gzeinnumer.mylibformvalidator.model.FormInput;
import com.gzeinnumer.mylibformvalidator.model.Rule;

public class ThirdActivity extends AppCompatActivity {

    TextInputEditText formUserName;
    TextInputLayout formUserNameParent;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        formUserName = findViewById(R.id.form_nama);
        formUserNameParent = findViewById(R.id.form_nama_p);

        btnSubmit = findViewById(R.id.submit_t);

        Validator validator = new Validator();
        validator.addView(
                new FormInput(formUserNameParent, formUserName),
                new Rule(TypeForm.TEXT_NO_SYMBOL,8,"Minimal 8 Charakter", "Tidak Boleh Mengunakan Symbol")
        );
        //validator.removeView(formUserName);
        validator.build();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validator.validate()) {
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Done", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}