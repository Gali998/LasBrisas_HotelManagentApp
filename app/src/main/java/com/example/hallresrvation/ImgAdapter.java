package com.example.hallresrvation;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ImgViewHolder> {

    private Context mContext;
    private List<Hall> mUploads;
    private onItemClickListener mListener;

    public  ImgAdapter(Context context,List<Hall> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_hall,parent,false);
        return new ImgViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgViewHolder holder, int position) {
       Hall hallCurrent = mUploads.get(position);
       holder.HName.setText(hallCurrent.getHallName());
       holder.EType.setText(hallCurrent.getTypeEvent());
       holder.NG.setText(hallCurrent.getNoOfGuest());
       holder.Time.setText(hallCurrent.getTime());
       holder.GName.setText(hallCurrent.getGuestName());
       holder.CN.setText(hallCurrent.getContactNo());
       holder.Email1.setText(hallCurrent.getEmail());
       holder.Adr.setText(hallCurrent.getAddress());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView HName,EType,NG,Time,GName,CN,Email1,Adr;

        public ImgViewHolder(@NonNull View itemView) {
            super(itemView);

            HName = itemView.findViewById(R.id.HName);
            EType = itemView.findViewById(R.id.EType);
            NG = itemView.findViewById(R.id.NG);
            Time = itemView.findViewById(R.id.Time);
            GName = itemView.findViewById(R.id.GName);
            CN = itemView.findViewById(R.id.CN);
            Email1 = itemView.findViewById(R.id.Email1);
            Adr = itemView.findViewById(R.id.Adr);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListener != null){
                int position=getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.setHeaderTitle("Select Action");

            MenuItem edit = contextMenu.add(contextMenu.NONE,1,1,"Edit");
            MenuItem delete = contextMenu.add(contextMenu.NONE,2,2,"Delete");


            edit.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (mListener != null){
                int position=getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){
                    switch (menuItem.getItemId()){
                        case 1 :
                            mListener.onEditClick(position);
                            return true;

                        case 2 :
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface onItemClickListener{
        void onItemClick(int position);
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){

        mListener = listener;
    }
}
