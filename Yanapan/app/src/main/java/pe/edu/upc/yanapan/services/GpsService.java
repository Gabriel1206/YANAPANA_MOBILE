package pe.edu.upc.yanapan.services;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import android.content.pm.PackageManager;
import android.Manifest;
import android.support.v4.app.ActivityCompat;

public class GpsService extends Service implements LocationListener {

    private final Context ctx;

    double latitud;
    double longitud;
    Location location;
    boolean gpsActivo;
    TextView texto;
    LocationManager locationManager;

    public GpsService() {
        super();
        this.ctx = this.getApplicationContext();
    }

    public GpsService(Context ctx) {
        super();
        this.ctx = ctx;
        getLocation();
    }

    public void setView(View v) {
        texto = (TextView) v;
        texto.setText("Coordenadas: " + latitud + ", " + longitud);
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) this.ctx.getSystemService(LOCATION_SERVICE);
            gpsActivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {

        }
        if (gpsActivo) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000 * 60, 10, this);
            location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            latitud = location.getLongitude();
            longitud = location.getLongitude();
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

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
