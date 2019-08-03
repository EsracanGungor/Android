package com.esragungor.gyk401.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.esragungor.gyk401.fragments.BilgiEkraniFragment;
import com.esragungor.gyk401.fragments.DiyetListesiFragment;
import com.esragungor.gyk401.fragments.EndeksHesaplaFragment;
import com.esragungor.gyk401.R;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationItemView=findViewById(R.id.bottomnav_main);
        bottomNavigationItemView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment=null;

        switch (menuItem.getItemId()){
            case R.id.navitem_endekshesapla:
            selectedFragment=new EndeksHesaplaFragment();
            break;
            case R.id.navitem_bilgiekrani:
            selectedFragment=new BilgiEkraniFragment();
            break;
            case R.id.navitem_dietlistesi:
            selectedFragment=new DiyetListesiFragment();
            break;


        }
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container,selectedFragment);
        transaction.commit();

        return true;
    }
}
