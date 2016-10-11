package pe.edu.upc.yanapan.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.yanapan.R;
import pe.edu.upc.yanapan.model.WorkingDate;


public class CheckActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    /*GPS Permission*/
   // private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    //private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;
    /*Position*/
    //private static final int MINIMUM_TIME = 1000; //10s
    //private static final int MINIMUM_DISTANCE = 50; //50m
    /*GPS*/
   // private String mProviderName;
   // private LocationManager mLocationManager;
    //private LocationListener mLocationListener;

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;

    private GoogleApiClient apiClient;

    private TextView lblLatitud;
    private TextView lblLongitud;
    private Button btnSave;
    Spinner list;
    String[] dat = {"I", "O"};


    final String Check_Url = "http://acmmh.siteli.com.pe:8080/Yanapan/rest/v1/workingdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        lblLatitud = (TextView) findViewById(R.id.lblLatitud);
        lblLongitud = (TextView) findViewById(R.id.lblLongitud);
        btnSave = (Button) findViewById(R.id.buttonSave);
        list = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dat);
        list.setAdapter(adapter);

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();


        WorkingDate check = new WorkingDate();
        check.setIdUser(0);
        check.setLongitude("-2.121333");
        check.setLatitude("-34.54665");
        check.setType("I");



        final JSONObject jsonObject = new JSONObject();
        try {
            /*
            jsonObject.put("idVisit", 0);
            jsonObject.put("longitude", "-2.121333");
            jsonObject.put("latitude", "-34.54665");
            jsonObject.put("user", user);
            jsonObject.put("lstDetailVisitBeneficiary", listaBeneficiario);
            */
            jsonObject.put("", check);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Consume el WebService para grabar
                AndroidNetworking.post(Check_Url)
                        .addJSONObjectBody(jsonObject) // posting json
                        //.addBodyParameter("visita", String.valueOf(visita))
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener(){

                            @Override
                            public void onResponse(JSONObject response) {
                                // Get Respuesta servicio Login
                                try {
                                    int codigo = response.getInt("idUser");
                                    String latitud = response.getString("longitude");
                                    String longitud = response.getString("latitude");
                                    String type = response.getString("type");

                                    Log.i("onResponse", "Resultado : " + String.valueOf(codigo) + " - " + latitud + " - " + longitud+ " - " + type);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("getLogin: Error","Error:"  + anError.toString());
                            }
                        });
            }
        });


    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.

        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        } else {

            Location lastLocation =
                    LocationServices.FusedLocationApi.getLastLocation(apiClient);

            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services

        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }

    private void updateUI(Location loc) {
        if (loc != null) {
            lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
        } else {
            lblLatitud.setText("Latitud: (desconocida)");
            lblLongitud.setText("Longitud: (desconocida)");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);

                updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }


}