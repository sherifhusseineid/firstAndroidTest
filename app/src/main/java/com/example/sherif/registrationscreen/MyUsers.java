package com.example.sherif.registrationscreen;

import java.lang.reflect.Array;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Sherif on 17/01/2017.
 */

public class MyUsers extends RealmObject {
//    @PrimaryKey
//    private String id;
    @Required
    private String name;
    @Required
    private String email;
    @Required
    private  String password;
    @Required
    private String subscribe;

    private String favMovies;

    private byte[] profilePic;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public String getId () {return  id;}
//
//    public void setId (String id) {this.id = id;}

    public String getEmail() {return  email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return  password;}

    public void setPassword(String password) {this.password = password;}

    public void setSubscribe(String subscribe)
    {
        this.subscribe = subscribe;
    }

    public String getSubscribe()
    {
        return subscribe;
    }

    public void setFavMovies(String favMovies) {this.favMovies = favMovies;}

    public String getFavMovies() {return favMovies;}

    public void setProfilePic(byte[] profilePic) {this.profilePic = profilePic;}

    public byte[] getProfilePic() {return profilePic;}



}