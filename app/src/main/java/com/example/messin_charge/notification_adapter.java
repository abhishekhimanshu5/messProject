package com.example.messin_charge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class notification_adapter extends RecyclerView.Adapter<notification_adapter.myViewHolder> {

    ArrayList<notification_model> container;

    public notification_adapter(ArrayList<notification_model> container) {
        this.container = container;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return container.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{



        public myViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }
}
