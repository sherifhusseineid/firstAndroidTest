package com.example.sherif.registrationscreen;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ShowUserDetails extends AppCompatActivity {

    TextView name,email,password,subscribe,favMovies,birthDate;
    EditText editName,editEmail,editPassword;
    ImageView profilePic;
    private LinearLayout showDetails;
    private  LinearLayout editUser;
    private Realm mRealm;
    static ShowUserDetails instance;


    public ShowUserDetails() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);
//        showDetails = (LinearLayout) findViewById(R.id.viewUserDetails);
//        editUser = (LinearLayout) findViewById(R.id.editUserDetails);
        mRealm = Realm.getDefaultInstance();
        instance = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null)
        {
            Intent userDetails = getIntent();
            String userName = userDetails.getStringExtra("userName");
            String userEmail = userDetails.getStringExtra("email");
            String userPassword = userDetails.getStringExtra("password");
            String userSubscribe = userDetails.getStringExtra("subscribe");
            String userFavMovies = userDetails.getStringExtra("favMovies");
            String userBirthDate = userDetails.getStringExtra("birthDate");
//        String userProfilePic = userDetails.getStringExtra("profilePic");
//        Bitmap bitmap = (Bitmap) userDetails.getParcelableExtra("profilePic");

            byte[] byteArray = getIntent().getByteArrayExtra("profilePic");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        subscribe = (TextView) findViewById(R.id.subscribe);
        favMovies = (TextView) findViewById(R.id.favMovies);
        profilePic = (ImageView) findViewById(R.id.display_image);
//        birthDate = (TextView) findViewById(R.id.birthDate);
       // editUser.setVisibility(LinearLayout.GONE);
        name.setText(userName);
        email.setText(userEmail);
        password.setText(userPassword);
        subscribe.setText(userSubscribe);
        favMovies.setText(userFavMovies);
        profilePic.setImageBitmap(bmp);
    }
        else{
           // showDetails.setVisibility(LinearLayout.GONE);
            editName = (EditText) findViewById(R.id.input_name);
            editEmail = (EditText) findViewById(R.id.input_email);
            editPassword = (EditText) findViewById(R.id.input_password);
        }

//        birthDate.setText(userBirthDate);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public static  ShowUserDetails getInstance()
    {
        return instance;
    }


}
