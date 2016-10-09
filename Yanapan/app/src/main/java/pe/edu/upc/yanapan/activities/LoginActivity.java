package pe.edu.upc.yanapan.activities;

import android.content.Intent;
import android.icu.util.RangeValueIterator;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import pe.edu.upc.yanapan.R;

public class LoginActivity extends AppCompatActivity {
    public String v_user;
    public String v_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inicializa Android Networking Library
        AndroidNetworking.initialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.LogIn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String user = ((EditText) findViewById(R.id.c_name)).getText().toString();
                String password = ((EditText) findViewById(R.id.c_password)).getText().toString();
                Log.d("user",user);
                Log.d("password",password);

                try {
                    getLogin(user, password);
                }catch (Exception e){
                    Log.d("Error_WEBService",e.getMessage());
                }
                //Log.d("Usuario: ", v_user);
                //Log.d("Password: ", v_pass.toString());
                //if (user.equals("admin")&& password.equals("admin")) {
                if (user.equals(v_user)&& password.equals(v_pass)) {
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

    private void getLogin(String userNick, String password){
        //final String LOGIN_Url = "http://192.168.1.11:8080/Yanapan/rest/v1/users?user=" + userNick + "&password=" + password;
        final String LOGIN_Url = "http://acmmh.siteli.com.pe:8080/Yanapan/rest/v1/users?user" + userNick + "&password=" + password;
        Log.d("getLogin","http://acmmh.siteli.com.pe:8080/Yanapan/rest/v1/users?user=" + userNick + "&password=" + password);

        AndroidNetworking.get(LOGIN_Url).setPriority(Priority.HIGH).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                // Get Respuesta servicio Login
                try {
                    v_user = response.getString("nickUser");
                    v_pass = response.getString("password");
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

}
