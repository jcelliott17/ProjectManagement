package com.example.jackieelliott.team60application.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.jackieelliott.team60application.R;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class LoginActivity extends Activity {

    Button login;
    Button cancel;
    EditText loginField;
    EditText passField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        addListenerOnButtonLogin();
        addListenerOnButtonCancel();
    }

    public void addListenerOnButtonLogin() {

        final Context context = this;

        login = (Button) findViewById(R.id.login_button);
        loginField = (EditText) findViewById(R.id.username_text);
        passField = (EditText) findViewById(R.id.editText2);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, HomeActivity.class);
                if (loginField.getText().toString().equals("user")
                        && passField.getText().toString().equals("pass")) {
                    startActivity(intent);
                }

            }

        });

    }

    public void addListenerOnButtonCancel() {

        final Context context = this;

        cancel = (Button) findViewById(R.id.login_Cancel);

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, WelcomePageActivity.class);
                startActivity(intent);

            }

        });

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
