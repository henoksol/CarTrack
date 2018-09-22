package com.henok_solomon.cartrack.wunder.data;

import com.henok_solomon.cartrack.wunder.data.local.datasource.CarLocationLocalDataSource;
import com.henok_solomon.cartrack.wunder.data.local.datasource.ICarLocationLocalDataSource;
import com.henok_solomon.cartrack.wunder.data.restApi.ApiHelper;
import com.henok_solomon.cartrack.wunder.data.restApi.IApiHelper;
import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponse;
import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponseList;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;



public class AppRepository implements IAppRepository {
 

    private final ICarLocationLocalDataSource mICarLocationLocalDataSource;
    private final IApiHelper mApiHelper;


    public AppRepository()
     {
         mICarLocationLocalDataSource = new CarLocationLocalDataSource();
         mApiHelper = new ApiHelper();
    }

 

    @Override
    public List<CarLocation> getAllLocalCarLocations() {

              return  mICarLocationLocalDataSource.getAll();

    } 
  
 
@Override
    public   List<CarLocation> saveCarLocationList(List<CarLocationResponse> CarLocationList) {
     return   mICarLocationLocalDataSource.insert(CarLocationList);

}

 

    @Override
    public Observable<List<CarLocation>> getAllRemoteCarLocation() {

        return mApiHelper.getAll().concatMap(new Func1<CarLocationResponseList, Observable<List<CarLocation>>>() {
            @Override
            public Observable<List<CarLocation>> call(CarLocationResponseList carLocationResponseList) {
                return Observable.just(saveCarLocationList(carLocationResponseList.getCarLocationResponses()));
            }
        }).asObservable();



                    }
    

    }
 

   

