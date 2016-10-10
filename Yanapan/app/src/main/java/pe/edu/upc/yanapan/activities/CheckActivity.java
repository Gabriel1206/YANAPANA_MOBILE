package pe.edu.upc.yanapan.activities;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import pe.edu.upc.yanapan.R;


public class CheckActivity extends FragmentActivity {

    /*GPS Permission*/
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;
    /*Position*/
    private static final int MINIMUM_TIME = 1000; //10s
    private static final int MINIMUM_DISTANCE = 50; //50m
    /*GPS*/
    private String mProviderName;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        //Ubicacion ub = new Ubicacion(this);
    }
}
