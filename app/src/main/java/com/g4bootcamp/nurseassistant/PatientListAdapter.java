package com.g4bootcamp.nurseassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class PatientListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Patient> mDataSource;

    public PatientListAdapter(Context context, ArrayList<Patient> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.patient_list_item, parent, false);


        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.patient_list_name);


        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.patient_list_room);


        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.patient_list_floor);


        titleTextView.setText(mDataSource.get(position).getFirstname() + " " + mDataSource.get(position).getLastname());

        subtitleTextView.setText(String.valueOf(mDataSource.get(position).getRoom()));

        detailTextView.setText(String.valueOf(mDataSource.get(position).getFloor()));
        return rowView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
