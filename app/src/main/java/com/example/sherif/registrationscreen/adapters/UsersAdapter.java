package com.example.sherif.registrationscreen.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
import com.example.sherif.registrationscreen.MyUsers;
import com.example.sherif.registrationscreen.R;

import io.realm.RealmResults;


/**
 * Created by Sherif on 26/01/2017.
 */

public class UsersAdapter extends BaseAdapter {

    private final Activity context;
    private RealmResults<MyUsers> results;

    public UsersAdapter(Activity context,RealmResults<MyUsers> results) {
        this.context = context;
        this.results=results;
    }

    private static class ViewHolder
    {
        TextView tvPersonName;
    }


    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public MyUsers getItem(int i) {
        return results.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inflate_list_item,viewGroup,false);

            final UsersAdapter.ViewHolder  viewHolder = new UsersAdapter.ViewHolder();
            viewHolder.tvPersonName = (TextView) view.findViewById(R.id.tvPersonName);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }
        final MyUsers current = getItem(i);
        UsersAdapter.ViewHolder holdr = (UsersAdapter.ViewHolder) view.getTag();
        holdr.tvPersonName.setText(current.getName());
        return view;
    }


}
