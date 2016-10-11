package pe.edu.upc.yanapan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONException;
import org.json.JSONObject;
import pe.edu.upc.yanapan.R;
import android.content.SharedPreferences;

public class LoginActivity extends AppCompatActivity{
    public String v_user;
    public String v_pass;
    public String v_idUser;
    public String[] respuestaAfuera = new String[2];

    private static final String PREFS = "prefs";
    private static final String PREF_ID = "idUser";

    Button button;
    EditText user;
    EditText password;

    // Preferences object
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inicializa Android Networking Library
        AndroidNetworking.initialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.LogIn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            user = (EditText) findViewById(R.id.c_name);
            password = (EditText) findViewById(R.id.c_password);
            getLogin(user.getText().toString(), password.getText().toString());
            }
        });
    }

    private void getLogin(String j_userNick, String j_password){
        final String[] respuesta = new String[2];
        String cadena = "http://acmmh.siteli.com.pe:8080/Yanapan/rest/v1/users?user=";
        cadena = cadena + j_userNick;
        cadena = cadena + "&password=";
        cadena = cadena + j_password;
        Log.d("URL_WS",cadena);
        final String LOGIN_Url = cadena;

        final String idUser = getKeptId();
        Log.d("idUserIni" , idUser);
        AndroidNetworking.get(LOGIN_Url).setPriority(Priority.HIGH).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                // Get Respuesta servicio Login
                try {
                    v_user = response.getString("nickUser");
                    v_pass = response.getString("password");
                    v_idUser = response.getString("idUser");
                    //respuesta[0] = v_user + "," + v_pass;
                    respuesta[0] = v_user;
                    respuesta[1] = v_pass;
                    if (user.getText().toString().equals(v_user)&& password.getText().toString().equals(v_pass)) {
                        setKeptId(v_idUser);
                        Log.d("idUserFin" , idUser);
                        Intent menu = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(menu);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.d("getLogin: Error","Error:"  + anError.toString());
                Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String getKeptId() {
        // Obtains stored name if present,
        // otherwise returns an empty string
        if(sharedPreferences == null) {
            sharedPreferences = getSharedPreferences(
                    PREFS, MODE_PRIVATE);
        }
        return sharedPreferences.getString(PREF_ID, "");
    }


    private void setKeptId(String idUser) {
        // Stores given name in Preferences
        if(sharedPreferences != null) {
            // Opens Preferences file for edition
            SharedPreferences.Editor e = sharedPreferences.edit();
            e.putString(PREF_ID, idUser);
            e.commit();
        }
    }
}
