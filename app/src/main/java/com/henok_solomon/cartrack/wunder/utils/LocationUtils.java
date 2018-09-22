 package com.henok_solomon.cartrack.wunder.utils;

 import android.content.Context;
import android.location.LocationManager;
import com.google.android.gms.maps.model.LatLng;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;
import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponse;

import java.util.ArrayList;
import java.util.List;

 public final class LocationUtils {


     public LocationUtils() {
        
    }
        public  static List<CarLocation> MapResponceEntity (  List<CarLocationResponse> carLocationResponses)
     {
         List<CarLocation>  carLocationList = new ArrayList<CarLocation>();
         try
         {

         for (CarLocationResponse carLocationResponse: carLocationResponses ) {

             CarLocation CcrLocation = new CarLocation(carLocationResponse.address,carLocationResponse.coordinates, carLocationResponse.engineType, carLocationResponse.exterior, carLocationResponse.fuel , carLocationResponse.interior, carLocationResponse.name, carLocationResponse.vin);

             carLocationList.add(CcrLocation);

         }}
         catch(Exception e)
             {
             String ee = e.getMessage();
                 return null;
             }

         return carLocationList;

     }

 
 public static LatLng ConvertRawToLatLong(String[] coordinate)
 {
     String[] Coordinates =  coordinate;

     double  Lat =   Double.parseDouble( Coordinates[0].toString());
     double Long =  Double.parseDouble( Coordinates[1].toString());

     return new LatLng(  Lat, Long);
 }
    public static boolean isLocationServiceEnabled(Context context) {
        boolean isGpsEnabled = false, isNetworkProviderEnabled = false;

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
           } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            isNetworkProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isGpsEnabled || isNetworkProviderEnabled;

    }
 
}
