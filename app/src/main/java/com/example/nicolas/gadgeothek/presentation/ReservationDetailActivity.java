package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.domain.Gadget;
import com.example.nicolas.gadgeothek.domain.Reservation;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nicolas on 02.11.16.
 */

public class ReservationDetailActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);
        setTitle("Reservation Detail");


        final Reservation reservation = (Reservation)this.getIntent().getSerializableExtra("gadget");
        Button btnLoan = (Button)findViewById(R.id.btnLoan);

        btnLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LibraryService.setServerAddress("http://10.0.2.2:8080/public");
                LibraryService.deleteReservation(reservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        Intent reservationsIntent = new Intent(getApplicationContext(), ReservationsActivity.class);
                        reservationsIntent.putExtra("path","ReservationDetailActivity");
                        startActivity(reservationsIntent);
                    }

                    @Override
                    public void onError(String message) {

                    }
                });



            }
        });



        if (reservation != null) {


            TextView txtProduct = (TextView) findViewById(R.id.txtValueProduct);
            TextView txtReservationDate = (TextView) findViewById(R.id.txtValueReservationDate);
            TextView txtState = (TextView)findViewById(R.id.txtValueState);




            txtProduct.setText(reservation.getGadget().getName());
            String reservationDate = new SimpleDateFormat("dd.MM.yyyy").format(reservation.getReservationDate()).toString();
            txtReservationDate.setText(reservationDate);

            String status = reservation.getWatingPosition() == 0 ? "Abholbereit" : "Warteschlange-Position "+reservation.getWatingPosition();

            txtState.setText(status);





        } else {
            System.out.println("Gadget error");
        }




    }
}
