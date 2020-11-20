| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example6.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example5.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example1.jpg" width="300"/> |
|:-----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|

<h1 align="center">
  MyLibFormValidation - Easy Validation
</h1>

<div align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg?style=flat"></a>
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
- [x] [Single Select](#searchviewdialog)

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
[MainActivity](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/java/com/gzeinnumer/mylibformvalidation/MainActivity.java)
[XML](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/res/layout/activity_main.xml)

You can add view that you want to validate to `List` with model `ValidatorModel`.
```java
EditText edittext = findViewById(R.id.edittext);
int minLength = 8;
String errorLength = "Form tidak boleh kosong";
String errorFormat = "Format salah";

List<ValidatorModel> views = new ArrayList<>();

views.add(new ValidatorModel(edittext));
views.add(new ValidatorModel(edittext, TypeForm.TEXT));
views.add(new ValidatorModel(edittext, TypeForm.TEXT,   minLength));
views.add(new ValidatorModel(edittext, TypeForm.TEXT,   minLength, errorLength));
views.add(new ValidatorModel(edittext, TypeForm.EMAIL,  minLength, errorLength, errorFormat));
views.add(new ValidatorModel(edittext, TypeForm.NUMBER, minLength, errorLength, errorFormat));
views.add(new ValidatorModel(edittext, TypeForm.PHONE,  minLength, errorLength, errorFormat));

//TypeForm avaliable value
//TypeForm.TEXT
//TypeForm.EMAIL
//TypeForm.NUMBER
//TypeForm.PHONE
```

- Type 1.
```java
public class MainActivity extends AppCompatActivity {

    TextInputEditText formNama, formAlamat, formNim, formJurusan, formEmail, formUmur, formNoHp;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ...

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataNew1();
            }
        });
    }

    private void validateDataNew1() {
        //type 1
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
}
```

- Type 2.
```java
//type 2
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
```

- Type 3.
```java
//type 3
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
```

**Preview** :

| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example6.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example5.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example1.jpg.jpg"/> |
|---|---|---|
| First Preview | Validate output `false` | Validate output `true` |

| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example4.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example7.jpg"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example3.jpg"/> |<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example2.jpg"/> |
|---|---|---|---|
| `default setting` | minimal charakter is 8 | Custom error message | Format `email` must be correct |

---

**FullCode [MainActivity](https://github.com/gzeinnumer/MyLibDialogSearchView/blob/master/app/src/main/java/com/gzeinnumer/mylibsearchviewdialog/MainActivity.java)**

---

### Version
- **2.0.3**
  - First Release
- **2.0.4**
  - Add animation and set custom animation show

---

### Contribution
You can sent your constibution to `branche` `open-pull`.

---

```
Copyright 2020 M. Fadli Zein
```