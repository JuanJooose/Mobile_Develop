package com.example.firststepsjava;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView texts, reloginTextx;
    Button loginBtn;
    EditText userETxt;
    Intent dataManagenment;
    UserEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
        userETxt = (EditText) findViewById(R.id.usernameEtxt);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        reloginTextx = (TextView) findViewById(R.id.Text_reLogin);
        user = new UserEntity((byte) 1, (byte) 20, "", "Gil");

    }

    @Override
    protected void onStart() {
        super.onStart();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFirstname(userETxt.getText().toString());
                texts = (TextView) findViewById(R.id.txtError);

                if (!(user.getFirstname().isEmpty())) {
                    Log.d("tag",user.getFirstname().concat(" ").concat(user.getLasstname()));
                    dataManagenment = new Intent(MainActivity.this, ProfileActivity.class);
                    dataManagenment.putExtra("Username", user.getFirstname().concat(" ").concat(user.getLasstname()));
                    dataManagenment.putExtra("Age",String.valueOf(user.getAge()));
                    dataManagenment.putExtra("Id", String.valueOf(user.getId()));
                    startActivity(dataManagenment);
                    texts.setText("");
                    texts.setBackgroundResource(R.color.primary);
                } else {
                    texts.setText(R.string.Text_ErrorUsername);
                    texts.setBackgroundResource(R.drawable.rounded_error);
                }
            }
        });

        dataManagenment = getIntent();
        if (dataManagenment.hasExtra("relogin")) {
            reloginTextx.setText(dataManagenment.getStringExtra("relogin"));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        dataManagenment = new Intent(MainActivity.this, Loader.class);
        startActivity(dataManagenment);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}