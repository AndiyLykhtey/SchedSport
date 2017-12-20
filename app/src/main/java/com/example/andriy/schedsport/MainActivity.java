package com.example.andriy.schedsport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    TextView txtWelcom;

    private NavigationView mNavigationView;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLogged();



        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initDrawer();
        setUserInfoDrawer();
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_schedule) {
            ScheduAdapter.start(this);
            finish();

        } else if (id == R.id.nav_gallery) {
            GalleryAdapter.start(this);
            finish();

        } else if (id == R.id.nav_chart) {

        } else if (id == R.id.nav_calc) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout){
            DashboardActivity.start(this);
            finish();
        } else if(id == R.id.nav_list){
            ListAdapter.start(this);
            //AddExercisesActivity.start(this);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkLogged(){
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null){
            LoginActivity.start(this);
            finish();
        }
    }


    public static void start(LoginActivity loginActivity) {
        Intent starter = new Intent(loginActivity,MainActivity.class);
        loginActivity.startActivity(starter);
    }


    public static void start(DashboardActivity dashboardActivity) {
        Intent starter = new Intent(dashboardActivity,MainActivity.class);
        dashboardActivity.startActivity(starter);
    }

    private void initDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public void setUserInfoDrawer(){
        auth = FirebaseAuth.getInstance();
        txtWelcom = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_user_email);
        if (auth.getCurrentUser() != null){
            txtWelcom.setText(auth.getCurrentUser().getEmail());
        }
    }

    public static void start(ScheduAdapter scheduAdapter) {
        Intent starter = new Intent(scheduAdapter,MainActivity.class);
        scheduAdapter.startActivity(starter);
    }
}
