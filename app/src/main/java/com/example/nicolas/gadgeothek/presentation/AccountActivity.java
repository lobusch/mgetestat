package com.example.nicolas.gadgeothek.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nicolas.gadgeothek.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setTitle("Benutzerdaten");
    }
}
