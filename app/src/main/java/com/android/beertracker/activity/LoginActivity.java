package com.android.beertracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.beertracker.R;
import com.android.beertracker.entity.User;
import com.android.beertracker.infrastructure.OperationListener;
import com.android.beertracker.manager.UserManager;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText loginText;
    EditText passwordText;
    Button buttonLogin;
    TextView signInText;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userManager = new UserManager(this);

        loginText = findViewById(R.id.login_text);
        passwordText = findViewById(R.id.password_text);
        buttonLogin = findViewById(R.id.login);
        signInText = findViewById(R.id.signin_text);

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(null, passwordText.getText().toString(), loginText.getText().toString());
                userManager.loginUser(user, new OperationListener() {
                    @Override
                    public void onOperationSuccess(Object o) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onOperationError(Object o, List list) {
                        Snackbar.make(loginText,"Erro ao efetuar login", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
