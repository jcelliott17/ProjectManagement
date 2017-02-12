package com.example.jackieelliott.team60application.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jackieelliott.team60application.R;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class RegisterUserActivity extends Activity{

    Button registerBotton;
    Button cancelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        addListenerOnButtonRegister();
        addListenerOnButtonCancel();
    }

    public void addListenerOnButtonRegister() {

        final Context context = this;

        registerBotton = (Button) findViewById(R.id.registerOnRegisterPage);

        registerBotton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);

            }

        });

    }

    public void addListenerOnButtonCancel() {

        final Context context = this;

        cancelButton = (Button) findViewById(R.id.cancelOnRegisterPage);

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, WelcomePageActivity.class);
                startActivity(intent);

            }

        });

    }
}
