package pe.edu.upc.yanapan.activities;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Johan on 10/10/2016.
 */

public class Ubicacion implements LocationListener {
    private Context ctx;
    LocationManager locationManager;
    String proveedor;
    private boolean networkOn;

    public Ubicacion(Context ctx, LocationManager locationManager, String proveedor) {
        this.ctx = ctx;
        this.locationManager = locationManager;
        this.proveedor = proveedor;
        networkOn = locationManager.isProviderEnabled(proveedor);
        locationManager.requestLocationUpdates(proveedor,1000,1,this);
        getLocation();
    }

    private void getLocation(){
        if (networkOn){
            Location lc = locationManager.getLastKnownLocation(proveedor);
            if (lc != null){
                StringBuilder builder = new StringBuilder();
                builder.append("Latitud:").append(lc.getLatitude())
                        .append("Longitud:").append(lc.getLongitude());
                Toast.makeText(ctx,builder.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
