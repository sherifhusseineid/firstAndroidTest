package com.example.sherif.registrationscreen;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */

public class MainActivityFragment extends Fragment {

//    private static final int REQUEST_CODE_PERMISSION = 2;
//    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;

    Button registeration,dateOfBirth,sendLocation;
    EditText personName,personEmail,personPassword,personConfirmPassword;
    TextInputLayout personEmailLayout,personPasswordLayout,personNameLayout,PersonConfirmPasswordLayout;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        personName = (EditText)view.findViewById(R.id.input_name);
        personNameLayout = (TextInputLayout)view.findViewById(R.id.input_name_layout);
        personEmail = (EditText)view.findViewById(R.id.input_email);
        personEmailLayout = (TextInputLayout) view.findViewById(R.id.input_email_layout);
        personPassword = (EditText)view.findViewById(R.id.input_password);
        personPasswordLayout = (TextInputLayout) view.findViewById(R.id.input_password_layout);
        personConfirmPassword = (EditText)view.findViewById(R.id.input_confirmpassword);
        PersonConfirmPasswordLayout= (TextInputLayout) view.findViewById(R.id.input_confirmpassword_layout);
        dateOfBirth = (Button) view.findViewById(R.id.date_button);
        sendLocation = (Button) view.findViewById(R.id.btn_location);
        registeration = (Button) view.findViewById(R.id.btn_signup);

        inputValidations userName = new inputValidations(personName,personNameLayout,4);
        inputValidations email = new inputValidations(personEmail,personEmailLayout);
        inputValidations password = new inputValidations(personPassword,personPasswordLayout,4,personConfirmPassword,PersonConfirmPasswordLayout);
        inputValidations confirmPassword = new inputValidations(personConfirmPassword,PersonConfirmPasswordLayout,personPassword);
        userName.validateUserName();
        email.validateEmail();
        password.validatePassword();
        confirmPassword.validateConfirmPassword();

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
        sendLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                gps = new GPSTracker(getActivity());
                // check if GPS enabled
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    // \n is for new line
//                    Toast.makeText(getActivity(), "Your Location is - \nLat: "+ latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getActivity(),MapsActivity.class);
                    Bundle b = new Bundle();
                    b.putDouble("lat", latitude);
                    b.putDouble("lon", longitude);
                    i.putExtras(b);
                    startActivity(i);

                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });

//        registeration.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                validationForm();
//            }
//
//            private void validationForm() {
//                if (personName.getText().toString().length()==0)
//                {
//                    personName.setError("The name is required");
//                    personName.requestFocus();
//                }
//                if (personEmail.getText().toString().length()==0)
//                {
//                    personEmail.setError("The Email is required");
//                    personEmail.requestFocus();
//                }
//                else {
//                    Toast.makeText(getActivity().getBaseContext(), "Success", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
        return view;
    }


}