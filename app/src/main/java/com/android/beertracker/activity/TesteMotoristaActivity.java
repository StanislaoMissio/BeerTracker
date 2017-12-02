package com.android.beertracker.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.beertracker.R;
import com.android.beertracker.activity_teste_motorista;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Érica Moreli on 29/11/2017.
 */

public class TesteMotoristaActivity extends activity_teste_motorista {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_motorista);

        listView = (ListView) findViewById(R.id.lstvMotorista);
        String[] nomes = {"Erica", "Lucas", "Bruno", "Ariele", "Sranislao"};
        arrayList = new ArrayList<>(Arrays.asList(nomes));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.edtNome);
        Button btnSorteio = (Button) findViewById(R.id.btnSorteio);
        btnSorteio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addItem = editText.getText().toString();
                arrayList.add(addItem);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),"Item added in List", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position) + " is selected ", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
