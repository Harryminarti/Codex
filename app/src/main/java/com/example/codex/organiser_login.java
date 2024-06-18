package com.example.codex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class organiser_login extends AppCompatActivity {

    EditText email,password;
    TextView signup;
    Button login;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_organiser_login);



        firebaseAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.login);
        email= findViewById(R.id.email);
        password = findViewById(R.id.pass);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emails = email.getText().toString().trim();

                String passwords = password.getText().toString().trim();
                if(emails.isEmpty()||passwords.isEmpty()){
                    Toast.makeText(organiser_login.this,"Fill all the field",Toast.LENGTH_LONG).show();
                }else{
                    firebaseAuth.signInWithEmailAndPassword(emails,passwords).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(organiser_login.this,"Login successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(organiser_login.this,organiser_round_selection.class));
                            finish();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(organiser_login.this,"Your id & pass is wrong",Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });



    }
}