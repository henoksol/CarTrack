package com.henok_solomon.cartrack.wunder.data.local.datasource;


import android.os.Environment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.henok_solomon.cartrack.wunder.config.AppConstants;
import com.henok_solomon.cartrack.wunder.data.restApi.model.CarLocationResponse;
import com.henok_solomon.cartrack.wunder.mvp.model.CarLocation;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CarLocationLocalDataSource  implements ICarLocationLocalDataSource {

    private List<CarLocation> AllCarLocations;

    public CarLocationLocalDataSource() {}

    @Override
    public List<CarLocation>  getAll() {
        return getCarLocation();
    }

    @Override
    public  List<CarLocation> insert (List<CarLocationResponse> carLocation) {
        return StoreCarLocation(carLocation);
    }


public List<CarLocation> getCarLocation()
{
    List<CarLocation> carLocations = new ArrayList<>();
    try {
        Gson gson = new Gson();
        String dirPath= Environment.getExternalStorageDirectory()+AppConstants.FILE_DIR;
        File dir =new File(dirPath);
        if(!dir.exists())
        {
            dir.mkdirs();
        }

        File jsonFile =new File(dirPath,AppConstants.FILE_NAME);
        if(jsonFile.exists()) {

            FileInputStream fileInputStream = new FileInputStream(jsonFile);
            BufferedInputStream fileBufferInputStream = new BufferedInputStream(fileInputStream);
            StringBuffer jsonBuffer = new StringBuffer();
            while (fileBufferInputStream.available() != 0) {
                char c = (char) fileBufferInputStream.read();
                jsonBuffer.append(c);
            }
            fileBufferInputStream.close();
            fileInputStream.close();

            Type CarLocationResponseType = new TypeToken<ArrayList<CarLocation>>(){}.getType();
              carLocations = gson.fromJson(jsonBuffer.toString(), CarLocationResponseType);


        }

    }
    catch (Exception e)
    {
        String ee= e.getMessage();

    }
    return  carLocations;
}
    public   List<CarLocation>  StoreCarLocation( List<CarLocationResponse> carLocationResponse)
    {

        try{
            Gson gson = new Gson();
            String dirPath= Environment.getExternalStorageDirectory()+AppConstants.FILE_DIR;
            File dir =new File(dirPath);
            if(!dir.exists())
            {
                dir.mkdirs();
            }

            File jsonFile =new File(dirPath,AppConstants.FILE_NAME);
            if(jsonFile.exists())
            {
                jsonFile.delete();

            }
           // jsonFile.createNewFile();
                 String jsonResponse = gson.toJson(carLocationResponse);
             Writer fileOutStream = new BufferedWriter(new FileWriter(jsonFile));
            fileOutStream.write(jsonResponse);
            fileOutStream.close();


        }
        catch(Exception e)
        {
            return null;
        }
        return getCarLocation();
    }
}
