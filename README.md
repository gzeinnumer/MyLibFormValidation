| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example5.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example1.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example8.jpg" width="300"/> |<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example10.jpg" width="300"/> |
|:-----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|---|

<h1 align="center">
  MyLibFormValidation - Easy Validation
</h1>

<div align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.2-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Koltin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple way to Validate Form</p>
</div>

---

## Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.github.gzeinnumer:MyLibFormValidation:version'
  implementation 'com.google.android.material:material:1.2.0'
}
```

## Feature List
- [x] [Validation Form](#validation-form)
- [x] [Validation Form RealTime](#validation-form-realtime)

## Tech stack and 3rd library
- Material.io ([docs](https://material.io/develop/android/docs/getting-started))

---

**First Step**. Use `MaterialComponents` in your style :

```xml
<style name="AppTheme" parent="Theme.MaterialComponents.Light.NoActionBar">
    <!-- Customize your theme here. -->
</style>
```

---

## USE

### Validation Form

- **Import and start validation with make and object from class `Validator`.**
```java
//import com.gzeinnumer.mylibformvalidator.Validator;

Validator validator = new Validator();
```

#
- **Add your form that you want to validate.**

Add `EditText` or `TextInputEditText` to `validator` with `addView(view)`.
```java
TextInputEditText formNama = = findViewById(R.id.form_nama);

validator.addView(formNama); // Default TypeForm.TEXT
```
Add `EditText` or `TextInputEditText` with custom `Rule`.
```java
validator.addView(
    formNama,
    new Rule(TypeForm.TEXT)
);
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example12.jpg" width="400"/>
<p>

Add `EditText` or `TextInputEditText` with `TextInputLayout`.
```java
TextInputEditText formNama = = findViewById(R.id.form_nama);
TextInputLayout formNamaParent = findViewById(R.id.form_nama_p);

validator.addView(
    new FormInput(formNamaParent, formNama)
); // Default TypeForm.TEXT
```
Add `EditText` or `TextInputEditText` with `TextInputLayout` and custom `Rule`.
```java
TextInputEditText formNama = = findViewById(R.id.form_nama);
TextInputLayout formNamaParent = findViewById(R.id.form_nama_p);

validator.addView(
    new FormInput(formNamaParent, formNama),
    new Rule(TypeForm.TEXT)
);
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example13.jpg" width="400"/>
<p>

#
- **Custom `Rule`.**

Here some `Rule` that you can use.
```java
int minLength = 2;
String errorLength = "Form tidak boleh kosong";
String errorFormat = "Format salah";

new Rule(TypeForm.TEXT)
new Rule(TypeForm.TEXT, minLength)
new Rule(TypeForm.TEXT, minLength, errorLength)
new Rule(TypeForm.TEXT, minLength, errorLength, errorFormat)
```
`TypeForm` available value
```java
TypeForm.TEXT               //Support Symbol / Number Decimal
TypeForm.EMAIL              //Email Format
TypeForm.NUMBER             //Number Integer
TypeForm.PHONE              //Phone Number Format With +62
TypeForm.TEXT_NO_SYMBOL     //Not Support Symbol
```
Example :
```java
int minLength = 8;
String errorLength = "Minimal 8 Charakter";
String errorFormat = "Tidak Boleh Mengunakan Symbol";
validator.addView(
        new FormInput(formNamaParent, formNama),
        new Rule(TypeForm.TEXT_NO_SYMBOL, minLength, errorLength, errorFormat)
);
```

|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example14.jpg" width="400"/>|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example15.jpg" width="400"/>|
|---|---|

```java
int minLength = 8;
String errorLength = "Minimal 8 Charakter";
String errorFormat = "Tidak Boleh Mengunakan Symbol";
validator.addView(
        formNama,
        new Rule(TypeForm.TEXT_NO_SYMBOL, minLength, errorLength, errorFormat)
);
```

|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example16.jpg" width="400"/>|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example17.jpg" width="400"/>|
|---|---|

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example18.jpg" width="400"/>
<p>

#
- **Validate Result.**

Use `validator.validate()` to get result of your validate.
```java
boolean result = validator.validate();
//true if validate success
//false if validate failed
```

#
- **Validate data by `OnClickListener`.**

Trigger with `OnClickListener`.
```java
@Override
protected void onCreate(Bundle savedInstanceState) {

    ... findViewById ...

    btnSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validateData();
        }
    });
}
```
Validate Proses will be like this.
```java
TextInputEditText formNama, formAlamat, formNim,
    formJurusan, formEmail, formUmur, formNoHp;
