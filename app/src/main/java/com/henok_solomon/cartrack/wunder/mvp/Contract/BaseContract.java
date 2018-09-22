package com.henok_solomon.cartrack.wunder.mvp.Contract;


public class BaseContract {

    public interface IBasePresenter<V extends IBaseView> {

        void onAttach(V View);

        void onDetach();

    }

    public interface IBaseView<T> {

        void showLoading();

        void hideLoading();

        boolean isNetworkConnected();



    }


}
