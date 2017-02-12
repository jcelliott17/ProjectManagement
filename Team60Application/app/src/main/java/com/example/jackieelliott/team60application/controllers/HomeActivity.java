package com.example.jackieelliott.team60application.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jackieelliott.team60application.R;

/**
 * Created by JackieElliott on 2/12/17.
 */

public class HomeActivity extends Activity {

    Button logoutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        addListenerOnButtonLogout();
    }

    public void addListenerOnButtonLogout() {

        final Context context = this;

        logoutButton = (Button) findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, WelcomePageActivity.class);
                startActivity(intent);

            }

        });

    }



}
