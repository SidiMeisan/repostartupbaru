package com.example.yogapermanatanaya.sewain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;

    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Get Recycler view
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);


        //Set Layout as Linear Layout...
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Read Query to Firebase Database ...
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Item");


    }

    //Load Data into Recycler view onstart


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Item, ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Item, ViewHolder>(
                        Item.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Item model, int position) {
                        viewHolder.setDetails(getApplicationContext(),
                                model.getImageAddress()
                                ,model.getNamaItem()
                                ,model.getHarga()
                                ,model.getDurasi()
                                ,model.getLokasi()
                                ,model.getMeetup());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //views
                                TextView mNamaItem=findViewById(R.id.txtNamaSewa);
                                ImageView mImage=findViewById(R.id.selectImage);
                                TextView mHarga=findViewById(R.id.hargaSewa);
                                TextView mDurasi=findViewById(R.id.durasi);
                                TextView mLokasi=findViewById(R.id.lokasi);
                                TextView mMeet=findViewById(R.id.meetup);

                                //get data from views
                                String txtNamaSewa=mNamaItem.getText().toString();
                                String durasi=mDurasi.getText().toString();
                                String hargaSewa=mHarga.getText().toString();
                                String lokasi=mLokasi.getText().toString();
                                String meetup=mMeet.getText().toString();
                                Drawable mDrawable=mImage.getDrawable();

                                Bitmap mBitmap=((BitmapDrawable)mDrawable).getBitmap();
                                Intent intent=new Intent(view.getContext(), ItemDetail.class);
                                ByteArrayOutputStream stream =new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes=stream.toByteArray();

                                intent.putExtra("image",bytes);
                                intent.putExtra("Item",txtNamaSewa);
                                intent.putExtra("Harga",hargaSewa);
                                intent.putExtra("Durasi",durasi);
                                intent.putExtra("Lokasi",lokasi);
                                intent.putExtra("Meet",meetup);

                                startActivity(intent);






                            }


                            @Override
                            public void onItemLongClick(View view, int position) {
                                    //Own action
                            }



                        });
                        return viewHolder;

                    }
                };

        //set adapter to recycler view...
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(Home.this, Profile.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Home.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {

    }
}
