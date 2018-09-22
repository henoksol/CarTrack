package com.henok_solomon.cartrack.wunder.data.restApi;


import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponseList;
import rx.Observable;

public interface IApiHelper {
     Observable<CarLocationResponseList> getAll();
} 
