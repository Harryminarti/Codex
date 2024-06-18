package com.example.codex;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class round2_dashboard extends AppCompatActivity {
    CountDownTimer countDownTimer;
    Integer timervalue=20;

    TextView timer ;

    DatabaseReference database;

    public TextView question,ans1,ans2,ans3,ans4;
    Button next;

    static List<question_class> ll;
    question_class model_class;

    static  int i=0;
    static int size = 0;

    String answers_count="";

    DatabaseReference score_database;

    String answer;

    Integer score =0;

    int scores=0;

    FirebaseUser user;
    String uid;
    String team_name;

    DatabaseReference rd;

    LinearLayout linearLayout;
    Integer val;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_round2_dashboard);

        start_time();

        user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        uid = user.getUid();

        rd = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance().getReference().child("round2");
        score_database = FirebaseDatabase.getInstance().getReference().child("score2");



        find_id();

        ll=new ArrayList<>();

        get_data();

        FirebaseDatabase.getInstance().getReference().child("play").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.hasChild("round2")){
                    linearLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rd.child("user_disable2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChild(uid)){
                    linearLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers_count=ans1.getText().toString().trim();

                ans1.setBackgroundResource(R.color.light_green); // Set the background color
                ans2.setBackgroundResource(R.color.white);
                ans3.setBackgroundResource(R.color.white);
                ans4.setBackgroundResource(R.color.white);


            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers_count=ans2.getText().toString().trim();

                ans1.setBackgroundResource(R.color.light_green); // Set the background color
                ans2.setBackgroundResource(R.color.white);
                ans3.setBackgroundResource(R.color.white);
                ans4.setBackgroundResource(R.color.white);
            }
        });
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers_count=ans3.getText().toString().trim();

                ans1.setBackgroundResource(R.color.light_green); // Set the background color
                ans2.setBackgroundResource(R.color.white);
                ans3.setBackgroundResource(R.color.white);
                ans4.setBackgroundResource(R.color.white);
            }
        });

        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers_count=ans4.getText().toString().trim();

                ans1.setBackgroundResource(R.color.light_green); // Set the background color
                ans2.setBackgroundResource(R.color.white);
                ans3.setBackgroundResource(R.color.white);
                ans4.setBackgroundResource(R.color.white);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = ll.get(i).getRight();

                if(answer.equals(answers_count)||answer==answers_count){
                    scores++;
                }
                score=scores;
                team_name = user.getEmail();

                ans1.setBackgroundResource(R.color.white); // Set the background color
                ans2.setBackgroundResource(R.color.white);
                ans3.setBackgroundResource(R.color.white);
                ans4.setBackgroundResource(R.color.white);

                score score1 = new score(team_name, score.toString(),timer.getText().toString().trim());

                score_database.child(uid).setValue(score1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(round2_dashboard.this,"Score added",Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(round2_dashboard.this,"Your score is not added",Toast.LENGTH_LONG).show();

                    }
                });


                i++;

                if(i<size){
                    question.setText(ll.get(i).getQuestion());
                    ans1.setText(ll.get(i).getOption1());
                    ans2.setText(ll.get(i).getOption2());
                    ans3.setText(ll.get(i).getOption3());
                    ans4.setText(ll.get(i).getOption4());


                }else{

                    rd.child("user_disable2").child(uid).setValue(1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(round2_dashboard.this,"Your form is submitted successfully",Toast.LENGTH_LONG).show();
                        }
                    });

//                    question.setVisibility(View.GONE);
//                    ans1.setVisibility(View.GONE);
//                    ans2.setVisibility(view.GONE);
//                    ans3.setVisibility(view.GONE);
//                    ans4.setVisibility(view.GONE);
                    linearLayout.setVisibility(View.GONE);

                }


            }
        });






//
//
//        countDownTimer= new CountDownTimer(10000,1000) {
//            @Override
//            public void onTick(long l) {
//                timervalue=timervalue-1;
//                Integer t = timervalue;
////                timer.setText(t.toString());
//
//
//            }
//
//            @Override
//            public void onFinish() {
//
//
//            }
//        }.start();
//
//





    }


    public void start_time(){

        countDownTimer=new CountDownTimer(1200000,1000) {
            @Override
            public void onTick(long l) {

                long hour=(l/1000)/3600;
                long minutes=((l/1000)%3600)/60;
                long second =(l/1000)%60;

                String time= String.format(Locale.getDefault(),"%02d:%02d:%02d",hour,minutes,second);
                timer.setText(time);

            }

            @Override
            public void onFinish() {

            }
        }.start();


    }



    public void get_data(){

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snap:snapshot.getChildren()){

                    question_class qs = snap.getValue(question_class.class);
                    ll.add(qs);
                }
                size=ll.size();

                question.setText(ll.get(i).getQuestion());
                ans1.setText(ll.get(i).getOption1());
                ans2.setText(ll.get(i).getOption2());
                ans3.setText(ll.get(i).getOption3());
                ans4.setText(ll.get(i).getOption4());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void find_id(){
        question = findViewById(R.id.question);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4= findViewById(R.id.ans4);
        next = findViewById(R.id.next_button);
        linearLayout = findViewById(R.id.round1_form);
        timer = findViewById(R.id.timer);


    }
}