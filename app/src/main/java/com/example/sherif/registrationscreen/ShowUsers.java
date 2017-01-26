package com.example.sherif.registrationscreen;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.internal.Context;

public class ShowUsers extends AppCompatActivity {
   private Realm realm;
//    RealmList<MyUsers> listOfUsers = new RealmList<>();
    RealmResults<MyUsers> results;

    TextView name,email,password;

    ListView lvPersonNameList;
    private static ArrayList<MyUsers> personDetailsModelArrayList = new ArrayList<>();
    // PersonDetailsAdapter personDetailsAdapter;
     static ShowUsers instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build();
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        results = realm.where(MyUsers.class).findAll();

//        name = (TextView) findViewById(R.id.name);
//        email = (TextView) findViewById(R.id.email);
//        password = (TextView) findViewById(R.id.password);
//        for(MyUsers users : results)
//        {
//            name.setText(users.getName());
//            email.setText(users.getEmail());
//            password.setText(users.getPassword());
//        }
//        MyUsers[] resultArray = (MyUsers[]) results.toArray();
//
//        name.setText(resultArray.getClass().getName());

         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



//        MyUsers[] resultArray = (MyUsers[]) allUsers.toArray();
//        for (int i=0;i<allUsers.size();i++)
//        {
//            listOfUsers.add(allUsers.get(i));
//        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



//    private void setPersonDetailsAdapter() {
//        personDetailsAdapter = new PersonDetailsAdapter(MainActivity.this, personDetailsModelArrayList);
//        lvPersonNameList.setAdapter(personDetailsAdapter);
//    }

//    private void bindWidgetsWithEvents() {
//        lvPersonNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(MainActivity.this,PersonDetailsActivity.class);
//                intent.putExtra("PersonID", personDetailsModelArrayList.get(position).getId());
//                startActivity(intent);
//            }
//        });
//    }

    private void getAllUsers() {
        results = realm.where(MyUsers.class).findAll();

        realm.beginTransaction();

        for (int i = 0; i < results.size(); i++) {
            personDetailsModelArrayList.add(results.get(i));
        }
        realm.commitTransaction();
        //personDetailsAdapter.notifyDataSetChanged();
    }



}

