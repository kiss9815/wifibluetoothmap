package com.example.arduino4;

import android.app.FragmentManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class FragmentMapActivity extends AppCompatActivity {
    GoogleMap map;
    LocationManager manager;
    MyLocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_map);

        SupportMapFragment fragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
         map = fragment.getMap();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(manager!= null){
            manager.removeUpdates(listener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestMyLocation();
    }

    public void requestMyLocation() {

           long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, listener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, listener);

            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                Double latitude = lastLocation.getLatitude();
                Double longitude = lastLocation.getLongitude();
                Toast.makeText(getApplicationContext(), "위도 :" + latitude + "경도 :" + longitude, Toast.LENGTH_SHORT).show();
            }
        }
        private void showCurrentMap(Double latitude, Double longtitude){
            LatLng curPoint = new LatLng(latitude, longtitude);

            map.setMyLocationEnabled(true);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setAllGesturesEnabled(true);
            map.getUiSettings().setRotateGesturesEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.getUiSettings().setZoomControlsEnabled(true);
            map.getUiSettings().setMapToolbarEnabled(true);


        }


    public class MyLocationListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            Toast.makeText(getApplicationContext(), "위도 :" +  latitude + "경도 :" +longitude, Toast.LENGTH_SHORT).show();

            showCurrentMap(latitude, longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}
