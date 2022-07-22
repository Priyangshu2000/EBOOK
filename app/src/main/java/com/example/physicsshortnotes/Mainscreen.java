package com.example.physicsshortnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Mainscreen extends AppCompatActivity {
RecyclerView scifi,horror,drama;
ArrayList<bookModel>sclist,horrolist,dramalist;
Bookadapter scadapter,horroradapter,dramaadapter;
DatabaseReference bookref;
ConstraintLayout layout;
ImageView movescifi,movehorror,movedrama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        scifi=findViewById(R.id.scifi_rv);
        horror=findViewById(R.id.horror_rv);
        drama=findViewById(R.id.drama_rv);
        layout=findViewById(R.id.layout);
        movescifi=findViewById(R.id.movescifi);
        movedrama=findViewById(R.id.movedrama);
        movehorror=findViewById(R.id.movehorror);
        bookref= FirebaseDatabase.getInstance().getReference();

        int color=getResources().getColor(R.color.color2);
        String s="For testing purpose we have added only one pdf for all books";
        Snackbar.make(layout,s, Snackbar.LENGTH_INDEFINITE).setTextColor(color).show(); // Don’t forget to show!

        sclist=new ArrayList<>();
        scadapter=new Bookadapter(sclist,Mainscreen.this);
        scifi.setAdapter(scadapter);
        LinearLayoutManager manager=new LinearLayoutManager(Mainscreen.this,LinearLayoutManager.HORIZONTAL,false);
        scifi.setHasFixedSize(true);
        scifi.setLayoutManager(manager);

        horrolist=new ArrayList<>();
        horroradapter=new Bookadapter(horrolist,Mainscreen.this);
        horror.setAdapter(horroradapter);
        LinearLayoutManager manager2=new LinearLayoutManager(Mainscreen.this,LinearLayoutManager.HORIZONTAL,false);
        horror.setHasFixedSize(true);
        horror.setLayoutManager(manager2);

        dramalist=new ArrayList<>();
        dramaadapter=new Bookadapter(dramalist,Mainscreen.this);
        drama.setAdapter(dramaadapter);
        LinearLayoutManager manager3=new LinearLayoutManager(Mainscreen.this,LinearLayoutManager.HORIZONTAL,false);
        drama.setHasFixedSize(true);
        drama.setLayoutManager(manager3);

        bookref.child("Sci-Fi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sclist.clear();
                for(DataSnapshot d:snapshot.getChildren()){
                    bookModel model=d.getValue(bookModel.class);
                    sclist.add(model);
                }
                scadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bookref.child("Horror").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                horrolist.clear();
                for(DataSnapshot d:snapshot.getChildren()){
                    bookModel model=d.getValue(bookModel.class);
                    horrolist.add(model);
                }
               horroradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        bookref.child("Drama").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dramalist.clear();
                for(DataSnapshot d:snapshot.getChildren()){
                    bookModel model=d.getValue(bookModel.class);
                    dramalist.add(model);
                }
                dramaadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        movescifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mainscreen.this,details.class);
                intent.putExtra("category","Sci-Fi");
                startActivity(intent);
            }
        });


        movehorror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mainscreen.this,details.class);
                intent.putExtra("category","Horror");
                startActivity(intent);
            }
        });


        movedrama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mainscreen.this,details.class);
                intent.putExtra("category","Drama");
                startActivity(intent);
            }
        });
    }
}
