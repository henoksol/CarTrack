package com.henok_solomon.cartrack.wunder.mvp.Contract;


import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;

import java.util.List;



public class CarLocationMapContract {

    public interface ICarLocationMapView extends BaseContract.IBaseView {


        void showMapNotAvailableAlert();
        void showLocationAccessDeniedAlert();
        void initMap();

        void terminateMap();

        void populateMapMarker(List<CarLocation> carLocations);

        void refreshMapMarkers(List<MarkerOptions> markerOptions);
        void refreshOtherMapMarkers(List<MarkerOptions> markerOptions,Marker marker);



    }

    public interface ICarLocationMapPresenter<V extends BaseContract.IBaseView> extends BaseContract.IBasePresenter<V>   {

        void getCarLocations(Boolean isOnline);
        void subscribe();
        void onLocationPermissionDenied();
         void onMapNotAvailable();
        void setMapMarkers(List<MarkerOptions> markerOptions,Marker marker);
        void setMapMarker(Marker marker);
    }

}















