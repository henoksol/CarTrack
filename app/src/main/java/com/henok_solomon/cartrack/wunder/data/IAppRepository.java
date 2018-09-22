package com.henok_solomon.cartrack.wunder.data;


import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponse;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import rx.Observable;

import java.util.List;

public interface IAppRepository  {


     List<CarLocation> getAllLocalCarLocations();
    List<CarLocation>  saveCarLocationList(List<CarLocationResponse> CarLocationList);
    Observable<List<CarLocation>> getAllRemoteCarLocation();

    }

