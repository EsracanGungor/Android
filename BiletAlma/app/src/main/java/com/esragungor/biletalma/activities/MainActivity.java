package com.esragungor.biletalma.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.esragungor.biletalma.R;
import com.esragungor.biletalma.fragment.BiletEkleFragment;
import com.esragungor.biletalma.fragment.BiletSatinAlmaFragment;
import com.esragungor.biletalma.fragment.BiletlerimFragment;
import com.esragungor.biletalma.fragment.ProfileFragment;
import com.esragungor.biletalma.model.Bilet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
    BottomNavigationView.OnNavigationItemSelectedListener {

    public ArrayList<Bilet> satinAlinanBiletlerList=new ArrayList<>();
    public ArrayList<Bilet> filmBiletleri = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstatnceState){
        super.onCreate(savedInstatnceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawe_main);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment=null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.nav_biletekle:
                        selectedFragment=new BiletEkleFragment();
                        break;

                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_container, selectedFragment);
                transaction.commit();
                drawer.closeDrawer(Gravity.START);
                return true;
            }
        });

        bottomNavigationView=findViewById(R.id.bottomnav_main);
        navigationView=findViewById(R.id.nav_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //filmBiletleri.add(new Bilet(R.drawable.film1, "Kayip Balık Nemo", 781623712, 30));
        //filmBiletleri.add(new Bilet(R.drawable.film2, "Karayip Korsanları", 781623569, 30));
        //filmBiletleri.add(new Bilet(R.drawable.film3, "Charlie'nin Çikolata Fabrikası",781623820, 30));

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment=null;

        switch (menuItem.getItemId()){
            case R.id.navitem_biletlerim:
                selectedFragment=new BiletlerimFragment();
                break;
            case R.id.navitem_bilet_satin_alma:
                selectedFragment=new BiletSatinAlmaFragment();
                break;


                    }
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container,selectedFragment);
        transaction.commit();

        return false;
    }

}
