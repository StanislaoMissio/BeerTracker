package com.android.beertracker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.beertracker.R;
import com.android.beertracker.entity.User;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.UserManager;

import java.util.Calendar;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeText;
    private EditText emailText;
    private EditText passwordText;
    Calendar myCalendar;
    Button btnRegister;
    Button btnCancelar;
    private UserManager userManager;
    private CheckBox checkAge;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        userManager = new UserManager(this);

        nomeText = findViewById(R.id.nome_cadastro);
        emailText = findViewById(R.id.email_cadastro);
        passwordText = findViewById(R.id.senha_cadastro);
        myCalendar = Calendar.getInstance();
        btnCancelar = findViewById(R.id.btnCancelar);
        btnRegister = findViewById(R.id.btnCadastrar);
        checkAge = findViewById(R.id.check_age);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(checkAge.isChecked()) {
                    User user = new User(nomeText.getText().toString(), passwordText.getText().toString(), emailText.getText().toString());
                    userManager.registerUser(user, new OperationListener() {
                        @Override
                        public void onOperationSuccess(Object o) {
                            Toast.makeText(getBaseContext(), "Cadastro Executado com Sucesso", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        }

                        @Override
                        public void onOperationError(Object o, List list) {
                            Snackbar.make(view, "Erro ao executar cadastro", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Snackbar.make(checkAge, "Você precisa confirmar que possui 18 anos ou mais", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
