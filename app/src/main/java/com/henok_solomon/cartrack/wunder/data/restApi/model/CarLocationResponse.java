package com.henok_solomon.cartrack.wunder.data.restApi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CarLocationResponse {
 
    @SerializedName("address")
    @Expose
    public String address;
 @SerializedName("coordinates")
    @Expose
    public String[] coordinates;
 @SerializedName("engineType")
    @Expose
    public String engineType;

     @SerializedName("exterior")
    @Expose
    public String exterior;
     @SerializedName("fuel")
    @Expose
    public String fuel;
 @SerializedName("interior")
    @Expose
    public String interior;
     
         @SerializedName("name")
    @Expose
    public String name;

     @SerializedName("vin")
    @Expose
    public String vin;
    

}

