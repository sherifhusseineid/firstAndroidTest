package com.example.sherif.registrationscreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

public class ShowUserDetails extends AppCompatActivity {

    TextView name,email,password,subscribe,favMovies;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent userDetails = getIntent();
        String userName = userDetails.getStringExtra("userName");
        String userEmail = userDetails.getStringExtra("email");
        String userPassword = userDetails.getStringExtra("password");
        String userSubscribe = userDetails.getStringExtra("subscribe");
        String userFavMovies = userDetails.getStringExtra("favMovies");
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
        name.setText(userName);
        email.setText(userEmail);
        password.setText(userPassword);
        subscribe.setText(userSubscribe);
        favMovies.setText(userFavMovies);
        profilePic.setImageBitmap(bmp);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
