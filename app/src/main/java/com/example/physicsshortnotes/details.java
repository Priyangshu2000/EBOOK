package com.example.physicsshortnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class details extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<bookModel>list;
Bookadapter adapter;
DatabaseReference book;
TextView cat;
String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recyclerView=findViewById(R.id.rv);
        book= FirebaseDatabase.getInstance().getReference();
        category=getIntent().getStringExtra("category");
        cat=findViewById(R.id.category);
        cat.setText(category);

        list=new ArrayList<>();
        adapter=new Bookadapter(list,details.this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager=new GridLayoutManager(details.this,3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        book.child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot d:snapshot.getChildren()){
                    bookModel model=d.getValue(bookModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}