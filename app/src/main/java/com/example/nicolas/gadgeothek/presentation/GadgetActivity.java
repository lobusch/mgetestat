package com.example.nicolas.gadgeothek.presentation;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.domain.Gadget;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;
import com.example.nicolas.gadgeothek.service.Server;

import static android.R.id.content;

public class GadgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget);

        final Gadget gadget = (Gadget)this.getIntent().getSerializableExtra("gadget");
        Button btnLoan = (Button)findViewById(R.id.btnLoan);

        btnLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LibraryService.setServerAddress(new Server("public").server);
                LibraryService.reserveGadget(gadget, new Callback<Boolean>() {

                    @Override
                    public void onCompletion(Boolean success) {
                        if(success) {
                            Intent reservationsIntent = new Intent(getApplicationContext(), ReservationsActivity.class);
                            reservationsIntent.putExtra("path","GadgetActivity");
                            startActivity(reservationsIntent);
                        } else {
                            errorMessage("Sie haben dieses Gadget entweder schon reserviert oder Ihre maximale Anzahl an Reservationen schon erreicht.");
                        }
                    }

                    @Override
                    public void onError(String message) {
                        errorMessage(message);
                    }
                });


            }
        });



        if (gadget != null) {

            setTitle(gadget.getName());

            TextView txtPrice = (TextView) findViewById(R.id.txtValuePrice);
            TextView txtManufacturer = (TextView) findViewById(R.id.txtValueProducer);
            TextView txtCondition = (TextView) findViewById(R.id.txtValueCondition);

            txtManufacturer.setText(gadget.getManufacturer());
            Integer price = (int)gadget.getPrice();
            txtPrice.setText(price.toString()+" CHF");
            txtCondition.setText(gadget.getCondition().toString());



        } else {
            errorMessage("Da ist leider ein Problem aufgetretten");
        }


    }

    private void errorMessage(String message) {
        Toast toast = Toast.makeText(GadgetActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


}
