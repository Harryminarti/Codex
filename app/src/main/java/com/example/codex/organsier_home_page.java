package com.example.codex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class organsier_home_page extends AppCompatActivity {

     DatabaseReference database ;

    public EditText question,option1,option2,option3,option4,right_answer;
     Button submit,round1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_organsier_home_page);

        find_id();

       database=  FirebaseDatabase.getInstance().getReference().child("round1");


       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               check_user();
           }
       });



       round1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(organsier_home_page.this,Round1_Score.class));
           }
       });




//----------------------------------------------------------------------------------------------------------------


// ------------------------------------------------------------------------------------------------------------------------


    }


    // check user value is here......

public void check_user(){
    String quest = question.getText().toString();
    String opt1 = option1.getText().toString();
    String opt2 = option2.getText().toString();
    String opt3 = option3.getText().toString();
    String opt4 = option4.getText().toString();
    String ans = right_answer.getText().toString();

    if(quest.isEmpty()||opt1.isEmpty()||opt2.isEmpty()||opt3.isEmpty()||opt4.isEmpty()||ans.isEmpty()){
        Toast.makeText(organsier_home_page.this,"Please Fill All Options",Toast.LENGTH_LONG).show();
        return;
    }

    question_class qs = new question_class(quest,opt1,opt2,opt3,opt4,ans);

    database.push().setValue(qs).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {

            Toast.makeText(organsier_home_page.this,"Question added successfully",Toast.LENGTH_SHORT).show();
            question.setText("");
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");

            right_answer.setText("");

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {

            Toast.makeText(organsier_home_page.this,e.toString(),Toast.LENGTH_SHORT).show();

        }
    });

}




    // find id functions is here................
    public void find_id(){

        question = findViewById(R.id.question_fill);
        option1= findViewById(R.id.question_a);
        option2 =findViewById(R.id.question_b);
        option3 = findViewById(R.id.question_c);
        option4= findViewById(R.id.question_d);

        right_answer = findViewById(R.id.answer);
        submit = findViewById(R.id.add_button);
        round1 = findViewById(R.id.round1);

    }


}

