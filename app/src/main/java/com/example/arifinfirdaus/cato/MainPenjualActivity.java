package com.example.arifinfirdaus.cato;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainPenjualActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private FloatingActionButton fabAdd;
    private FloatingActionButton fabEditDecription;
    private FloatingActionButton fabAddPhoto;
    private FloatingActionButton fab_edit_cover_photo;

    private Animation fabOpen;
    private Animation fabClose;
    private Animation fabRotateClockwise;
    private Animation fabRotateAntiClockwise;

    private boolean isFabAlreadyOpen = false;

    private Animation flOpen;
    private Animation flClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_penjual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupFab();
    }

    private void setupFab() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_home_add);
        fabEditDecription = (FloatingActionButton) findViewById(R.id.fab_edit_description);
        fabAddPhoto = (FloatingActionButton) findViewById(R.id.fab_add_photo);
        fab_edit_cover_photo = (FloatingActionButton) findViewById(R.id.fab_edit_cover_photo);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabRotateClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabRotateAntiClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fabAdd.setOnClickListener(this);
        fabEditDecription.setOnClickListener(this);
        fabAddPhoto.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
        fab_edit_cover_photo.setOnClickListener(this);

        flOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.framelayout_focus_fab_open);
        flClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.framelayout_focus_fab_close);
    }

    @Override
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
        getMenuInflater().inflate(R.menu.main_penjual, menu);
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

        if (id == R.id.nav_remove_account_penjual) {
            // Handle the camera action
        } else if (id == R.id.nav_logout_penjual) {
            handleLogout();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleLogout() {
        FirebaseAuth.getInstance().signOut();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }


//        /** on your logout method:**/
//        Intent broadcastIntent = new Intent();
//        broadcastIntent.setAction("com.package.ACTION_LOGOUT");
//        sendBroadcast(broadcastIntent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_home_add:
                // klo buka, maka di close
                if (this.isFabAlreadyOpen) {
                    fabEditDecription.startAnimation(fabClose);
                    fabAddPhoto.startAnimation(fabClose);
                    fab_edit_cover_photo.startAnimation(fabClose);
                    fabAdd.startAnimation(fabRotateAntiClockwise);
                    fabEditDecription.setClickable(false);
                    fabAddPhoto.setClickable(false);
                    fab_edit_cover_photo.setClickable(false);
                    fabAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                    isFabAlreadyOpen = false;

                } else {
                    // di buka (expand)
                    fab_edit_cover_photo.startAnimation(fabOpen);
                    fabAddPhoto.startAnimation(fabOpen);
                    fabEditDecription.startAnimation(fabOpen);
                    fabAdd.startAnimation(fabRotateClockwise);
                    fabAddPhoto.setClickable(true);
                    fabEditDecription.setClickable(true);
                    fab_edit_cover_photo.setClickable(true);
                    fabAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fab_home_pressed)));
                    isFabAlreadyOpen = true;
                }
                break;

            case R.id.fab_edit_description:
                Toast.makeText(this, "add friend", Toast.LENGTH_SHORT).show();

                // tutup fab
                fab_edit_cover_photo.startAnimation(fabClose);
                fab_edit_cover_photo.setClickable(false);
                fabEditDecription.startAnimation(fabClose);
                fabAddPhoto.startAnimation(fabClose);
                fabAdd.startAnimation(fabRotateAntiClockwise);
                fabEditDecription.setClickable(false);
                fabAddPhoto.setClickable(false);
                fabAdd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                isFabAlreadyOpen = false;
                break;

            case R.id.fab_add_photo:

                // tutup fab
                fab_edit_cover_photo.startAnimation(fabClose);
                fab_edit_cover_photo.setClickable(false);
                fabEditDecription.startAnimation(fabClose);
                fabAddPhoto.startAnimation(fabClose);
                fabAdd.startAnimation(fabRotateAntiClockwise);
                fabEditDecription.setClickable(false);
                fabAddPhoto.setClickable(false);
                fabAdd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                isFabAlreadyOpen = false;
                break;

            case R.id.fab_edit_cover_photo:
                // tutup fab
                fab_edit_cover_photo.startAnimation(fabClose);
                fabEditDecription.startAnimation(fabClose);
                fabAddPhoto.startAnimation(fabClose);
                fabAdd.startAnimation(fabRotateAntiClockwise);
                fabEditDecription.setClickable(false);
                fab_edit_cover_photo.setClickable(false);
                fabAddPhoto.setClickable(false);
                fabAdd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                isFabAlreadyOpen = false;
                break;
        }

    }


}
