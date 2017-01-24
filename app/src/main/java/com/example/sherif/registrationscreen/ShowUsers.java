package com.example.sherif.registrationscreen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ShowUsers extends AppCompatActivity {
    private Realm realm;
    RealmResults<MyUsers> allUsers;
    TextView name,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build();
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        allUsers = realm.where(MyUsers.class).findAll();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);

        name.setText(allUsers.toString());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
