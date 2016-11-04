package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.domain.Reservation;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;
import com.example.nicolas.gadgeothek.service.Server;

import java.text.SimpleDateFormat;

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

                LibraryService.setServerAddress(new Server("public").server);
                LibraryService.deleteReservation(reservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        Intent reservationsIntent = new Intent(getApplicationContext(), ReservationsActivity.class);
                        reservationsIntent.putExtra("path","ReservationDetailActivity");
                        startActivity(reservationsIntent);
                    }

                    @Override
                    public void onError(String message) {
                        showMessage(message,Toast.LENGTH_SHORT);
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

    private void showMessage(String message, int toastLength) {
        Toast toast = Toast.makeText(ReservationDetailActivity.this, message, toastLength);
        toast.show();
    }
}
