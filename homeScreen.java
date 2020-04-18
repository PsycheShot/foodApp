package com.example.myfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class homeScreen extends AppCompatActivity {
    Button b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        b = (Button) findViewById(R.id.btnSignup);
        c = (Button) findViewById(R.id.btnLogin);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homeScreen.this, "Please wait", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(homeScreen.this,SignUp_Form.class);
                startActivity(intent);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homeScreen.this, "Please wait", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(homeScreen.this,loginform.class);
                startActivity(intent);
            }
        });
    }


}
