package com.orchotech.apps.googlemaps;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    boolean isTerrain;
    private GoogleMap googleMap;
    private boolean isMapReady;
    MarkerOptions mMarkerOptions;
    private CameraPosition target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);
        isTerrain = false;
        target = CameraPosition.builder()
                .target(new LatLng(26.9826, 94.6425))
                .bearing(90)
                .zoom(18)
                .tilt(50)
                .build();

        mMarkerOptions = new MarkerOptions().position(new LatLng(26.7465, 94.2026))
                .title("Jorhat")
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (isMapReady) {
                    if (isTerrain) {
                        isTerrain = true;
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    } else {
                        isTerrain = false;
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        isMapReady = true;
        this.googleMap = googleMap;
        this.googleMap.addMarker(mMarkerOptions);
        this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }
}
