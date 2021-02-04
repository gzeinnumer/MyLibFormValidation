| <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example5.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example1.jpg" width="300"/> | <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example8.jpg" width="300"/> |<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example10.jpg" width="300"/> |
|:-----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|---|

<h1 align="center">
  MyLibFormValidation - Easy Validation
</h1>

<div align="center">
    <a><img src="https://img.shields.io/badge/Version-1.1.7-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Kotlin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple way to Validate Form</p>
</div>

---
# Content List
* [Download](#download)
* [Feature List](#feature-list)
* [Tech stack and 3rd library](#tech-stack-and-3rd-library)
* [Usage](#usage)
* [Example Code/App](#example-codeapp)
* [Version](#version)
* [Contribution](#contribution)

---
# Download
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

---
# Feature List
- [x] [Validation Form](#validation-form)
- [x] [Validation Form RealTime](#validation-form-realtime)

---
# Tech stack and 3rd library
- Material.io ([docs](https://material.io/develop/android/docs/getting-started))

---
# Usage

**First Step**. Use `MaterialComponents` in your style :

```xml
<style name="AppTheme" parent="Theme.MaterialComponents.Light.NoActionBar">
    <!-- Customize your theme here. -->
</style>
```

#
### Validation Form

- **Import and start validation with make and object from class `Validator`.**
```java
//import com.gzeinnumer.mylibformvalidator.Validator;

TextInputLayout formNamaParent = findViewById(R.id.form_nama_p);
TextInputEditText formNama = findViewById(R.id.form_nama);
Button btnSubmit = findViewById(R.id.btn_submit);

Validator validator = new Validator();

//Add your form that you want to validate.

//Add `EditText` or `TextInputEditText` to `validator`.
validator.addView(formNama); // Default TypeForm.TEXT

btnSubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        boolean result = validator.validate();
        //true if validate success
        //false if validate failed
        if (result) {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();
        }
    }
});
```

#
- Delete `form view` from validate process

Put your `EditText` or `TextInputEditText` to `removeView(view)`.
```java
validator.removeView(formNama);
```

#
- **Add view with custom configuration**
```java
//example 1
//Add `EditText` or `TextInputEditText` with custom `Rule`.
validator.addView(
    formNama,
    new Rule(TypeForm.EMAIL)
);

//example 2
//Add `EditText` or `TextInputEditText` with `TextInputLayout`.
validator.addView(
    new FormInput(formNamaParent, formNama)
); // Default TypeForm.TEXT

//example3
//Add `EditText` or `TextInputEditText` with `TextInputLayout` and custom `Rule`.
validator.addView(
    new FormInput(formNamaParent, formNama),
    new Rule(TypeForm.TEXT)
);
```

Read More Custom [**Rule**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/README_RULE.md)

|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example12.jpg" width="400"/>|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example13.jpg" width="400"/>|
|---|---|
|Example 1| Example 2 & Example 3|

**FullCode**
[**MainActivity**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/java/com/gzeinnumer/mylibformvalidation/MainActivity.java)
**&** [**XML**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/res/layout/activity_main.xml)
**&** [**Preview**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/README_VALIDATOR_NOT_REALTIME.md) **.**

#
### Validation Form RealTime

- **Import and start validation with make and object from class `ValidatorRealTime`.**
```java
TextInputEditText formUserName = = findViewById(R.id.form_username);
TextInputLayout formUserNameParent = findViewById(R.id.form_username_p);
Button btnSubmit = findViewById(R.id.btn_submit);

ValidatorRealTime validatorRealTime = new ValidatorRealTime();

validatorRealTime.addView(
    new FormInput(formUserNameParent, formUserName)
); // Default TypeForm.TEXT

validatorRealTime.build();

//Validate Realtime
validatorRealTime.observer(new ValidatorCallBack() {
    @Override
    public void result(boolean isDone) {
        //true if validate success
        //false if validate failed

        btnSubmit.setEnabled(isDone);
    }
});

//Trigger with onClickListener
btnValidate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        boolean result = validatorRealTime.getResult();
        //true if validate success
        //false if validate failed
        if (result){
            Toast.makeText(SecondActivity.this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SecondActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
});
```

#
- Delete `form view` from validate process

Put your `EditText` or `TextInputEditText` to `removeView(view)`.
```java
validatorRealTime.removeView(formNama);
```

#
- Add view with custom configuration
```java
//example 1
//Add `EditText` or `TextInputEditText` with custom `Rule`.
validatorRealTime.addView(
    formUserName,
    new Rule(TypeForm.EMAIL)
);

//example 2
//Add `EditText` or `TextInputEditText` with `TextInputLayout`.
validatorRealTime.addView(
    new FormInput(formUserNameParent, formUserName)
); // Default TypeForm.TEXT

//example3
//Add `EditText` or `TextInputEditText` with `TextInputLayout` and custom `Rule`.
validatorRealTime.addView(
    new FormInput(formUserNameParent, formUserName),
    new Rule(TypeForm.TEXT)
);
```
Read More Custom [**Rule**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/README_RULE.md)

|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example24.jpg" width="400"/>|<img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example25.jpg" width="400"/>|
|---|---|

**FullCode**
[**SecondActivity**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/java/com/gzeinnumer/mylibformvalidation/SecondActivity.java)
**&** [**XML**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/app/src/main/res/layout/activity_second.xml)
**&** [**Preview**](https://github.com/gzeinnumer/MyLibFormValidation/blob/master/README_VALIDATOR_REALTIME.md) **.**

---
# Example Code/app

[Sample Code And App](https://github.com/gzeinnumer/MyLibFormValidationExample)

---
# Version
- **1.0.0**
  - First Release
- **1.0.1**
  - Add TEXT_NO_SYMBOL
- **1.0.4**
  - Support TextInputLayout
- **1.0.5**
  - Support Delete
- **1.0.6**
  - Bug Fixing
- **1.1.6**
  - Add Feature Disable Start With Space
- **1.1.7**
  - Bug Fixing

---
# Contribution
You can sent your constibution to `branche` `master`.

---

```
Copyright 2020 M. Fadli Zein
```
