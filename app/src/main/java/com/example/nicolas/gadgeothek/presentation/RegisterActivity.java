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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registrieren");


        LibraryService.setServerAddress(new Server("public").server);

        Button btnRegistration = (Button) findViewById(R.id.buttonRegistration);



        btnRegistration.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
                EditText inputMartikelNr = (EditText) findViewById(R.id.inputMartikelNr);
                EditText inputEmailAddress = (EditText) findViewById(R.id.inputEmailAddress);
                EditText inputAddress = (EditText)findViewById(R.id.inputPostalAddress);
                EditText inputName = (EditText)findViewById(R.id.inputName);

                String txtEmailAddress = inputEmailAddress.getText().toString();
                String txtMartikelNr = inputMartikelNr.getText().toString();
                String txtAddress = inputAddress.getText().toString();
                String txtName = inputName.getText().toString();
                String txtPassword = inputPassword.getText().toString();

                boolean error = false;

                if(txtPassword.length() < 4) {
                    error = true;
                    inputPassword.requestFocus();
                    inputPassword.setError("Bite Passwort mit min. 4 Zeichen eingeben");
                }

                if(txtMartikelNr.length() < 4) {
                    error = true;
                    inputMartikelNr.requestFocus();
                    inputMartikelNr.setError("Bitte gültige MarikelNr eingeben.");

                }

                if(!isValidEmail(txtEmailAddress)) {
                    error = true;
                    inputEmailAddress.requestFocus();
                    inputEmailAddress.setError("Bitte gültige Email-Adresse eingeben");
                }

                if(txtName.length() < 4) {
                    error = true;
                    inputName.requestFocus();
                    inputName.setError("Bitte Namen eingeben. Min. 3 Zeichen");

                }





                if(!error) {

                    LibraryService.register(txtEmailAddress, txtPassword, txtName, txtMartikelNr, new Callback<Boolean>() {

                        @Override
                        public void onCompletion(Boolean success) {
                            if (success) {
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                i.putExtra("hint","Danke für die Anmeldung. Sie können sich nun einloggen.");
                                startActivity(i);
                            } else {
                                showMessage("Da ist leider ein Problem aufgetretten");
                            }
                        }

                        @Override
                        public void onError(String message) {
                            showMessage(message);
                        }


                        //
                    });
                }

            }

        });

    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}

