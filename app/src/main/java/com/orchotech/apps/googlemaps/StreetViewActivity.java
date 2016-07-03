package com.orchotech.apps.googlemaps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/**
 * Created by Priyam on 03-07-2016.
 */
public class StreetViewActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    StreetViewPanoramaFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streetview);
        mFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.fragment_streetview);
        mFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
      //  streetViewPanorama.setPosition(new LatLng(36.05966, -112.1430));
        streetViewPanorama.setPosition(new LatLng(27.1750, 78.0422));//taj mahal
        StreetViewPanoramaCamera streetViewPanoramaCamera = StreetViewPanoramaCamera.builder()
                .bearing(170).build();
        streetViewPanorama.animateTo(streetViewPanoramaCamera,5000);

    }
}
