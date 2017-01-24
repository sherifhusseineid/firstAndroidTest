package com.example.sherif.registrationscreen;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static android.R.id.message;

/**
 * Created by Sherif on 16/12/2016.
 */

public class inputValidations  {

    EditText text,confirmText;
    TextInputLayout textLayout,confirmLayout;
    Button submit;
    int textlength=0;
    Pattern pattern;
    Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    inputValidations(EditText cText , TextInputLayout cTextLayout,Button cSubmit){
        this.text = cText;
        this.submit= cSubmit;
        this.textLayout = cTextLayout;
    }

     inputValidations(EditText cText , TextInputLayout cTextLayout ,Button cSubmit, int cTextLength){
         this.text = cText;
         this.submit= cSubmit;
         this.textLayout = cTextLayout;
         this.textlength = cTextLength;
    }

    inputValidations(EditText password , TextInputLayout cTextLayout , EditText confirmPassword,Button cSubmit){
        this.text = password;
        this.confirmText = confirmPassword;
        this.submit= cSubmit;
        this.textLayout = cTextLayout;
    }

    inputValidations(EditText cText , TextInputLayout cTextLayout , int cTextLength, EditText confirmPassword, TextInputLayout confirmLayOut,Button cSubmit){
        this.text = cText;
        this.textLayout = cTextLayout;
        this.textlength = cTextLength;
        this.confirmText = confirmPassword;
        this.confirmLayout = confirmLayOut;
        this.submit= cSubmit;
    }

    public void validateUserName()
    {

        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void  afterTextChanged(Editable editable) {

                if(TextUtils.isEmpty(text.getText().toString().trim()))
                {
                    textLayout.setError("MUST ENTER USERNAME");
                    submit.setEnabled(false);

                }

                else if(TextUtils.getTrimmedLength(text.getText().toString().trim()) < textlength)
                {
                    textLayout.setError("ENTER AT LEAST" + textlength +" WORDS");
                    submit.setEnabled(false);

                }

                else
                {
                    textLayout.setErrorEnabled(false);
                    submit.setEnabled(true);

                }
            }

        });

    }

    public void validateEmail()
    {

        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(text.getText().toString().trim()))
                {
                    textLayout.setError("MUST ENTER EMAIL ADDRESS");
                    submit.setEnabled(false);
                }
                else if( !Patterns.EMAIL_ADDRESS.matcher(text.getText().toString().trim()).matches())
                {
                    textLayout.setError("INVALID EMAIL");
                    submit.setEnabled(false);

                }
                else if(TextUtils.getTrimmedLength(text.getText().toString().trim()) < textlength)
                {
                    textLayout.setError("ENTER AT LEAST" + textlength +" WORDS");
                    submit.setEnabled(false);
                }
                else
                {
                    textLayout.setErrorEnabled(false);
                    submit.setEnabled(true);

                }
            }
        });
    }

    public void validatePassword()
    {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(text.getText().toString());
                if (confirmText.getText().toString().trim().matches("")){
                    if (TextUtils.isEmpty(text.getText().toString().trim())) {
                        textLayout.setError("MUST ENTER PASSWORD");
                    } else if (TextUtils.getTrimmedLength(text.getText().toString().trim()) < textlength) {
                        textLayout.setError("ENTER AT LEAST" + textlength + " WORDS");
                        submit.setEnabled(false);
                    } else if (matcher.matches() == false) {
                        textLayout.setError("PASSWORD MUST CONTAIN REGULAR EXPRESSION");
                        submit.setEnabled(false);
                    } else if (matcher.matches() == true) {
                        textLayout.setErrorEnabled(false);
                        submit.setEnabled(true);
                    }
            }
                else{
                    if(text.getText().toString().equals(confirmText.getText().toString()))
                    {
                       confirmLayout.setErrorEnabled(false);
                        submit.setEnabled(true);
                    }
                    else
                    {
                        confirmLayout.setError("PASSWORD DO NOT MATCH");
                        submit.setEnabled(false);
                    }
                }
            }
        });
    }

    public void validateConfirmPassword()
    {
        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(text.getText().toString().equals(confirmText.getText().toString()))
                {
                    textLayout.setErrorEnabled(false);
                    submit.setEnabled(true);
                }
                else
                {
                    textLayout.setError("PASSWORD DO NOT MATCH");
                    submit.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
              if(text.getText().toString().equals(confirmText.getText().toString()))
                {
                    textLayout.setErrorEnabled(false);
                    submit.setEnabled(true);
                }
                else
                {
                    textLayout.setError("PASSWORD DO NOT MATCH");
                    submit.setEnabled(false);
                }
            }
        });
    }
}