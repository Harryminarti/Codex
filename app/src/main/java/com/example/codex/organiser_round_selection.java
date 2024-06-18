package com.example.codex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class organiser_round_selection extends AppCompatActivity {

    TextView round1,round2;

    Button play1,play2;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_organiser_round_selection);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("play");

        round1 = findViewById(R.id.round1);
        round2 = findViewById(R.id.round2);

        play1=findViewById(R.id.round1_playbtn);
        play2=findViewById(R.id.round2_playbtn);

        round1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(organiser_round_selection.this,organsier_home_page.class));

            }
        });

        round2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(organiser_round_selection.this,organiser_round2.class));
            }
        });

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("round1").setValue("start").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(organiser_round_selection.this,"Round 1 is Started",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("round2").setValue("start").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(organiser_round_selection.this,"Round 2 is Started",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
}