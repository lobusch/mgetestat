package com.example.nicolas.gadgeothek.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;

public class MenuActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

        Intent gadgetOverviewIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(gadgetOverviewIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_new);

        setTitle("Menü");

        ListView menu = (ListView)findViewById(R.id.mainMenu);

        String[] myStringArray = new String[] {
                "Gadgeothek",
                "Ausgeliehene Gadgets & Reservationen",
                "Benutzerdaten",
                "Logout"
        };


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, myStringArray);

        menu.setAdapter(arrayAdapter);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position) {
                    case 0:
                        Intent registerIntent = new Intent(getApplicationContext(), GadgetOverviewActivity.class);
                        startActivity(registerIntent);
                        break;
                    case 1:
                        Intent reservationIntent = new Intent(getApplicationContext(), ReservationsActivity.class);
                        startActivity(reservationIntent);
                        break;
                    case 2:
                        Intent accountIntent = new Intent(getApplicationContext(), AccountActivity.class);
                        startActivity(accountIntent);
                        break;
                    case 3:
                        LibraryService.logout(new Callback<Boolean>() {
                            @Override
                            public void onCompletion(Boolean success) {
                                if (success) {

                                    Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(loginIntent);
                                } else {
                                    Toast.makeText(MenuActivity.this, "Logout ist fehlgeschlagen.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(String message) {
                                Toast.makeText(MenuActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                }

            }
        });

    }
}