TextInputLayout formNamaParent, formAlamatParent, formNimParent,
    formJurusanParent, formEmailParent, formUmurParent, formNoHpParent;

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
```

|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example5.jpg" width="400"/>|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example1.jpg" width="400"/>|
|---|---|

**FullCode** [**MainActivity**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/java/com/gzeinnumer/mylibformvalidation/MainActivity.java) **&** [**XML**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/res/layout/activity_main.xml) **.**

**Preview** :

| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example6.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example5.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example1.jpg"/> |
|---|---|---|
| First Preview | Validate output `false` | Validate output `true` |

| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example4.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example7.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example3.jpg"/> |<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example2.jpg"/> |
|---|---|---|---|
| `default setting` | minimal charakter is 8 | Custom error message | Format `email` must be correct |

---

### Validation Form RealTime

- **Import and start validation with make and object from class `ValidatorRealTime`.**
```java
//import com.gzeinnumer.mylibformvalidator.ValidatorRealTime;

ValidatorRealTime validatorRealTime = new ValidatorRealTime();
```

#
- **Add your form that you want to validate.**

Add `EditText` or `TextInputEditText` to `validatorRealTime` with `addView(view)`.
```java
TextInputEditText formUserName = = findViewById(R.id.form_username);

validatorRealTime.addView(formUserName); // Default TypeForm.TEXT
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example19.jpg" width="400"/>
<p>

Add `EditText` or `TextInputEditText` with custom `Rule`.
```java
validatorRealTime.addView(
    formUserName,
    new Rule(TypeForm.EMAIL)
);
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example20.jpg" width="400"/>
<p>

Add `EditText` or `TextInputEditText` with `TextInputLayout`.
```java
TextInputEditText formUserName = = findViewById(R.id.form_username);
TextInputLayout formUserNameParent = findViewById(R.id.form_username_p);

validator.addView(
    new FormInput(formUserNameParent, formUserName)
); // Default TypeForm.TEXT
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example21.jpg" width="400"/>
<p>

Add `EditText` or `TextInputEditText` with `TextInputLayout` and custom `Rule`.
```java
TextInputEditText formUserName = = findViewById(R.id.form_username);
TextInputLayout formUserNameParent = findViewById(R.id.form_username_p);

validator.addView(
    new FormInput(formUserNameParent, formUserName)
    new Rule(TypeForm.EMAIL)
);
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example22.jpg" width="400"/>
<p>

#
- **Custom `Rule`.**

Here some `Rule` that you can use.
```java
int minLength = 2;
String errorLength = "Form tidak boleh kosong";
String errorFormat = "Format salah";

new Rule(TypeForm.TEXT)
new Rule(TypeForm.TEXT, minLength)
new Rule(TypeForm.TEXT, minLength, errorLength)
new Rule(TypeForm.TEXT, minLength, errorLength, errorFormat)
```
`TypeForm` available value
```java
TypeForm.TEXT               //Support Symbol / Number Decimal
TypeForm.EMAIL              //Email Format
TypeForm.NUMBER             //Number Integer
TypeForm.PHONE              //Phone Number Format With +62
TypeForm.TEXT_NO_SYMBOL     //Not Support Symbol
```
Example :
```java
int minLength = 8;
String errorLength = "Minimal 8 Charakter";
String errorFormat = "Tidak Boleh Mengunakan Symbol";
validator.addView(
        formNama,
        new Rule(TypeForm.TEXT_NO_SYMBOL, minLength, errorLength, errorFormat)
);
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example23.jpg" width="400"/>
<p>

#
- **Start Validation `RealTime`.**

Use `validatorRealTime.build();` to start validation.
```java
validatorRealTime.build();
```

#
- **Validate Result.**

Use `validatorRealTime.observer( ... );` to get result of your validate for every input data on your form.
```java
validatorRealTime.observer(new ValidatorCallBack() {
    @Override
    public void result(boolean isDone) {
        //true if validate success
        //false if validate failed

        btnSubmit.setEnabled(isDone);
    }
});
```

|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example24.jpg" width="400"/>|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example25.jpg" width="400"/>|
|---|---|

#
- **Validate data by `OnClickListener`.**

Trigger with `OnClickListener`.
```java
btnValidate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (validatorRealTime.getResult()){
            Toast.makeText(SecondActivity.this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SecondActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
        //true if validate success
        //false if validate failed
    }
});
```

#
- Preview Code
```java
TextInputEditText formUserName = findViewById(R.id.form_username);
TextInputLayout formUserNameParent = findViewById(R.id.form_username_p);

TextInputEditText formPass = findViewById(R.id.form_password);
TextInputLayout formPassParent = findViewById(R.id.form_password_p);

Button btnSubmit = findViewById(R.id.submit);
Button btnValidate = findViewById(R.id.validate);

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
```

**FullCode** [**SecondActivity**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/java/com/gzeinnumer/mylibformvalidation/SecondActivity.java) **&** [**XML**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/res/layout/activity_second.xml) **.**

**Preview** :

| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example8.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example9.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example10.jpg"/> |
|---|---|---|
| Email Format | Password Requirements 8 Character  | `Button` Login Enable |

---

### Version
- **1.0.0**
  - First Release
- **1.0.1**
  - Add TEXT_NO_SYMBOL
- **1.0.2**
  - Support TextInputLayout

---

### Contribution
You can sent your constibution to `branche` `open-pull`.

---

```
Copyright 2020 M. Fadli Zein
```