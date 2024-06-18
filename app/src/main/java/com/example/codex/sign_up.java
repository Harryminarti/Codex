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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    EditText email,password;
    TextView login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.login);
        email= findViewById(R.id.email);
        password = findViewById(R.id.pass);
        signup = findViewById(R.id.Sign_up);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emails = email.getText().toString().trim();

                String passwords = password.getText().toString().trim();

                if(emails.isEmpty()||passwords.isEmpty()){
                    Toast.makeText(sign_up.this,"Please fill the field",Toast.LENGTH_LONG).show();
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(emails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                Toast.makeText(sign_up.this, "You are signup successfully", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(sign_up.this, Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(sign_up.this, task.getException().toString(), Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_up.this, Login.class);
                startActivity(intent);
            }
        });





    }
}