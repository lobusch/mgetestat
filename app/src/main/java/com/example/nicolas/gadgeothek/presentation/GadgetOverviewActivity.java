package com.example.nicolas.gadgeothek.presentation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nicolas.gadgeothek.R;
import com.example.nicolas.gadgeothek.domain.Gadget;
import com.example.nicolas.gadgeothek.service.Callback;
import com.example.nicolas.gadgeothek.service.LibraryService;
import com.example.nicolas.gadgeothek.service.Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GadgetOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        LibraryService.setServerAddress(new Server().server);


        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                fillList(input);
            }

            @Override
            public void onError(String message) {

            }

        });



    }

    @Override
    public void onBackPressed() {

        Intent gadgetOverviewIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(gadgetOverviewIntent);

    }

    private void fillList(final List<Gadget> input) {

        final ListView listGadgets = (ListView) findViewById(R.id.listview);

        List<String> myStringArray = new ArrayList<>();

        for(Gadget g:input) {
            System.out.println(g.getName());
            myStringArray.add(g.getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, myStringArray);

        // DataBind ListView with items from ArrayAdapter
        listGadgets.setAdapter(arrayAdapter);

        listGadgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent gadgetActivity = new Intent(getApplicationContext(), GadgetActivity.class);

                System.out.println("asdasd");
                Gadget gadget = input.get(position);

                gadgetActivity.putExtra("gadget", gadget);

                System.out.println("out_there");
                startActivity(gadgetActivity);
            }

        });
    }






}
