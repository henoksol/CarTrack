package com.henok_solomon.cartrack.wunder.mvp.presenter;

import com.henok_solomon.cartrack.wunder.data.IAppRepository;
import com.henok_solomon.cartrack.wunder.mvp.Contract.BaseContract;

 
public class BasePresenter<V extends BaseContract.IBaseView> implements BaseContract.IBasePresenter<V> {

     private final IAppRepository mAppRepository;
      private V mView;


    public BasePresenter(IAppRepository appRepository) {
        this.mAppRepository = appRepository ;
           }

    @Override
    public void onAttach(V View) {
        mView = View;
    }

    @Override
    public void onDetach() {
         mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    public IAppRepository getAppRepository() {
        return mAppRepository;
    }

   

    

    

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call Presenter.onAttach(View) before" +
                    " requesting data to the Presenter");
        }
    }
   
}
