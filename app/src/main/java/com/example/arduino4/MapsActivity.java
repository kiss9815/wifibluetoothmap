package com.example.arduino4;

import android.content.Context;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude ;
    double longitude ;

    double droneLatitude ;
    double droneLongitude ;

    private static final String TAG_LOCATION = "location";
    private static final String TAG_DRONE = "drone";



    TextView text_distance;
    float actual_distance; //실제 거리 값을 담을 변수
    TextView text_gps;

    Location lastLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        text_distance = (TextView)findViewById(R.id.text_distance);
        text_gps = (TextView)findViewById(R.id.textView_gps);




        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        long minTime = 10000;//10초
        float minDistance = 0;

        MyLocationListener listener = new MyLocationListener();

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                minTime, minDistance, listener);

         lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {



            latitude = lastLocation.getLatitude();
            longitude = lastLocation.getLongitude();
            Toast.makeText(getApplicationContext(), "경도" + latitude + " 위도" + longitude, Toast.LENGTH_SHORT).show();
           text_gps.setText("가장 최근의 내 위치:\n" + latitude + ", " + longitude);


            Button btn_distance = (Button)findViewById(R.id.button_distance);
            btn_distance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    distanceCalculate();
                    Log.d("aaa", "" + latitude +":"+ longitude);
                }
            });
        }



    }
////////////////////////////oncreate

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        mMap.setMyLocationEnabled(true);


        // Add a marker in Sydney and move the camera
        //36.354219, 127.341823 // 유성온천
        //36.362657, 127.344850
        droneLatitude = 36.362657 ;
        droneLongitude = 127.344850;
        LatLng dronePosition = new LatLng(droneLatitude, droneLongitude);
        mMap.addMarker(new MarkerOptions().position(dronePosition).title("드론위치"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(dronePosition));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dronePosition, 15));

         mMap.addCircle(new CircleOptions()
                .center(dronePosition)
                .radius(10000)
                .strokeColor(Color.RED)
                .fillColor(Color.TRANSPARENT));
    }

    class MyLocationListener implements LocationListener {

        @Override

        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            LatLng curPoint = new LatLng(latitude, longitude);
            Toast.makeText(getApplicationContext(), "경도" + latitude + " 위도" + longitude, Toast.LENGTH_SHORT).show();
//            mMap.addPolyline(new PolylineOptions().add(curPoint).color(Color.BLUE).width(50));
//        textView.setText("내 위치:" + latitude + ", " + longitude);
//        textView.invalidate();

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

    private void distanceCalculate(){
        double latitude_cal = lastLocation.getLatitude();
        double longitude_cal = lastLocation.getLongitude();

        Log.i(TAG_LOCATION, latitude_cal + " : " + longitude_cal );

        Log.i(TAG_DRONE, droneLatitude + " ," + droneLongitude);

        Distance distance =  new Distance();
        double dist = distance.calDistance(latitude_cal, longitude_cal, droneLatitude, droneLongitude);

        Log.i("dist", "" + dist);
        double a = dist/100000;
        DecimalFormat fmt = new DecimalFormat("0.##");
        String decimal =  fmt.format(a);

        text_distance.setText(decimal + "m" + " sdf :" + dist );

    }



}
