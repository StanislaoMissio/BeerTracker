package com.android.beertracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beertracker.R;

public class ChosenDriver extends AppCompatActivity {

    ImageView imageDriver;
    TextView nameDriver;
    Button chooseAgain;
    Button home;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_driver);
        imageDriver = findViewById(R.id.profile_driver);
        imageDriver.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_camera));

        nameDriver = findViewById(R.id.name_driver);
        try {
            nameDriver.setText(getIntent().getExtras().getString(MotoristaActivity.NAME_DRIVER));
        } catch (NullPointerException e){
            onBackPressed();
            finish();
            Snackbar.make(imageDriver, "Ocorreu um erro ao sortear um ", Snackbar.LENGTH_SHORT).show();
        }
        chooseAgain = findViewById(R.id.random_again);
        chooseAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChosenDriver.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
