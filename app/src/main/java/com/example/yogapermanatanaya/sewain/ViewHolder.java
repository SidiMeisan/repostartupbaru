package com.example.yogapermanatanaya.sewain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ViewHolder extends RecyclerView.ViewHolder{

    View mView;

    public ViewHolder(View itemView) {
        super(itemView);
        mView=itemView;


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
    }
    public void setDetails(Context ctx,String image, String namaItem, String harga, String durasi, String lokasi, String meetup){
        TextView mNamaSewa=mView.findViewById(R.id.txtNamaSewa);
        ImageView mSelectImage=mView.findViewById(R.id.selectImage);
        TextView mHargaSewa=mView.findViewById(R.id.hargaSewa);
        TextView mDurasi=mView.findViewById(R.id.durasi);
        TextView mLokasi=mView.findViewById(R.id.lokasi);
        TextView mMeet=mView.findViewById(R.id.meetup);


        //set data to views
        mNamaSewa.setText(namaItem);
        mLokasi.setText(lokasi);
        mMeet.setText(meetup);
        mDurasi.setText(durasi);
        mHargaSewa.setText(harga);



        Picasso.get().load(image).into(mSelectImage);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);

    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
