package pe.edu.upc.yanapan.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.ListClaimedBleDevicesRequest;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

import pe.edu.upc.yanapan.R;


//public class CheckActivity extends FragmentActivity {
public class CheckActivity extends AppCompatActivity {

    TextView mensaje1; //Longitud
    TextView mensaje2; //Latitud

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        mensaje1 = (TextView) findViewById(R.id.txtLongitud);
        mensaje2 = (TextView) findViewById(R.id.txtLatitud);

        /* Uso de la clase Location para obtener el GPS */
        Log.d("Usando Location","OK");
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion local = new Localizacion();
        local.setCheckActivity(this);
        Log.d("Paso Location","OK");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) local);
        mensaje1.setText("Localizacion Agregada");
        mensaje2.setText("");
    }

    //Empieza la clase localizacion
    public class Localizacion implements LocationListener {
        CheckActivity checkActivity;

        /*public Localizacion(CheckActivity checkActivity) {
            this.checkActivity = checkActivity;
        }*/

        public Localizacion() {
            super();
            this.checkActivity = checkActivity;
        }

        public CheckActivity getCheckActivity() {
            return checkActivity;
        }

        public void setCheckActivity(CheckActivity checkActivity) {
            this.checkActivity = checkActivity;
        }

        @Override
        public void onLocationChanged(Location location) {
            location.getLatitude();
            location.getLongitude();
            String Text = "Mi ubicacion actual es: " + "\n Lat = " + location.getLatitude() + "\n Lon = " + location.getLongitude();
            mensaje1.setText(Text);
            mensaje2.setText("Pruebas");
            this.checkActivity.setLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            mensaje1.setText("GPS Activado");
        }

        @Override
        public void onProviderDisabled(String provider) {
            mensaje1.setText("GPS Desactivado");
        }
    }


    public void setLocation(Location location) {
        //Obtener direccion
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<android.location.Address> list = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    android.location.Address DirCalle = list.get(0);
                    mensaje2.setText("Mi dirección es : \n"
                            + DirCalle.getAddressLine(0));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
