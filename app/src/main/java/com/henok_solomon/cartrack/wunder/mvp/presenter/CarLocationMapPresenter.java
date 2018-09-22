package com.henok_solomon.cartrack.wunder.mvp.presenter;


import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.henok_solomon.cartrack.wunder.data.IAppRepository;
import com.henok_solomon.cartrack.wunder.mvp.Contract.CarLocationMapContract;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;


public class CarLocationMapPresenter<V extends CarLocationMapContract.ICarLocationMapView> extends BasePresenter<V> implements
        CarLocationMapContract.ICarLocationMapPresenter<V> {


    public CarLocationMapPresenter(IAppRepository appRepository) {
        super(appRepository);

    }


    private void createRemoteObservable() {

        Observable<List<CarLocation>> RemoteCarLocationObservable =  getAppRepository().getAllRemoteCarLocation();

        RemoteCarLocationObservable.subscribe(new rx.Observer<List<CarLocation>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<CarLocation> carLocations) {
                getView().populateMapMarker(carLocations);
            }
        });

    }

    private void createLocalObservable() {
        Observable<List<CarLocation>> LocalCarLocationObservable = Observable.just(getAppRepository().getAllLocalCarLocations());

        LocalCarLocationObservable.subscribe(new rx.Observer<List<CarLocation>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<CarLocation> carLocations) {
                getView().populateMapMarker(carLocations);
            }
        });

    }
    @Override
    public   void getCarLocations(Boolean isOnline){

        if(isOnline)
        {
            createRemoteObservable();
        }
        else
        {
            createLocalObservable();
        }




    }




    @Override
    public void subscribe() {
            // Init Google Map

      if(isViewAttached())
      getView().initMap();

    }

    //@Override
    public void unSubscribe() {
         // Garbage Collection
        getView().terminateMap();
    }

    @Override
    public void onLocationPermissionDenied() {
        getView().showLocationAccessDeniedAlert();
    }

    @Override
    public void onMapNotAvailable() {
        getView().showMapNotAvailableAlert();
    }

    @Override
    public void setMapMarkers(List<MarkerOptions> markerOptions, Marker marker) {
            getView().refreshOtherMapMarkers(markerOptions,marker);
    }

    @Override
    public void setMapMarker(Marker marker) {
        List<MarkerOptions> markerOptions = new ArrayList<>();
        markerOptions.add(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle()));

        getView().refreshMapMarkers(markerOptions);
    }
}
