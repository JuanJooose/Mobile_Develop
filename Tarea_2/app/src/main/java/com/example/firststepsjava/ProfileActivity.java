package com.example.firststepsjava;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    TextView userTView, userAgeTView, userIdTView;
    Button btnTurnAlarm;
    Intent intentUsage;

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

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        userTView = findViewById(R.id.textUser);
        userAgeTView = findViewById(R.id.textVAge);
        userIdTView = findViewById(R.id.textVId);
        btnTurnAlarm = findViewById(R.id.btnTurnAlarm);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent getData = getIntent();
        String username = getData.getStringExtra("Username");
        String age = getData.getStringExtra("Age");
        String id = getData.getStringExtra("Id");
        userTView.setText(username);
        userAgeTView.setText(String.valueOf(age));
        userIdTView.setText(String.valueOf(id));

        btnTurnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTimer("Wake up!!!!!", 5);
            }
        });
    }

    public void startTimer(String message, int seconds) {
        intentUsage = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intentUsage.resolveActivity(getPackageManager()) == null)
            startActivity(intentUsage);


    }


    @Override
    protected void onRestart() {
        super.onRestart();

        String text = "You should login again.";
        Intent setData = new Intent(ProfileActivity.this, MainActivity.class);
        setData.putExtra("relogin", text);
        startActivity(setData);
    }
}