package com.henok_solomon.cartrack.wunder.mvp.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.henok_solomon.cartrack.R;
import com.henok_solomon.cartrack.wunder.data.AppRepository;
import com.henok_solomon.cartrack.wunder.data.IAppRepository;
import com.henok_solomon.cartrack.wunder.mvp.Contract.CarLocationMapContract;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import com.henok_solomon.cartrack.wunder.mvp.presenter.CarLocationMapPresenter;
import com.henok_solomon.cartrack.wunder.mvp.view.base.BaseMapActivity;
import com.henok_solomon.cartrack.wunder.utils.LocationUtils;

import java.util.ArrayList;
import java.util.List;


public class CarLocationMapActivity extends BaseMapActivity implements CarLocationMapContract.ICarLocationMapView, OnMapReadyCallback, GoogleMap.OnInfoWindowCloseListener  {


    CarLocationMapPresenter<CarLocationMapContract.ICarLocationMapView> mPresenter;
    List<MarkerOptions> mMarkerOptions;
    private GoogleMap mMap;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1001;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
  try {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_car_location_map);

       initDependency();
  }
  catch(Exception e)
  {
      String ee= e.getMessage();
  }
    }


    public  void initDependency()
    {

        IAppRepository appRepository = new AppRepository();
        mPresenter = new CarLocationMapPresenter<>(appRepository);
        mPresenter.onAttach(this);
        mPresenter.subscribe();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        if(googleMap == null)
        {
            mPresenter.onMapNotAvailable();
        }
            else
            {
        mMap = googleMap;

                mPresenter.getCarLocations(this.isNetworkConnected());

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

               if(marker.isInfoWindowShown())
               {
                   marker.hideInfoWindow();

               }
               else
               {

                      mPresenter.setMapMarker(marker);

               }
                    return false;
            }
        });

        if( ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
        }

        mMap.setMyLocationEnabled(true);
            }
    }



    @Override
    public void showMapNotAvailableAlert()
    {
        new AlertDialog.Builder(this)
            .setTitle(R.string.common_google_play_services_enable_title)
            .setMessage(R.string.common_google_play_services_enable_title)
            .setPositiveButton(android.R.string.ok, null)
            .show();
    }

    @Override
    public void showLocationAccessDeniedAlert()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.common_location_access_denied_title)
                .setMessage(R.string.common_location_access_denied_title)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,   String[] permissions,   int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0 )
        {
            if( mPresenter != null )
            {
                if( grantResults[0] != PackageManager.PERMISSION_GRANTED )
                {
                       mPresenter.onLocationPermissionDenied();
                }

            }

        }
    }

    @Override
    public void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void terminateMap() {
            mMarkerOptions = null;
    }

    @Override
       public  void populateMapMarker(List<CarLocation> carLocations)
       {
            // Populate Map Marker

               mMarkerOptions = new ArrayList<>();


                       for (CarLocation data : carLocations) {


                           LatLng GeoPoint = LocationUtils.ConvertRawToLatLong(data.getCoordinates());

                           MarkerOptions marker = new MarkerOptions().position(GeoPoint).title(data.getName());

                           mMap.addMarker(marker);
                           mMarkerOptions.add(marker);
                       }



          
       }
    @Override
    public void refreshMapMarkers(List<MarkerOptions> markerOptions) {

        mMap.clear();

        for (MarkerOptions markerOption : markerOptions) {


            mMap.addMarker(markerOption);

        }
      
    }

    @Override
    public void refreshOtherMapMarkers(List<MarkerOptions> markerOptions,Marker marker) {

        mMap.clear();

        for (MarkerOptions markerOption : markerOptions) {

            if(marker.getPosition() != markerOption.getPosition())
            mMap.addMarker(markerOption);

        }

    }


    @Override
    public void onInfoWindowClose(Marker marker) {


                marker.setVisible(false);
            if( mMarkerOptions.size()>0)
                   {
                       mPresenter.setMapMarkers(mMarkerOptions,marker);
                   }
    }
    @Override
    public void onResume() {
         super.onResume();
    }

    @Override
    public void onDestroy() {
      //  mPresenter.unSubscribe();
        super.onDestroy();
    }


}
