package com.henok_solomon.cartrack.wunder.data.restApi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CarLocationResponseList {

    @SerializedName("placemarks")
    @Expose
    private  List<CarLocationResponse> CarLocationResponses = null;

    public List<CarLocationResponse> getCarLocationResponses() {
        return CarLocationResponses;
    }


    public void setCarLocationResponses(ArrayList<CarLocationResponse> CarLocationResponses) {
        this.CarLocationResponses = CarLocationResponses;
    }

}