package com.example.sherif.registrationscreen;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherif.registrationscreen.adapters.UsersAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.internal.Context;

public class ShowUsers extends AppCompatActivity {
   private Realm realm;
    RealmResults<MyUsers> results;
    ListView lvPersonNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvPersonNameList =  (ListView) findViewById(R.id.lvPersonNameList);
        final UsersAdapter adapter = new UsersAdapter(this,realm.where(MyUsers.class).findAll());
//        Map<String,String> userMap;

        lvPersonNameList.setAdapter(adapter);
        lvPersonNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyUsers user = adapter.getItem(i);
//                Toast.makeText(ShowUsers.this, "Your "+ user.getEmail(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ShowUsers.this,ShowUserDetails.class);
                intent.putExtra("userName", user.getName());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("password", user.getPassword());
                intent.putExtra("subscribe",user.getSubscribe());
                intent.putExtra("favMovies",user.getFavMovies());
                intent.putExtra("profilePic",user.getProfilePic());
                startActivity(intent);

            }
        });

    }
}