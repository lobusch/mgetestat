package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        Button btnRegister = (Button)findViewById(R.id.registerLink);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inputEmailAddress = (EditText) findViewById(R.id.inputEmailAddress);
                EditText inputPassword = (EditText) findViewById(R.id.inputPassword);

                String txtEmailAddress = inputEmailAddress.getText().toString();
                String txtPassword = inputPassword.getText().toString();

                //LibraryService.setServerAddress("http://mge10.dev.ifs.hsr.ch/public");
                LibraryService.setServerAddress("http://10.0.2.2:8080/public");



                LibraryService.login(txtEmailAddress, txtPassword, new Callback<Boolean>() {

                    @Override
                    public void onCompletion(Boolean success) {
                        System.out.println("geht");
                        if (success) {

                            startActivity(i);
                        } else {
                            System.out.println("geht nicht");
                            Toast.makeText(LoginActivity.this, "Falsche Eingaben", Toast.LENGTH_SHORT).show();
                        }
                    }



                    @Override
                    public void onError(String message) {
                        // Fehler z.B. in einem Toast/Snackbar darstellen
                        Toast.makeText(LoginActivity.this, "Anmeldedaten unbekannt.", Toast.LENGTH_SHORT).show();
                    }

                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    //
                });

            }

        });
    }
}
