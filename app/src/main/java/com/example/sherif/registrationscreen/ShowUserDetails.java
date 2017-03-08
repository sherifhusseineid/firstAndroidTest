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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherif.registrationscreen.adapters.UserDetailsAdapter;
import com.example.sherif.registrationscreen.adapters.UsersAdapter;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ShowUserDetails extends AppCompatActivity {

    TextView name, email, password, subscribe, favMovies, birthDate,user_profile_name;
    EditText editName, editEmail, editPassword;
    ImageView profilePic;
    ListView showUserInformation;
    private LinearLayout showDetails;
    private LinearLayout editUser;
    private Realm mRealm;
    static ShowUserDetails instance;
    static public String userIdKey = "userID";


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
        showUserInformation = (ListView) findViewById(R.id.showUserInformation);
        //final UserDetailsAdapter DetailsAdapter = new UserDetailsAdapter(this,mRealm.where(MyUsers.class).findAll());

        //if (getIntent().getExtras() != null) {
            Intent userDetails = getIntent();
            int userID = userDetails.getIntExtra(userIdKey, 0);
           //MyUsers current = mRealm.where(MyUsers.class).equalTo("id", userID).findFirst();
            //Toast.makeText(ShowUserDetails.this, "Your "+ current, Toast.LENGTH_LONG).show();
            name = (TextView) findViewById(R.id.name);
            email = (TextView) findViewById(R.id.email);
            password = (TextView) findViewById(R.id.password);
            subscribe = (TextView) findViewById(R.id.subscribe);
            favMovies = (TextView) findViewById(R.id.favMovies);
            user_profile_name = (TextView) findViewById(R.id.user_profile_name);
            profilePic = (ImageView) findViewById(R.id.display_image);

            final UserDetailsAdapter adapter = new UserDetailsAdapter(this,mRealm.where(MyUsers.class).equalTo("id", userID).findFirst());
            showUserInformation.setAdapter(adapter);
//          birthDate = (TextView) findViewById(R.id.birthDate);
            //   editUser.setVisibility(LinearLayout.GONE);

//            name.setText(current.getName());
//            email.setText(current.getEmail());
//            password.setText(current.getPassword());
//            subscribe.setText(current.getSubscribe());
//            favMovies.setText(current.getFavMovies());
//            byte[] byteArray = current.getProfilePic();
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            profilePic.setImageBitmap(bmp);
        //}

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

    public static ShowUserDetails getInstance() {
        return instance;
    }


}
