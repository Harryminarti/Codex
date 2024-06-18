package com.example.codex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class choose_activity extends AppCompatActivity {

    TextView organiser;
    TextView participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose);

        organiser = findViewById(R.id.Organiser);
        participant = findViewById(R.id.Participate);

        organiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(choose_activity.this,organiser_login.class);
                startActivity(intent);

            }
        });

        participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(choose_activity.this,sign_up.class);
                startActivity(intent);

            }});



    }


}

