#
- **Custom `Rule`.**

Here some `Rule` that you can use.
```java
int minLength = 2;
String errorLengthMsg = "Form tidak boleh kosong";
String errorFormatMsg = "Format salah";

new Rule(TypeForm.TEXT)
new Rule(TypeForm.TEXT, minLength)
new Rule(TypeForm.TEXT, minLength, errorLengthMsg)
new Rule(TypeForm.TEXT, minLength, errorLengthMsg, errorFormatMsg)
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

```java
validator.addView(
        formNama,
        new Rule(TypeForm.EMAIL)
);
```

<p align="center">
    <img src="https://github.com/gzeinnumer/MyLibFormValidation/blob/master/preview/example18.jpg" width="400"/>
<p>

#
```
Copyright 2020 M. Fadli Zein
```