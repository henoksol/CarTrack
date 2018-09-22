package com.henok_solomon.cartrack.wunder.data.restApi.service;

import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponseList;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by HENOK_SOLOMON on 16/9/2018.
 */
public interface CarLocationService {
    @GET("locations.json")
    Observable<CarLocationResponseList> getCarLocationList();
}