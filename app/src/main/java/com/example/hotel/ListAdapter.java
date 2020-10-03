package com.example.hotel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {


        private Activity mContext;
        List<Reservation>ListReserve;
    public Button delBtn1;
        public ListAdapter(Activity mContext, List<Reservation> ListReserve){
            super(mContext,R.layout.activity_list_room,ListReserve);
            this.mContext = mContext;
            this.ListReserve =ListReserve;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = mContext.getLayoutInflater();
            View listReservationView = inflater.inflate(R.layout.activity_list_room,null,true);

            TextView checkIn = listReservationView.findViewById(R.id.checkIn);
            TextView checkOut = listReservationView.findViewById(R.id.checkOut);
            TextView Roomname = listReservationView.findViewById(R.id.Roomname);
            TextView NoOfRooms = listReservationView.findViewById(R.id.NoOfRooms);
            TextView noOfkids = listReservationView.findViewById(R.id.noOfKids);
            TextView noOfAdults = listReservationView.findViewById(R.id.noOfAdults);
            TextView Name7 = listReservationView.findViewById(R.id.Name7);
            TextView Email3 = listReservationView.findViewById(R.id.Email3);
            TextView ContactNo3 = listReservationView.findViewById(R.id.ContactNo3);

            Reservation reservation = ListReserve.get(position);

            checkIn.setText(reservation.getCheckin());
            checkOut.setText(reservation.getCheckout());
            Roomname.setText(reservation.getRname());
            NoOfRooms.setText(reservation.getRooms());
            noOfkids.setText(reservation.getAdults());
            noOfAdults.setText(reservation.getKids());
            Name7.setText(reservation.getName());
            Email3.setText(reservation.getEmail());
            ContactNo3.setText(reservation.getContactNo());

            return listReservationView;
        }
    }

