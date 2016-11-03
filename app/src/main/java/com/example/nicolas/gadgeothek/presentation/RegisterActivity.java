package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        LibraryService.setServerAddress("http://10.0.2.2:8080/public");



        Button btnRegistration = (Button) findViewById(R.id.buttonRegistration);

        btnRegistration.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText inputEmailAddress = (EditText) findViewById(R.id.inputEmailAddress);
                EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
                EditText inputMartikelNr = (EditText) findViewById(R.id.inputMartikelNr);
                EditText inputAddress = (EditText)findViewById(R.id.inputPostalAddress);
                EditText inputName = (EditText)findViewById(R.id.inputName);

                String txtEmailAddress = inputEmailAddress.getText().toString();
                String txtMartikelNr = inputMartikelNr.getText().toString();
                String txtAddress = inputAddress.getText().toString();
                String txtName = inputName.getText().toString();
                String txtPassword = inputPassword.getText().toString();


                LibraryService.register(txtEmailAddress,txtPassword,txtName,txtMartikelNr, new Callback<Boolean>() {

                    @Override
                    public void onCompletion(Boolean success) {
                        System.out.println("geht");
                        if (success) {
                            startActivity(i);
                        } else {
                            System.out.println("geht nicht");
                        }
                    }

                    @Override
                    public void onError(String message) {
                        // Fehler z.B. in einem Toast/Snackbar darstellen
                    }

                    Intent i = new Intent(getApplicationContext(), GadgetOverviewActivity.class);
                    //
                });

            }

        });

    }
}
