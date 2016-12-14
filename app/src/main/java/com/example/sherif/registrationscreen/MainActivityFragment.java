package com.example.sherif.registrationscreen;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment{

    Button registeration;
    Button dateOfBirth;
    EditText personName;
    EditText personEmail;
    EditText personPassword;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        personName = (EditText)view.findViewById(R.id.input_name);
        personEmail = (EditText)view.findViewById(R.id.input_email);
        personPassword = (EditText)view.findViewById(R.id.input_password);
        dateOfBirth = (Button) view.findViewById(R.id.date_button);
        registeration = (Button) view.findViewById(R.id.btn_signup);

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BirthDatAction();
            }

            private void BirthDatAction() {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        dateOfBirth.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        registeration.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View view) {
                validationForm();
            }

            private void validationForm() {
                if (personName.getText().toString().length()==0)
                {
                    personName.setError("The name is required");
                    personName.requestFocus();
                }
                if (personEmail.getText().toString().length()==0)
                {
                    personEmail.setError("The Email is required");
                    personEmail.requestFocus();
                }
                if (personPassword.getText().toString().length()==0)
                {
                    personPassword.setError("The Password is required");
                    personPassword.requestFocus();
                }
                else {
                    Toast.makeText(getActivity().getBaseContext(), "Success", Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;


    }

}
