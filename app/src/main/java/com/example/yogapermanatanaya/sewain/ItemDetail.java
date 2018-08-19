package com.example.yogapermanatanaya.sewain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetail extends AppCompatActivity {

    TextView mNamaItem, mHarga, mDurasi, mLokasi, mMeetup;
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);


        mNamaItem=findViewById(R.id.txtNamaSewa);
        mHarga=findViewById(R.id.hargaSewa);
        mDurasi=findViewById(R.id.durasi);
        mLokasi=findViewById(R.id.lokasi);
        mMeetup=findViewById(R.id.meetup);
        mImage=findViewById(R.id.selectImage);

        byte[] bytes=getIntent().getByteArrayExtra("image");
        String txtNamaSewa=getIntent().getStringExtra("Nama Item");
        String hargaSewa=getIntent().getStringExtra("Harga");
        String durasi=getIntent().getStringExtra("Durasi");
        String lokasi=getIntent().getStringExtra("Durasi");
        String meetup=getIntent().getStringExtra("Meetup");
        String description=getIntent().getStringExtra("Deskripsi");
        Bitmap bmp= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        mNamaItem.setText(txtNamaSewa);
        mHarga.setText(hargaSewa);
        mDurasi.setText(durasi);
        mLokasi.setText(lokasi);
        mMeetup.setText(meetup);
        mImage.setImageBitmap(bmp);


    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}
