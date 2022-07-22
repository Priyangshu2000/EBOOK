package com.example.physicsshortnotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Bookadapter extends RecyclerView.Adapter<Bookadapter.viewholder> {
    ArrayList<bookModel>list;
    Context context;

    public Bookadapter(ArrayList<bookModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleimage, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        bookModel model=list.get(position);
        holder.bookName.setText(model.getName());
        holder.authname.setText(model.getAuthor());
        Picasso.get().load(model.getCoverpic()).into(holder.coverPic);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,pdfview.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView coverPic;
        TextView bookName,authname;
        CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            bookName=itemView.findViewById(R.id.Bookname);
            authname=itemView.findViewById(R.id.authorname);
            coverPic=itemView.findViewById(R.id.coverpic);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
