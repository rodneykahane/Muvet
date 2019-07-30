package com.aaclogic.muvet;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.aaclogic.muvet.directionhelpers.FetchURL;
import com.aaclogic.muvet.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

//public class MainMap extends AppCompatActivity implements OnMapReadyCallback {
public class MainMap extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private MarkerOptions userLocation, destination;
    Button directionBtn;
    private Polyline currentPolyline;

    // *** start udemy code member variables ***
    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    LocationManager mLocationManager;
    LocationListener mLocationListener;
    long MIN_TIME = 5000;
    float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 123;
    // *** end udemy code member variables ***

    // *** start udemy method ***
    // *** THIS METHOD GETS EXECUTED AFTER onCreate() AND JUST BEFORE USER CAN INTERACT WITH ACTIVITY
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Muvet.onResume", "onResume() called");
        Log.d("Muvet.onResume", "getting current location");
        getCurrentLocation();
    }
    // *** end udemy method ***

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* which layout are we using?*/
        setContentView(R.layout.main_map);


        directionBtn = findViewById(R.id.btnDirections);
        directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchURL(MainMap.this).execute(getUrl(userLocation.getPosition(), destination.getPosition(), "driving"), "driving");
            }
        });


        userLocation = new MarkerOptions().position(new LatLng(27.658143, 85.3199503)).title("Current Location");
        destination = new MarkerOptions().position(new LatLng(27.667491, 85.3208583)).title("Destination");
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mainMap);
        mapFragment.getMapAsync(this);
    }//end onCreate()

    // *** START UDEMY METHOD ***
    // *** METHOD FOR GETTING CURRENT LOCATION ***
    private void getCurrentLocation() {

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                float zoom = (float) 14.0;
                BitmapDescriptor muvetBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.blah3);

                Log.d("Muvet.onLocationChanged", "onLocationChanged() callback received");
                String longitude = String.valueOf(location.getLongitude());
                String latitude = String.valueOf(location.getLatitude());

                Log.d("Muvet.onLocationChanged", "longitude is: " + longitude + " and latitude is: " + latitude);

                //the commented out MarkerOptions below is using view_custom_marker.xml and the getMarkerBitmapFromView() method & uses a .bmp file for image
                //MarkerOptions testMarker = new MarkerOptions().position(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude))).title("This is the title").snippet("This is a snippet").icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.blah2)));
                MarkerOptions testMarker= new MarkerOptions().position(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude))).icon(muvetBitmapDescriptor).title("This is the title").snippet("This is a snippet").rotation(46);

                mMap.addMarker(testMarker);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)), zoom));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Muvet.onProviderDisabl", "onProviderDisabled() callback received");
            }
        };

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

            return;
        }
        mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Muvet.onRequestPermissi", "onRequestPermissionResult(): Permission Granted!");
                getCurrentLocation();
            } else {
                Log.d("Muvet.onRequestPermissi", "onRequestPermissionResult(): Permission Denied!");
            }
        }
    }
// *** END METHODS FOR GETTING CURRENT LOCATION ***
// *** END UDEMY ***



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("MainMap.onMapReady()", "Added Markers");

        //adding  call to method to get user's current location
       getCurrentLocation();

        //mMap.addMarker(userLocation);
        //mMap.addMarker(destination);
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
/*
    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
*/
}


