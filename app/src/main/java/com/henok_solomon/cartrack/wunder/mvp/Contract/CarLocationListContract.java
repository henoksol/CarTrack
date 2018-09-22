package com.henok_solomon.cartrack.wunder.mvp.Contract;


import android.support.annotation.NonNull;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;

import java.util.List;

public class CarLocationListContract {


    public interface ICarLocationListPresenter<V extends ICarLocationListView> extends BaseContract.IBasePresenter<V> {
        void getCarLocations(Boolean isOnline);

        void subscribe(Boolean isOnline);

        void unSubscribe();
    }


    public interface ICarLocationListView extends BaseContract.IBaseView {

        void openCarLocationMapActivity();
        void refreshRecyclerView(@NonNull List<CarLocation> carLocations);

    }
}