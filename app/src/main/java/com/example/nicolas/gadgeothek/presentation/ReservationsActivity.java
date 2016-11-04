package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.domain.Loan;
import com.example.nicolas.gadgeothek.domain.Reservation;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;
import com.example.nicolas.gadgeothek.service.Server;

import java.util.ArrayList;
import java.util.List;

public class ReservationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        setTitle("Ausgeliehene Gadgets & Reservationen");

        String lastActivity = getIntent().getStringExtra("path");

        if(lastActivity != null) {
            if (lastActivity.equals("GadgetActivity")) {
                showMessage("Das Produkt wurde reserviert.",Toast.LENGTH_LONG);
            } else if (lastActivity.equals("ReservationDetailActivity")) {
                showMessage("Die Reservation wurde entfernt.",Toast.LENGTH_LONG);
            }
        }

        LibraryService.setServerAddress(new Server("public").server);
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                fillList(input);
            }

            @Override
            public void onError(String message) {

            }
        });

        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                fillListLoans(input);
            }

            @Override
            public void onError(String message) {

            }


        });


    }

    @Override
    public void onBackPressed() {

        String lastActivity = getIntent().getStringExtra("path");

        if(lastActivity != null) {
            Intent gadgetOverviewIntent = new Intent(getApplicationContext(), GadgetOverviewActivity.class);
            startActivity(gadgetOverviewIntent);
        } else {
            Intent menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(menuIntent);
        }
    }



    private void fillList(final List<Reservation> input) {

        if(!input.isEmpty()) {
            final ListView listGadgets = (ListView) findViewById(R.id.listviewReservations);

            List<String> myStringArray = new ArrayList<>();

            for (Reservation g : input) {
                myStringArray.add(g.getGadget().getName());
            }

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);

            // DataBind ListView with items from ArrayAdapter
            listGadgets.setAdapter(arrayAdapter);

            listGadgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent gadgetActivity = new Intent(getApplicationContext(), ReservationDetailActivity.class);
                    Reservation gadget = input.get(position);

                    gadgetActivity.putExtra("gadget", gadget);
                    startActivity(gadgetActivity);
                }

            });
        } else {
            TextView txtInfo = (TextView)findViewById(R.id.txtInfoReservation);
            txtInfo.setVisibility(View.VISIBLE);
        }
    }


    private void fillListLoans(final List<Loan> input) {

        if(!input.isEmpty()) {
            final ListView listLoans = (ListView) findViewById(R.id.listviewLoans);

            List<String> myStringArray = new ArrayList<>();

            for(Loan g:input) {
                myStringArray.add(g.getGadget().getName());
            }

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, myStringArray);

            // DataBind ListView with items from ArrayAdapter
            listLoans.setAdapter(arrayAdapter);

            listLoans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent loanDetailActivity = new Intent(getApplicationContext(), LoanDetailAcitivity.class);
                    Loan loan = input.get(position);

                    loanDetailActivity.putExtra("loan", loan);
                    startActivity(loanDetailActivity);
                }

            });
        } else {
            TextView txtInfoLoan = (TextView)findViewById(R.id.txtInfoLoan);
            txtInfoLoan.setVisibility(View.VISIBLE);
        }
    }


    private void showMessage(String message, int toastLength) {
        Toast toast = Toast.makeText(ReservationsActivity.this, message, toastLength);
        toast.show();
    }

}
