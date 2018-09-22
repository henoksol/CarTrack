 package com.henok_solomon.cartrack.wunder.mvp.presenter;

 import com.henok_solomon.cartrack.wunder.data.IAppRepository;
import com.henok_solomon.cartrack.wunder.mvp.Contract.CarLocationListContract;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import rx.Observable;
import rx.Observer;

import java.util.List;


 public class CarLocationListPresenter<V extends CarLocationListContract.ICarLocationListView> extends BasePresenter<V>
        implements CarLocationListContract.ICarLocationListPresenter<V> {


    public CarLocationListPresenter(IAppRepository appRepository)
    {
        super(appRepository);
    }

     private void createLocalObservable() {
         Observable<List<CarLocation>> LocalCarLocationObservable = Observable.just(getAppRepository().getAllLocalCarLocations());

         LocalCarLocationObservable.subscribe(new Observer<List<CarLocation>>() {

             @Override
             public void onCompleted() {
        getView().hideLoading();
             }

             @Override
             public void onError(Throwable e) {

             }

             @Override
             public void onNext(List<CarLocation> carLocations) {
                 getView().refreshRecyclerView(carLocations);
             }
         });

     }
     private void createRemoteObservable() {
         Observable<List<CarLocation>> RemoteCarLocationObservable = getAppRepository().getAllRemoteCarLocation();

         RemoteCarLocationObservable.subscribe(new Observer<List<CarLocation>>() {

             @Override
             public void onCompleted() {
        getView().hideLoading();
             }

             @Override
             public void onError(Throwable e) {

             }

             @Override
             public void onNext(List<CarLocation> carLocations) {
                 getView().refreshRecyclerView(carLocations);
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
        
    getView().hideLoading();
   
         
    } 
 
  

   

      @Override
    public void subscribe(Boolean isOnline) {
        // Subscribe Rx subscription

        if(isViewAttached())
       getView().showLoading();

       getCarLocations(isOnline);
    }

    @Override
    public void  unSubscribe() {
        // Unsubscribe Rx subscription

    }
}
