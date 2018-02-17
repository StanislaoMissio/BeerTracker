package com.android.beertracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.beertracker.R;
import com.android.beertracker.adapter.DriverAdapter;

import java.util.Random;

public class MotoristaActivity extends AppCompatActivity {

    private DriverAdapter adapter;
    private EditText editText;
    Toolbar toolbarDriver;
    public final static String NAME_DRIVER = "name_driver";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);
        toolbarDriver = findViewById(R.id.toolbarDriver);
        setSupportActionBar(toolbarDriver);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.txtInput);

        final RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        adapter = new DriverAdapter();

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                adapter.addDriver(newItem);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }

        });

        Button random = findViewById(R.id.btnSortear);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getItemCount() > 0) {
                    Intent intent = new Intent(MotoristaActivity.this, ChosenDriverActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(NAME_DRIVER, adapter.getDriverAtPosition(randomDriver()));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    private int randomDriver(){
        Random rand = new Random();
        return rand.nextInt(4);
    }

}
