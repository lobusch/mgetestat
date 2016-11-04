package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;
import com.example.nicolas.gadgeothek.service.Server;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        if(getIntent().getStringExtra("hint")!=null) {
            showMessage(getIntent().getStringExtra("hint"),Toast.LENGTH_LONG);
        }

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

                LibraryService.setServerAddress(new Server("public").server);

                LibraryService.login(txtEmailAddress, txtPassword, new Callback<Boolean>() {

                    @Override
                    public void onCompletion(Boolean success) {
                        if (success) {

                            startActivity(i);
                        } else {
                            showMessage("Falsche Eingaben.",Toast.LENGTH_SHORT);
                        }
                    }



                    @Override
                    public void onError(String message) {
                        showMessage("Anmeldedaten unbekannt.",Toast.LENGTH_SHORT);
                    }

                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    //
                });

            }

        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void showMessage(String message, int toastLength) {
        Toast toast = Toast.makeText(LoginActivity.this, message, toastLength);
        toast.show();
    }
}
