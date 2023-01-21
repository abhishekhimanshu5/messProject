package com.example.messin_charge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.viewholder> {

    ArrayList<model> data;
    private Object context;

    public myAdapter(ArrayList<model> data,Context context) {
        this.data = data;
        this.context=context;
    }

    //<----------------------------------- upper date text filling ------------------------------->
    // getting date
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String Date = sdf.format(new Date());

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {


       Glide.with((Activity) context)
               .load(data.get(position).getUrl())
               .into(holder.imageView);

        holder.name.setText("Name : "+data.get(position).getName());
        holder.date.setText("Date : "+data.get(position).getDate());
        holder.item.setText("Item : "+data.get(position).getItem());
        holder.price.setText("Price : "+data.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView name,date,item,price;
        LinearLayout linearLayout;
        CircleImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profile_image);
            linearLayout =  itemView.findViewById(R.id.row_layout);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            item = itemView.findViewById(R.id.item);
            price = itemView.findViewById(R.id.price);

        }
    }
}
