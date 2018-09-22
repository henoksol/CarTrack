package com.henok_solomon.cartrack.wunder.data.local.datasource;


import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponse;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;

import java.util.List;

 

public interface ICarLocationLocalDataSource {



     List<CarLocation> getAll();

    List<CarLocation> insert(List<CarLocationResponse> carLocation);


}
