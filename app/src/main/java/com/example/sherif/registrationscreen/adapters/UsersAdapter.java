package com.example.sherif.registrationscreen.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.example.sherif.registrationscreen.MainActivity;
import com.example.sherif.registrationscreen.MainActivityFragment;
import com.example.sherif.registrationscreen.MyUsers;
import com.example.sherif.registrationscreen.R;
import com.example.sherif.registrationscreen.ShowUserDetails;
import com.example.sherif.registrationscreen.ShowUsers;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.sherif.registrationscreen.R.id.tvPersonName;


/**
 * Created by Sherif on 26/01/2017.
 */

public class UsersAdapter extends ArrayAdapter<MyUsers>{

    private final Activity context;
    private int personId;
    private int position;
    private RealmResults<MyUsers> results;
    private SparseBooleanArray mSelectedItemsIds;
    Realm realm;

//    public UsersAdapter(Context context,RealmResults<MyUsers> results) {
//        this.context = context;
//        this.results=results;
//    }

    public UsersAdapter(Activity context, int resource,RealmResults<MyUsers> results) {
        super(context, resource,results);
        this.context = context;
        this.results=results;
        mSelectedItemsIds = new SparseBooleanArray();

    }


    private static class ViewHolder
    {
        TextView tvPersonName;
        ImageView delete;
        ImageView edit;

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
    public View getView(final int i, final View convertView, final ViewGroup viewGroup) {
        View view;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inflate_list_item,viewGroup,false);

            final UsersAdapter.ViewHolder  viewHolder = new UsersAdapter.ViewHolder();
            viewHolder.tvPersonName = (TextView) view.findViewById(tvPersonName);
           // viewHolder.delete = (ImageView) view.findViewById(R.id.cell_trash_button);
           // viewHolder.edit = (ImageView) view.findViewById(R.id.ivEditPesonDetail);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }



        final MyUsers current = getItem(i);
        UsersAdapter.ViewHolder holdr = (UsersAdapter.ViewHolder) view.getTag();
        holdr.tvPersonName.setText(current.getName());
//        holdr.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                ShowUsers.deletePerson(current.getId());
//                realm = Realm.getDefaultInstance();
//                realm.beginTransaction();
//                results = realm.where(MyUsers.class).equalTo("id",current.getId()).findAll();
//                 results.deleteAllFromRealm();
//               // Toast.makeText(view.getContext(), "ssssss"+current.getId() , Toast.LENGTH_SHORT).show();
//                realm.commitTransaction();
//                notifyDataSetChanged();
//                context.startActivity(new Intent(context, ShowUsers.class));
//            }
//        });

//        holdr.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyUsers dataToEdit = ((ShowUsers)context).searchPerson(current.getId());
//                ((ShowUsers)context).addOrUpdatePersonDetailsDialog(dataToEdit,i);
//            }
//        });

        return view;
    }


    @Override
    public void remove(MyUsers object) {
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));

    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);

        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }


}
