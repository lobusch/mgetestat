package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nicolas.gadgeothek.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setTitle("Benutzerdaten");

        Spinner spinner = (Spinner) findViewById(R.id.server_gadgeothek_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.server_gadgeothek, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btnSaveSettings = (Button) findViewById(R.id.btnSaveSettings);
        btnSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Die Einstellungen wurden gespeichert.", Toast.LENGTH_LONG);
            }
        });

    }

    private void showMessage(String message, int toastLength) {
        Toast toast = Toast.makeText(AccountActivity.this, message, toastLength);
        toast.show();
    }
}
