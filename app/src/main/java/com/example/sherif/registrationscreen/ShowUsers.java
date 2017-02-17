package com.example.sherif.registrationscreen;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherif.registrationscreen.adapters.UsersAdapter;
import com.google.android.gms.maps.model.MarkerOptions;

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

import static android.R.attr.id;
import static android.content.ContentValues.TAG;

public class ShowUsers extends AppCompatActivity {

    RealmResults<MyUsers> results;
    ListView lvPersonNameList;
    TextView tvPersonName;
    ImageView delete;
    Realm realm;
    private static ShowUsers instance;

    public ShowUsers() {

    }

    public static  ShowUsers getInstance()
    {
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvPersonNameList =  (ListView) findViewById(R.id.lvPersonNameList);
        tvPersonName = (TextView) findViewById(R.id.tvPersonName);
        delete = (ImageView) findViewById(R.id.cell_trash_button);
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
//                intent.putExtra("birthDate",user.getBirthDate());
                startActivity(intent);

            }
        });





        //adapter.notifyDataSetChanged();
//        lvPersonNameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                MyUsers deleteUser = adapter.getItem(i);
//                realm.beginTransaction();
//
////                RealmResults<MyUsers> result1 = realm.where(MyUsers.class).equalTo("id",i).findAll();
////                result1.deleteAllFromRealm();
//                results = realm.where(MyUsers.class).equalTo("id",deleteUser.getId()).findAll();
//                results.deleteAllFromRealm();
//                realm.commitTransaction();
//                adapter.notifyDataSetChanged();
//                return true;
//            }
//        });




    }

    public MyUsers searchPerson(int personId)
    {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<MyUsers> resultsUser = realm.where(MyUsers.class).equalTo("id", personId).findAll();
        realm.commitTransaction();
        return resultsUser.get(0);
    }

    public void addOrUpdatePersonDetailsDialog(final MyUsers model,final int position)
    {

        Toast.makeText(ShowUsers.this, "Ya rab" + model, Toast.LENGTH_LONG).show();
//      editName.setText(model.getName());
//        personPassword.setText(model.getPassword());
    }



}