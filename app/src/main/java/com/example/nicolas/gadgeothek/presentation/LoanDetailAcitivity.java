package com.example.nicolas.gadgeothek.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.domain.Loan;
import com.example.nicolas.gadgeothek.domain.Reservation;

import java.text.SimpleDateFormat;

public class LoanDetailAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_detail_acitivity);

        setTitle("Ausleihe Detail");

        final Loan loan = (Loan)this.getIntent().getSerializableExtra("loan");



        if (loan != null) {


            TextView txtProduct = (TextView) findViewById(R.id.txtValueProduct);
            TextView txtReservationDate = (TextView) findViewById(R.id.txtValueReservationDate);
            TextView txtState = (TextView)findViewById(R.id.txtValueState);




            txtProduct.setText(loan.getGadget().getName());
            String reservationDate = new SimpleDateFormat("dd.MM.yyyy").format(loan.getPickupDate()).toString();
            txtReservationDate.setText(reservationDate);






        } else {
            System.out.println("Gadget error");
        }



    }
}
