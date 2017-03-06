package com.example.sherif.registrationscreen.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.example.sherif.registrationscreen.MyUsers;
import com.example.sherif.registrationscreen.R;
import com.example.sherif.registrationscreen.ShowUserDetails;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sherif on 24/02/2017.
 */

public class UserDetailsAdapter extends BaseAdapter{

    private final Activity context;
    private RealmResults<MyUsers> results;
    Realm realm;


    public UserDetailsAdapter(Activity context,RealmResults<MyUsers> results) {
        this.context = context;
        this.results = results;
    }

//    public UserDetailsAdapter(Activity context)
//    {
//        this.context = context;
//    }


    private static class ViewHolder
    {
        TextView name,email,password,subscribe,favMovies;
        ImageView display_image;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public MyUsers getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, final View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_user_details,viewGroup,false);

            final UserDetailsAdapter.ViewHolder viewHolder = new UserDetailsAdapter.ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.email = (TextView) view.findViewById(R.id.email);
            viewHolder.password = (TextView) view.findViewById(R.id.password);
            viewHolder.subscribe = (TextView) view.findViewById(R.id.subscribe);
            viewHolder.favMovies = (TextView) view.findViewById(R.id.favMovies);
            viewHolder.display_image = (ImageView) view.findViewById(R.id.display_image);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
        }


        final MyUsers currentResult = getItem(i);
//        Toast.makeText(view.getContext(), "ssssss"+currentResult.getId() , Toast.LENGTH_SHORT).show();
        UserDetailsAdapter.ViewHolder holder = (UserDetailsAdapter.ViewHolder) view.getTag();
        holder.name.setText(currentResult.getName());
        holder.email.setText(currentResult.getEmail());
        holder.password.setText(currentResult.getPassword());
        holder.subscribe.setText(currentResult.getSubscribe());
        holder.favMovies.setText(currentResult.getFavMovies());
        byte[] byteArray = currentResult.getProfilePic();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        holder.display_image.setImageBitmap(bmp);
        return view;
    }



}
