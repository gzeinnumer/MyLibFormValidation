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

public class MainActivity extends AppCompatActivity {

    TextInputEditText formNama, formAlamat, formNim,
            formJurusan, formEmail, formUmur, formNoHp;
    TextInputLayout formNamaParent, formAlamatParent, formNimParent,
            formJurusanParent, formEmailParent, formUmurParent, formNoHpParent;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formNama = findViewById(R.id.form_nama);
        formNamaParent = findViewById(R.id.form_nama_p);
        formAlamat = findViewById(R.id.form_alamat);
        formAlamatParent = findViewById(R.id.form_alamat_p);
        formNim = findViewById(R.id.form_nim);
        formNimParent = findViewById(R.id.form_nim_p);
        formJurusan = findViewById(R.id.form_jurusan);
        formJurusanParent = findViewById(R.id.form_jurusan_p);
        formEmail = findViewById(R.id.form_email);
        formEmailParent = findViewById(R.id.form_email_p);
        formUmur = findViewById(R.id.form_umur);
        formUmurParent = findViewById(R.id.form_umur_p);
        formNoHp = findViewById(R.id.form_nohp);
        formNoHpParent = findViewById(R.id.form_nohp_p);

        btnSubmit = findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        Validator validator = new Validator();

        validator.addView(formNama);
        validator.addView(
                formAlamat,
                new Rule(TypeForm.TEXT)
        );
        validator.addView(
                new FormInput(formNimParent, formNim)
        );
        validator.addView(
                new FormInput(formJurusanParent, formJurusan),
                new Rule(TypeForm.TEXT_NO_SYMBOL)
        );
        validator.addView(
                new FormInput(formEmailParent, formEmail),
                new Rule(TypeForm.EMAIL, 2)
        );
        validator.addView(
                new FormInput(formUmurParent, formUmur),
                new Rule(TypeForm.NUMBER, 2, "Umur tidak boleh kosong")
        );
        validator.addView(
                new FormInput(formNoHpParent, formNoHp),
                new Rule(TypeForm.PHONE, 2, "NoHp tidak boleh kosong", "Format NoHp salah")
        );

        if (validator.validate()) {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();
        }
    }
}