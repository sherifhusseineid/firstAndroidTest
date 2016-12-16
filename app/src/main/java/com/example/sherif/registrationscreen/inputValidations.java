package com.example.sherif.registrationscreen;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sherif on 16/12/2016.
 */

public class inputValidations {

    EditText text;
    //String editText = text.getText().toString();
    TextInputLayout textLayout;
     int textlength=0;

    inputValidations(EditText cText , TextInputLayout cTextLayout){
        this.text = cText;
        this.textLayout = cTextLayout;
    }

     inputValidations(EditText cText , TextInputLayout cTextLayout , int cTextLength){
        this.text = cText;
        this.textLayout = cTextLayout;
        this.textlength = cTextLength;
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
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(text.getText().toString().trim()))
                {
                    textLayout.setError("MUST ENTER USERNAME");
                }

                else if(TextUtils.getTrimmedLength(text.getText().toString().trim()) < textlength)
                {
                    textLayout.setError("ENTER AT LEAST" + textlength +" WORDS");
                }

                else
                {
                    textLayout.setErrorEnabled(false);
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
                }
                else if( !Patterns.EMAIL_ADDRESS.matcher(text.getText().toString().trim()).matches())
                {
                    textLayout.setError("INVALID EMAIL");

                }
                else if(TextUtils.getTrimmedLength(text.getText().toString().trim()) < textlength)
                {
                    textLayout.setError("ENTER AT LEAST" + textlength +" WORDS");
                }
                else
                {
                    textLayout.setErrorEnabled(false);

                }
            }
        });
    }


}
