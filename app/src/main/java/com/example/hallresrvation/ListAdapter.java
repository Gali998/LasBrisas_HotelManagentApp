package com.example.hallresrvation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Hall> hallList;

    public ListAdapter(Activity mContext, List<Hall> hallList){
      super(mContext,R.layout.list_hall,hallList);
      this.mContext = mContext;
      this.hallList = hallList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listHallView = inflater.inflate(R.layout.list_hall,null,true);

        TextView hallName = listHallView.findViewById(R.id.hallName);
        TextView eventType = listHallView.findViewById(R.id.eventType);
        TextView NoGuest = listHallView.findViewById(R.id.NoGuest);
        TextView eventTime = listHallView.findViewById(R.id.timeE);
        TextView NameGuest = listHallView.findViewById(R.id.NameGuest);
        TextView ContactNo = listHallView.findViewById(R.id.ContactNo);
        TextView Email = listHallView.findViewById(R.id.Email);
        TextView Address = listHallView.findViewById(R.id.Address);

        Hall hall = hallList.get(position);

        hallName.setText(hall.getHallName());
        eventType.setText(hall.getTypeEvent());
        NoGuest.setText(hall.getNoOfGuest());
        eventTime.setText(hall.getTime());
        NameGuest.setText(hall.getGuestName());
        ContactNo.setText(hall.getContactNo());
        Email.setText(hall.getEmail());
        Address.setText(hall.getAddress());

        return listHallView;
    }
}
