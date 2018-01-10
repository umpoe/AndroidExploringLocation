package za.co.immedia.Classes;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Umpoe on 12/8/2017.
 */

public class MyClassLatLng implements LocationListener  {
    double currentLatitude, currentLongitude;
    String StatusLocation="";



    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
    }

    public Double GetCurrentLatitude(){
            return currentLatitude;
    }
    public Double GetCurrentLongitude(){
        return currentLongitude;
    }
    public String GetStatusLocation(){
        return StatusLocation;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        StatusLocation=s+" Please enable location on your device";
    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
