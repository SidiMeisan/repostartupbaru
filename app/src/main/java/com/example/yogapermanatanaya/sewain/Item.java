package com.example.yogapermanatanaya.sewain;

import android.widget.ImageView;
import android.widget.Spinner;

public class Item {

    String imageAddress;
    String namaItem;
    String harga;
    String durasi;
    String lokasi;
    String meetup;

    public Item(){

    }


    public String getImageAddress() {
        return imageAddress;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public String getHarga() {
        return harga;
    }

    public String getDurasi() {
        return durasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getMeetup() {
        return meetup;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }


    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setMeetup(String meetup) {
        this.meetup = meetup;
    }
}
