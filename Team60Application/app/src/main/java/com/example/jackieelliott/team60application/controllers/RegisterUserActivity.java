package com.example.jackieelliott.team60application.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.jackieelliott.team60application.R;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class RegisterUserActivity extends Activity{

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
    }
}
