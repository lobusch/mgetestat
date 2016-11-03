package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
            showMessage(getIntent().getStringExtra("hint"));
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

        final EditText inputEmailAddress = (EditText) findViewById(R.id.inputEmailAddress);

        inputEmailAddress.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                if (s.length() > 5) {
                    if(!isValidEmail(email))
                     inputEmailAddress.setError("Bitte gib eine gültige Email-Adresse ein.");
                }
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText inputPassword = (EditText) findViewById(R.id.inputPassword);

                String txtEmailAddress = inputEmailAddress.getText().toString();
                String txtPassword = inputPassword.getText().toString();

                //LibraryService.setServerAddress("http://mge10.dev.ifs.hsr.ch/public");
                LibraryService.setServerAddress(new Server("public").server);



                LibraryService.login(txtEmailAddress, txtPassword, new Callback<Boolean>() {

                    @Override
                    public void onCompletion(Boolean success) {
                        if (success) {

                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "Falsche Eingaben", Toast.LENGTH_SHORT).show();
                        }
                    }



                    @Override
                    public void onError(String message) {
                        Toast.makeText(LoginActivity.this, "Anmeldedaten unbekannt.", Toast.LENGTH_LONG).show();
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

    private void showMessage(String message) {
        Toast toast = Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
