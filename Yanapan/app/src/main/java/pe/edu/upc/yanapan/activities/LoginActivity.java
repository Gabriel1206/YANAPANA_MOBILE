package pe.edu.upc.yanapan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.upc.yanapan.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.LogIn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String user = ((EditText) findViewById(R.id.c_name)).getText().toString();
                String password = ((EditText) findViewById(R.id.c_password)).getText().toString();
                if (user.equals("admin")&& password.equals("admin")) {

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


}
