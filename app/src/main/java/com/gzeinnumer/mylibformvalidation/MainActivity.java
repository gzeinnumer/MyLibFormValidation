package com.gzeinnumer.mylibformvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.gzeinnumer.mylibformvalidator.constant.TypeForm;
import com.gzeinnumer.mylibformvalidator.Validator;
import com.gzeinnumer.mylibformvalidator.helper.ValidatorModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputEditText formNama, formAlamat, formNim, formJurusan, formEmail, formUmur, formNoHp;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formNama = findViewById(R.id.form1);
        formAlamat = findViewById(R.id.form2);
        formNim = findViewById(R.id.form3);
        formJurusan = findViewById(R.id.form4);
        formEmail = findViewById(R.id.form5);
        formUmur = findViewById(R.id.form6);
        formNoHp = findViewById(R.id.form7);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataNew1();
//                validateDataNew2();
//                validateDataNew3();
            }
        });
    }

    private void validateDataNew1() {
        List<ValidatorModel> views = new ArrayList<>();
        views.add(new ValidatorModel(formNama));
        views.add(new ValidatorModel(formAlamat, TypeForm.TEXT));
        views.add(new ValidatorModel(formNim, TypeForm.TEXT, 10));
        views.add(new ValidatorModel(formJurusan, TypeForm.TEXT, 1, "Jurusan tidak boleh kosong"));
        views.add(new ValidatorModel(formEmail, TypeForm.EMAIL, 1, "Email tidak boleh kosong", "Format email salah"));
        views.add(new ValidatorModel(formUmur, TypeForm.NUMBER, 1, "Umur tidak boleh kosong", "Format number salah"));
        views.add(new ValidatorModel(formNoHp, TypeForm.PHONE, 1, "NoHp tidak boleh kosong", "Format NoHp salah"));

        if (Validator.toValidate(views)) {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();
        }
    }

    private void validateDataNew2() {
        Validator validator = new Validator();

        validator.addView(new ValidatorModel(formNama));
        validator.addView(new ValidatorModel(formAlamat, TypeForm.TEXT));
        validator.addView(new ValidatorModel(formNim, TypeForm.TEXT, 10));
        validator.addView(new ValidatorModel(formJurusan, TypeForm.TEXT, 1, "Jurusan tidak boleh kosong"));
        validator.addView(new ValidatorModel(formEmail, TypeForm.EMAIL, 1, "Email tidak boleh kosong", "Format email salah"));
        validator.addView(new ValidatorModel(formUmur, TypeForm.NUMBER, 1, "Umur tidak boleh kosong", "Format number salah"));
        validator.addView(new ValidatorModel(formNoHp, TypeForm.PHONE, 1, "NoHp tidak boleh kosong", "Format NoHp salah"));

        if (validator.validate()) {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();
        }
    }

    private void validateDataNew3() {
        List<ValidatorModel> views = new ArrayList<>();

        views.add(new ValidatorModel(formNama));
        views.add(new ValidatorModel(formAlamat, TypeForm.TEXT));
        views.add(new ValidatorModel(formNim, TypeForm.TEXT, 10));
        views.add(new ValidatorModel(formJurusan, TypeForm.TEXT, 1, "Jurusan tidak boleh kosong"));
        views.add(new ValidatorModel(formEmail, TypeForm.EMAIL, 1, "Email tidak boleh kosong", "Format email salah"));
        views.add(new ValidatorModel(formUmur, TypeForm.NUMBER, 1, "Umur tidak boleh kosong", "Format number salah"));
        views.add(new ValidatorModel(formNoHp, TypeForm.PHONE, 1, "NoHp tidak boleh kosong", "Format NoHp salah"));

        Validator validator = new Validator();

        validator.addAllView(views);

        if (validator.validate()) {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();
        }
    }
}