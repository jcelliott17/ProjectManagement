package com.example.jackieelliott.team60application.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jackieelliott.team60application.R;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class WelcomePageActivity extends AppCompatActivity {

    Button button;
    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        addListenerOnButton();
        addListenerOnButtonLogin();
    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.register_button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, RegisterUserActivity.class);
                startActivity(intent);

            }

        });

    }

    public void addListenerOnButtonLogin() {

        final Context context = this;

        button2 = (Button) findViewById(R.id.login_button);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);

            }

        });

    }

}
