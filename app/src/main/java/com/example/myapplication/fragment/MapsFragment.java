package com.example.myapplication.fragment;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener, LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 859;
    private GoogleMap googleMap;

    Double lan, lon;
    LocationManager locationManager;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.maps_layout, container, false);
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lon = location.getLongitude();
            lan = location.getLatitude();
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lon = location.getLongitude();
            lan = location.getLatitude();
        }
        final LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                lon = location.getLongitude();
                lan = location.getLatitude();
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10,1,locationListener);
        ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.maps)).getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lan, lon), 13.0f));
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        lon = location.getLongitude();
        lan = location.getLatitude();

    }


    @Override
    public void onCameraMoveStarted(int i) {

    }
}