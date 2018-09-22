package com.henok_solomon.cartrack.wunder.mvp.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.henok_solomon.cartrack.R;
import com.henok_solomon.cartrack.wunder.data.AppRepository;
import com.henok_solomon.cartrack.wunder.data.IAppRepository;
import com.henok_solomon.cartrack.wunder.mvp.Contract.CarLocationListContract;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import com.henok_solomon.cartrack.wunder.mvp.presenter.CarLocationListAdapter;
import com.henok_solomon.cartrack.wunder.mvp.presenter.CarLocationListPresenter;
import com.henok_solomon.cartrack.wunder.mvp.view.base.BaseActivity;

import java.util.List;

public class CarLocationListActivity extends BaseActivity implements CarLocationListContract.ICarLocationListView
{

    FloatingActionButton mFloatingActionButton;
    RecyclerView mRecyclerView;



    CarLocationListPresenter<CarLocationListContract.ICarLocationListView> mPresenter;

    CarLocationListAdapter mCarLocationListAdapter;

    private static final int REQUEST_CODE_IO_PERMISSION = 1002;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_location_list);

        try {
            if(Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            setupWidget();
            initDependency();
        }catch(Exception e)
        {

        }

    }


    public void initDependency() {
        IAppRepository appRepository = new AppRepository();
        mPresenter = new CarLocationListPresenter<>(appRepository);
        mPresenter.onAttach(this);
        mPresenter.subscribe(this.isNetworkConnected());

    }
    public void setupWidget()
    {

        mCarLocationListAdapter = new CarLocationListAdapter();

         mRecyclerView = (RecyclerView) findViewById(R.id.carlocation_recycler_view);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        mRecyclerView.setHasFixedSize(true);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mFloatingActionButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        openCarLocationMapActivity();
                    }
                }
        );
        if( ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&  ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_CODE_IO_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,   String[] permissions,   int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == REQUEST_CODE_IO_PERMISSION && grantResults.length > 0 )
        {

        }
    }


    @Override
    public void refreshRecyclerView( List<CarLocation> carLocations) {


        mCarLocationListAdapter.setCarLocations(carLocations);
       mRecyclerView.setAdapter( mCarLocationListAdapter);
        mCarLocationListAdapter.notifyDataSetChanged();
    }








    @Override
    public void onDestroy() {
        mPresenter.unSubscribe();
        super.onDestroy();
    }




    @Override
    public void openCarLocationMapActivity() {

        Intent intent = new Intent(CarLocationListActivity.this, CarLocationMapActivity.class);
        startActivity(intent);
    }



}
