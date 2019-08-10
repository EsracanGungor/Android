package com.esragungor.mymaps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

        @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

         locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
         locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
/*
                LatLng userlocation=new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userlocation).title("your location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocation,15));
*/

                Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addressList=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                    if (addressList !=null&&addressList.size()>0){
                        System.out.println("Address:"+addressList.get(0).toString());
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
            mMap.setOnMapLongClickListener(this);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }else{
           //0 0 yazarsan time ve distance her saniye update yapar uygulamanda böyle yapma
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
           Location lastLocation= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng userLastLocation=new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().title("user last location").position(userLastLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,15));
        }
/*
        // Add a marker in Sydney and move the camera
        LatLng sultanahmet = new LatLng(41.0054096, 28.9746251);
        mMap.addMarker(new MarkerOptions().position(sultanahmet).title("Marker in Sultanahmet"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sultanahmet,15));*/

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if (grantResults.length>0){
           if (requestCode==1){
               if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
               }
           }
       }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        Geocoder geocoder=new Geocoder(getApplicationContext(),Locale.getDefault());
        String address="";
        try {
            List<Address> addressList=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if (addressList !=null&&addressList.size()>0){
                if (addressList.get(0).getThoroughfare()!=null){
                    address=addressList.get(0).getThoroughfare();

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (address.matches("")){
            address="No address";
        }
            mMap.addMarker(new MarkerOptions().position(latLng).title(address));
    }
}
