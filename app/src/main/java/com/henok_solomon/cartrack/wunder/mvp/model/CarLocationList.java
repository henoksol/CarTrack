package com.henok_solomon.cartrack.wunder.mvp.model;


import java.util.List;

public class CarLocationList {

  public List<CarLocation> placemarks;

   public List<CarLocation> getPlacemarks() {
      return placemarks;
   }

   public void setPlacemarks(List<CarLocation> placemarks) {
      this.placemarks = placemarks;
   }
}