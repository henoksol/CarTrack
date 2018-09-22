 package com.henok_solomon.cartrack.wunder.data.restApi;

 import com.henok_solomon.cartrack.wunder.config.AppConstants;
import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponseList;
import com.henok_solomon.cartrack.wunder.data.restApi.service.CarLocationService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

 
public class ApiHelper implements IApiHelper {

    Retrofit mRetrofit;

    public ApiHelper() {

        mRetrofit = getRetrofitInstance();
    }
  
   private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

   @Override
    public Observable<CarLocationResponseList> getAll() {
        return mRetrofit.create(CarLocationService.class).getCarLocationList();
    }

}

