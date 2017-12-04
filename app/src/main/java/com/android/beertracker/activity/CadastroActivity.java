package com.android.beertracker.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.beertracker.R;
import com.android.beertracker.entity.User;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.UserManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeText;
    private EditText emailText;
    private EditText senhaText;
    private EditText dataNasc_cadastro;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private Button btnCadastrar;
    private Button btnCancelar;
    private UserManager userManager;
    private CheckBox checkAge;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        userManager = new UserManager(this);

        nomeText = findViewById(R.id.nome_cadastro);
        emailText = findViewById(R.id.email_cadastro);
        senhaText = findViewById(R.id.senha_cadastro);
        dataNasc_cadastro = findViewById(R.id.dataNasc_cadastro);
        myCalendar = Calendar.getInstance();
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        checkAge = findViewById(R.id.check_age);


        dataNasc_cadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(CadastroActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(checkAge.isChecked()) {
                    User user = new User(nomeText.getText().toString(), senhaText.getText().toString(), emailText.getText().toString());
                    userManager.registerUser(user, new OperationListener() {
                        @Override
                        public void onOperationSuccess(Object o) {
                            Snackbar.make(view, "Cadastro Executado com sucesso", Snackbar.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onOperationError(Object o, List list) {
                            Snackbar.make(view, "Erro ao executar cadastro", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Snackbar.make(checkAge,)
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dataNasc_cadastro.setText(sdf.format(myCalendar.getTime()));
    }
}
