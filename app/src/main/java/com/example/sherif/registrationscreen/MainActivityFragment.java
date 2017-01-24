package com.example.sherif.registrationscreen;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static io.realm.Realm.getDefaultInstance;

/**
 * A placeholder fragment containing a simple view.
 */

public class MainActivityFragment extends Fragment {

    //    private static final int REQUEST_CODE_PERMISSION = 2;
//    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    Button registeration, dateOfBirth, sendLocation;
    EditText personName, personEmail, personPassword, personConfirmPassword;
    TextView uploadPic;
    ImageView imgView, recordVoice;
    TextInputLayout personEmailLayout, personPasswordLayout, personNameLayout, PersonConfirmPasswordLayout;
    private static int RESULT_LOAD_IMAGE = 2;
    private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
    private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
    private static final String AUDIO_RECORDER_FOLDER = "TestAppAudioRecorder";
    private MediaRecorder recorder = null;
    private int currentFormat = 0;
    private int output_formats[] = {MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP};
    private String file_exts[] = {AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP};
//    public static final int RequestPermissionCode = 1;
    private Realm mRealm;
    private boolean isActionDown = false;


    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        personName = (EditText) view.findViewById(R.id.input_name);
        personNameLayout = (TextInputLayout) view.findViewById(R.id.input_name_layout);
        personEmail = (EditText) view.findViewById(R.id.input_email);
        personEmailLayout = (TextInputLayout) view.findViewById(R.id.input_email_layout);
        personPassword = (EditText) view.findViewById(R.id.input_password);
        personPasswordLayout = (TextInputLayout) view.findViewById(R.id.input_password_layout);
        personConfirmPassword = (EditText) view.findViewById(R.id.input_confirmpassword);
        PersonConfirmPasswordLayout = (TextInputLayout) view.findViewById(R.id.input_confirmpassword_layout);
        uploadPic = (TextView) view.findViewById(R.id.pickPic);
        imgView = (ImageView) view.findViewById(R.id.picImgView);
        recordVoice = (ImageView) view.findViewById(R.id.recordImageView);
        dateOfBirth = (Button) view.findViewById(R.id.date_button);
        sendLocation = (Button) view.findViewById(R.id.btn_location);
        registeration = (Button) view.findViewById(R.id.btn_signup);


        inputValidations userName = new inputValidations(personName, personNameLayout, registeration, 4);
        inputValidations email = new inputValidations(personEmail, personEmailLayout, registeration);
        inputValidations password = new inputValidations(personPassword, personPasswordLayout, 4, personConfirmPassword, PersonConfirmPasswordLayout, registeration);
        inputValidations confirmPassword = new inputValidations(personConfirmPassword, PersonConfirmPasswordLayout, personPassword, registeration);
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

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


        recordVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                    // TODO Auto-generated method stub
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_UP:
                            AppLog.logString("stop Recording");
                            stopRecording();
                            break;
            }
                    return false;

                }
        });


        recordVoice.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                isActionDown = true;
                try {
                    if (isActionDown) {
                        startRecording();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("onLongPress Error ", e.toString());
                }
                return false;
            }
        });


        sendLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                gps = new GPSTracker(getActivity());
                // check if GPS enabled
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    // \n is for new line
//                    Toast.makeText(getActivity(), "Your Location is - \nLat: "+ latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getActivity(), MapsActivity.class);
                    Bundle b = new Bundle();
                    b.putDouble("lat", latitude);
                    b.putDouble("lon", longitude);
                    i.putExtras(b);
                    startActivityForResult(i, 1);

                } else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });
        registeration.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendFormValues();
            }

            private void sendFormValues() {
                if (TextUtils.isEmpty(personName.getText().toString().trim())) {
                    registeration.setEnabled(false);
                }
                if (TextUtils.isEmpty(personEmail.getText().toString().trim())) {
                    registeration.setEnabled(false);
                }
                if (TextUtils.isEmpty(personPassword.getText().toString().trim())) {
                    registeration.setEnabled(false);
                }
                if (TextUtils.isEmpty(personConfirmPassword.getText().toString().trim())) {
                    registeration.setEnabled(false);
                }
                 else{
//                    // Create an object
//                    mRealm = Realm.getDefaultInstance();
                    MyUsers person = new MyUsers();
                    // Set its fields
                    person.setName(personName.getText().toString());
                    person.setEmail(personEmail.getText().toString());
                    person.setPassword(personPassword.getText().toString());
                    addDataToRealm(person);
                }
            }
        });
        return view;
    }

    private void addDataToRealm(MyUsers model) {
        mRealm.beginTransaction();
//        MyUsers person = mRealm.createObject(MyUsers.class,UUID.randomUUID().toString());
        MyUsers person = mRealm.createObject(MyUsers.class);
        person.setName(model.getName());
        person.setEmail(model.getEmail());
        person.setPassword(model.getPassword());
        mRealm.commitTransaction();
    }


    /* Get the real path from the URI */

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


    private String getFilename() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, AUDIO_RECORDER_FOLDER);

        if (!file.exists()) {
            file.mkdirs();
        }

        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[currentFormat]);
    }


    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(output_formats[currentFormat]);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(getFilename());
        recorder.setOnErrorListener(errorListener);
        recorder.setOnInfoListener(infoListener);

        try {
            recorder.prepare();
            vibrate();
            recorder.start();
            Toast.makeText(getActivity(),"Start Recording",Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener() {
        @Override
        public void onError(MediaRecorder mr, int what, int extra) {
            AppLog.logString("Error: " + what + ", " + extra);
        }
    };

    private MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener() {
        @Override
        public void onInfo(MediaRecorder mr, int what, int extra) {
            AppLog.logString("Warning: " + what + ", " + extra);
        }
    };

    private void stopRecording() {
        if (null != recorder) {
            recorder.stop();
            recorder.reset();
            recorder.release();
            vibrate();
            Toast.makeText(getActivity(),"Stop and save Recording in external storage",Toast.LENGTH_SHORT).show();

            recorder = null;
        }
    }

    private void vibrate() {
        // TODO Auto-generated method stub
        try {
            Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
//                WRITE_EXTERNAL_STORAGE);
//        int result1 = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
//                RECORD_AUDIO);
//        return result == PackageManager.PERMISSION_GRANTED &&
//                result1 == PackageManager.PERMISSION_GRANTED;
//    }
//    private void requestPermission() {
//        MainActivityFragment.RequestPermissionCode(getActivity().this, new
//                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
//    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("lat-long");
//                Toast.makeText(getActivity(),strEditText,Toast.LENGTH_SHORT).show();
                sendLocation.setText(strEditText);
            }
        }
        if (requestCode == 2) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Get the path from the Uri
                String path = getPathFromURI(selectedImageUri);
                Log.i(TAG, "Image Path : " + path);
                // Set the image in ImageView
                imgView.setImageURI(selectedImageUri);
                imgView.setMaxWidth(100);
                imgView.setMaxHeight(100);
            }
        }


    }

}