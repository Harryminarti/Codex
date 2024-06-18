package com.example.codex;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Round1_Score extends AppCompatActivity {

    DatabaseReference database;
    RecyclerView recyclerView;

    List<score> items;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_round1_score);

        database = FirebaseDatabase.getInstance().getReference().child("score");

        items=new ArrayList<>();
        fetch_data();

//         recyclerView= findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        myAdapter= new MyAdapter(getApplicationContext(),items);
//        recyclerView.setAdapter(myAdapter);


    }

    public void fetch_data(){
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//        Query query = usersRef.orderByChild("age"); // Order by the "age" field
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Handle retrieved data here
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                    // Access individual user data
//                    String name = userSnapshot.child("name").getValue(String.class);
//                    int age = userSnapshot.child("age").getValue(Integer.class);
//                    // Process the data as needed
//                    System.out.println("Name: " + name + ", Age: " + age);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });



        database.orderByChild("score_no").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap:snapshot.getChildren()){

                    score qs = snap.getValue(score.class);
                    items.add(qs);
                }

                recyclerView= findViewById(R.id.recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(Round1_Score.this));
                myAdapter= new MyAdapter(getApplicationContext(),items);
                recyclerView.setAdapter(myAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Round1_Score.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}