package com.android.beertracker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.beertracker.R;
import com.android.beertracker.adapter.DriverAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MotoristaActivity extends AppCompatActivity{

    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;
    Toolbar toolbarDriver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);

        setSupportActionBar(toolbarDriver);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new DriverAdapter());

        adapter=new ArrayAdapter<String>(this,R.layout.view_list_drivers,R.id.name_driver,itemList);
        ListView listV = findViewById(R.id.list);
        listV.setAdapter(adapter);
        editText = findViewById(R.id.txtInput);
        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=editText.getText().toString();
                // add new item to arraylist
                itemList.add(newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }

        });

    }

}
