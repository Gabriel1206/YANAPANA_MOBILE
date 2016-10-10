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

public class LoginActivity extends AppCompatActivity{
    public String v_user;
    public String v_pass;
    public String[] respuestaAfuera = new String[2];

    Button button;
    EditText user;
    EditText password;

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
                Log.d("user", "[" + user.getText().toString() + "]");
                Log.d("password", "[" + password.getText().toString() + "]");
                String[] respuestaAfuera = getLogin(user.getText().toString(), password.getText().toString());
                Log.d("Respuesta Afuera",respuestaAfuera[0] + " - " + respuestaAfuera[1]);

                if (user.getText().toString().equals(v_user)&& password.getText().toString().equals(v_pass)) {
                //if (user.equals(v_user)&& password.equals(v_pass)) {
                    Intent menu = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(menu);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ;
    }

    private String[] getLogin(String userNick, String password){
        //final String LOGIN_Url = "http://192.168.1.11:8080/Yanapan/rest/v1/users?user=" + userNick + "&password=" + password;
        //final String[] respuesta = {""};
        final String[] respuesta = new String[2];
        String cadena = "http://acmmh.siteli.com.pe:8080/Yanapan/rest/v1/users?user=";
        cadena = cadena + userNick;
        cadena = cadena + "&password=";
        cadena = cadena + password;
        Log.d("URL_WS",cadena);
        final String LOGIN_Url = cadena;

        Log.d("getLogin",cadena);

        AndroidNetworking.get(LOGIN_Url).setPriority(Priority.HIGH).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                // Get Respuesta servicio Login
                try {
                    v_user = response.getString("nickUser");
                    v_pass = response.getString("password");
                    //respuesta[0] = v_user + "," + v_pass;
                    respuesta[0] = v_user;
                    respuesta[1] = v_pass;
                    Log.d("Respuesta Dentro",respuesta[0]+","+respuesta[1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.d("getLogin: Error","Error:"  + anError.toString());
            }
        });


        return respuesta;
    }
}
