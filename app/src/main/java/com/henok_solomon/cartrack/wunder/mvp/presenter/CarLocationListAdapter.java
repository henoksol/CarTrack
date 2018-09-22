package com.henok_solomon.cartrack.wunder.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; 
import com.henok_solomon.cartrack.R;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import java.util.Collections;
import java.util.List;


public class CarLocationListAdapter extends RecyclerView.Adapter<CarLocationListAdapter.CarLocationViewHolder> {

    private List<CarLocation> CarLocations;

    public CarLocationListAdapter() {
        CarLocations = Collections.emptyList();
    }

    public void setCarLocations(@NonNull List<CarLocation> CarLocations) {
        this.CarLocations = CarLocations;
    }

    public void clear() {
        CarLocations.clear();
    }
 

    @Override
    public CarLocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carlocation_list_item, parent, false);
        return new CarLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CarLocationViewHolder holder, int position) {
        holder.CarLocation = CarLocations.get(position);
        holder.carName.setText(holder.CarLocation.name);
        holder.carAddress.setText("Address "+holder.CarLocation.address);
        holder.carEngineType.setText("Engine Type "+holder.CarLocation.engineType);
        holder.carFuel.setText("Fuel "+holder.CarLocation.fuel+" L");

    }

    @Override
    public int getItemCount() {
        return CarLocations.size();
    }

    class CarLocationViewHolder extends RecyclerView.ViewHolder {

         TextView carName;
         TextView carAddress;
        TextView carFuel;
       TextView carEngineType;

        CarLocation CarLocation;

        CarLocationViewHolder(@NonNull View view) {
            super(view);
            carName = (TextView) view.findViewById(R.id.car_name);
            carAddress = (TextView) view.findViewById(R.id.car_address);
            carFuel = (TextView) view.findViewById(R.id.car_fuel);
            carEngineType = (TextView) view.findViewById(R.id.car_engineType);

        }
    }

     
}