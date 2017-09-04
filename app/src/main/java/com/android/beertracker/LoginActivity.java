package com.android.beertracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText loginText;
    private EditText passwordText;
    private Button buttonLogin;
    private Button buttonLoginFacebook;
    private TextView signInText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginText = (EditText) findViewById(R.id.login_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        buttonLogin = (Button) findViewById(R.id.login);
        buttonLoginFacebook = (Button) findViewById(R.id.facebook_login);
        signInText = (TextView) findViewById(R.id.signin_text);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("login", loginText.getText());
                startActivity(intent);
            }
        });
    }

}
