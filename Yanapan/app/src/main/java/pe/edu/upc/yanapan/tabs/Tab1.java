package pe.edu.upc.yanapan.tabs;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.content.pm.PackageManager;
import android.Manifest;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.edu.upc.yanapan.R;
import pe.edu.upc.yanapan.activities.LoginActivity;
import pe.edu.upc.yanapan.activities.MenuActivity;
import pe.edu.upc.yanapan.model.DetailVisitBeneficiary;
import pe.edu.upc.yanapan.model.TypeBeneficiary;
import pe.edu.upc.yanapan.model.User;
import pe.edu.upc.yanapan.model.Profile;
import pe.edu.upc.yanapan.model.Ubigeo;
import pe.edu.upc.yanapan.model.Beneficiary;
import pe.edu.upc.yanapan.model.Visit;



/**
 * Created by Juan on 07/10/2016.
 */

public class Tab1 extends Fragment implements View.OnClickListener {

    FloatingActionButton ftnsave ;
    EditText campo_ID,campo_nombre, campo_LastName, campo_City, campo_District, campo_Address, campo_Observation;
    Button boton_aceptar;

    private static final String PREFS = "prefs";
    private static final String PREF_ID = "idUser";

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.tab1, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab1, container, false);

        campo_ID = (EditText) view.findViewById(R.id.campo_ID);
        campo_nombre = (EditText) view.findViewById(R.id.campo_nombre);
        campo_LastName = (EditText) view.findViewById(R.id.campo_LastName);
        campo_City = (EditText) view.findViewById(R.id.campo_City);
        campo_District = (EditText) view.findViewById(R.id.campo_District);
        campo_Address = (EditText) view.findViewById(R.id.campo_Address);
        campo_Observation = (EditText) view.findViewById(R.id.campo_Observation);
        boton_aceptar = (Button) view.findViewById(R.id.boton_aceptar);

        final String LOGIN_Url = "http://acmmh.siteli.com.pe:8080/Yanapan/rest/v1/visit";
        //final String LOGIN_Url = "http://192.168.1.4:8088/Yanapan/rest/v1/visit";


        boton_aceptar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){

                Log.i("campo_nombre",campo_nombre.getText().toString());
                Log.i("campo_LastName",campo_LastName.getText().toString());

                final User user = new User();
                Profile profile = new Profile();

                profile.setIdProfile(1);
                profile.setDescProfile("Administrador");

                user.setProfile(profile);
                user.setIdUser(Integer.parseInt(getKeptId()));
               // user.setIdUser(1);
                user.setDocumentUser("46860100");
                user.setNickUser("mcastaneda");
                user.setPassword("admin");
                user.setFirstName("Manuel");
                user.setLastName("Castaneda");

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateInString = "01/10/2016";

                try {

                    Date date = formatter.parse(dateInString);
                    user.setBirthdate(dateInString);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //user.setBirthdate(Date.valueOf("01/10/2016"));

                TypeBeneficiary tipoBeneficiario = new TypeBeneficiary();
                Ubigeo ubigeo = new Ubigeo();

                tipoBeneficiario.setIdTypeBeneficiary(1);
                tipoBeneficiario.setDescTypeBeneficiary("Usuario");
                ubigeo.setIdUbigeo(150101);
                ubigeo.setDescUbigeo("Lima/Lima/Cercado");

                Beneficiary beneficiario = new Beneficiary();
                beneficiario.setTypeBeneficiary(tipoBeneficiario);
                beneficiario.setUbigeo(ubigeo);
                beneficiario.setIdBeneficiary(1);
                beneficiario.setDocumentBeneficiary(campo_ID.getText().toString());
                beneficiario.setFirstName(campo_nombre.getText().toString());
                beneficiario.setLastName(campo_LastName.getText().toString());
                beneficiario.setBirthdate("20/05/1980");
                beneficiario.setAddress(campo_Address.getText().toString());
                beneficiario.setFlagDisabled("0");
                beneficiario.setFlagKnowledge("1");
                beneficiario.setDescKnowledge("Sabe contar fabulas");

                DetailVisitBeneficiary detalle = new DetailVisitBeneficiary();
                detalle.setIdVisit(1);
                detalle.setNote("Pruebas");
                detalle.setUrlPhoto1("");
                detalle.setUrlPhoto2("");
                detalle.setBeneficiary(beneficiario);

                List<DetailVisitBeneficiary> listaBeneficiario = new ArrayList<DetailVisitBeneficiary>();
                listaBeneficiario.add(detalle);

                Visit visita = new Visit();
                visita.setIdVisit(0);
                visita.setLongitude("-2.121333");
                visita.setLatitude("-34.54665");
                visita.setUser(user);
                visita.setLstDetailVisitBeneficiary(listaBeneficiario);

                // final JSONObject jsonObject = new JSONObject();
                final JSONArray jsonArray = new JSONArray();
                Gson gson = new Gson();
                JSONObject jsonObject = new JSONObject();
                try {

            /*jsonArray.put(0,detalle);
            jsonObject.put("idVisit", 0);
            jsonObject.put("longitude", "-2.121333");
            jsonObject.put("latitude", "-34.54665");
            jsonObject.put("user", user);
            jsonObject.put("lstDetailVisitBeneficiary",jsonArray );*/
                    jsonObject = new JSONObject(gson.toJson(visita));
                    // jsonObject.put("Visit",gson.toJson(visita) );
                    Log.d("jsonObjectManuel",gson.toJson(visita));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("jsonObjectRequest",visita.toString());
                final JSONObject finalJsonObject = jsonObject;


                //Consume el WebService para grabar

                AndroidNetworking.post(LOGIN_Url)
                        .addJSONObjectBody(finalJsonObject) // posting json
                        //.addBodyParameter("visita", String.valueOf(visita))
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {

                            @Override
                            public void onResponse(JSONObject response) {
                                // Get Respuesta servicio Login
                                try {
                                    int codigo = response.getInt("idVisit");
                                    String latitud = response.getString("longitude");
                                    String longitud = response.getString("latitude");
                                    Toast.makeText(getActivity().getApplicationContext(), "Se registró la visita correctamente.", Toast.LENGTH_SHORT).show();
                                    Log.i("onResponse", "Resultado : " + String.valueOf(codigo) + " - " + latitud + " - " + longitud);
                                    Intent menu = new Intent(getActivity(), MenuActivity.class);
                                    startActivity(menu );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity().getApplicationContext(), "Ocurrió un problema al registrar la visita.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                anError.printStackTrace();
                                Log.d("getLogin: Error", anError.getErrorBody());
                                Toast.makeText(getActivity().getApplicationContext(), "Ocurrió un problema al registrar la visita.", Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        });

        campo_nombre.setText("");

        return view;
    }

    @Override
    public void onClick(View v) {

    }


    private String getKeptId() {
        // Obtains stored name if present,
        // otherwise returns an empty string
        if(sharedPreferences == null) {
            ContextWrapper wrapper = new ContextWrapper(getActivity().getApplicationContext());
            sharedPreferences = wrapper.getSharedPreferences(
                    PREFS, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(PREF_ID, "0");
    }



}