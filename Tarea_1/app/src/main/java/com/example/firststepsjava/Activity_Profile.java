package com.example.firststepsjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Profile extends AppCompatActivity {

    TextView userTView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
        userTView = findViewById(R.id.textUser);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent getData = getIntent();
        String username = getData.getStringExtra("Username");

        userTView.setText(username);
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        String text = "You should login again.";
        Intent setData = new Intent(Activity_Profile.this, MainActivity.class);
        setData.putExtra("relogin", text);
        startActivity(setData);
    }
}