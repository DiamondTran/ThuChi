package com.diamond.diamond.thuchi.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.diamond.diamond.thuchi.R;
import com.diamond.diamond.thuchi.model.Thu;
import com.diamond.diamond.thuchi.sqldao.ThuDAO;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView img;
    private TextView tvnameuser;
    private TextView tvtmailuser,sodu;
    View hview;

    int a, tong = 0;
    ArrayList<Integer> integers = new ArrayList<>();
    private ThuDAO thuDao;
    private List<Thu> thus;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        tongthu();
        sodu= findViewById(R.id.sodu);
        sodu.setText(String.valueOf(tong));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        hview = navigationView.getHeaderView(0);
        tvnameuser = (TextView) hview.findViewById(R.id.tvname);
        tvtmailuser = (TextView) hview.findViewById(R.id.tvemail);
        img = (ImageView) hview.findViewById(R.id.imguser);
        navigationView.setNavigationItemSelectedListener(this);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            tvnameuser.setText(personName);
            tvtmailuser.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(img);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            startActivity(new Intent(MainActivity.this, ViActivity.class));
        } else if (id == R.id.chuyentien) {
            startActivity(new Intent(MainActivity.this, ChuyentienActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_chi) {
            startActivity(new Intent(MainActivity.this, ChiActivity.class));
        } else if (id == R.id.nav_thu) {
            startActivity(new Intent(MainActivity.this, ThuActivity.class));
        } else if (id == R.id.nav_tools) {
            signOut();
        } else if (id == R.id.nav_infor) {

        } else if (id == R.id.nav_thongke) {
            startActivity(new Intent(MainActivity.this, ChiActivity.class));
        } else if (id == R.id.nav_exit) {

            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Siged out successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    public void thu(View view) {
        startActivity(new Intent(MainActivity.this, ThuActivity.class));
    }

    public void chi(View view) {
        startActivity(new Intent(MainActivity.this, ChiActivity.class));
    }

    public void thongke(View view) {
        startActivity(new Intent(MainActivity.this, ThongkeActivity.class));
    }

    public void note(View view) {
        startActivity(new Intent(MainActivity.this, NoteActivity.class));
    }

    private void tongthu() {
        Thu thu;
        thuDao = new ThuDAO(MainActivity.this);
        thus = thuDao.getALLThu();
        for (int i = 0; i < thus.size(); i++) {
            thu = thus.get(i);
            a = Integer.parseInt(thu.date);
            integers.add(a);
        }
        for (Integer element : integers) {
            tong += element;

        }
    }
}
