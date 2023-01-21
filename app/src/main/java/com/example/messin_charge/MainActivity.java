package com.example.messin_charge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RadioGroup radiogrp;
    RadioButton radioButton,radio1,radio2,radio3;
    Switch switchbar;

    RecyclerView recyclerView;
    LinearLayoutManager lm;
    myAdapter myAdapter;
    ArrayList<model> datalist;
    TextView newentry;
    SwipeRefreshLayout swipeRefresh;
    String[] monthName = {"January","February","March","April","May","June","July","August","September","October","November","December"};
   // int total = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        newentry = findViewById(R.id.newentry);
        swipeRefresh = findViewById(R.id.swiperefresh);
        switchbar = findViewById(R.id.switchbar);

        recyclerView = findViewById(R.id.recycleview);

        lm = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);

        datalist = new ArrayList<model>();
        myAdapter = new myAdapter(datalist,MainActivity.this);

        recyclerView.setAdapter(myAdapter);

        radiogrp = findViewById(R.id.radiogrp);
        int r= radiogrp.getCheckedRadioButtonId();
        radioButton = findViewById(r);


        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);

        Format f = new SimpleDateFormat("M");
        String strMonth = f.format(new Date());

        int monthNum = Integer.valueOf(strMonth);

        radio2.setText(monthName[monthNum - 1]);
        if(monthNum <= 1){
            radio1.setText(monthName[11]);
        }else {
            radio1.setText(monthNum - 2);
        }

        if(monthNum == 12){
            radio3.setText(monthName[0]);
        }else{
            radio3.setText(monthName[monthNum]);
        }



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("image");



       //<------------------------------------------------------------------------>


        //code for new entry button
        //<---------------------------------------------------------------------->
        newentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,newEntryScreen.class));
            }
        });
        //<-------------------------------------------------------------------------->

        //Adding of data from fire store

        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (switchbar.isChecked())
                    datachange(findViewById(radiogrp.getCheckedRadioButtonId()),"name");
                else
                    datachangeDecending(findViewById(radiogrp.getCheckedRadioButtonId()),"timestamp");
            }
        });
        if (switchbar.isChecked())
            datachange(findViewById(radiogrp.getCheckedRadioButtonId()),"name");
        else
            datachangeDecending(findViewById(radiogrp.getCheckedRadioButtonId()),"timestamp");



        //<------------- refresh bar ---------------->
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (switchbar.isChecked())
                    datachange(findViewById(radiogrp.getCheckedRadioButtonId()),"name");
                else
                    datachangeDecending(findViewById(radiogrp.getCheckedRadioButtonId()),"timestamp");

                swipeRefresh.setRefreshing(false);
            }
        });

        // code for switch bar-------------------------

        switchbar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    datachange(findViewById(radiogrp.getCheckedRadioButtonId()),"name");
                else
                    datachangeDecending(findViewById(radiogrp.getCheckedRadioButtonId()),"timestamp");
            }
        });




    }// code for change in radio button
    //<--------------------------------------------------------------------->


    private void datachange(RadioButton rb,String name) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("month").document(rb.getText().toString())
                .collection("records").orderBy(name, Query.Direction.ASCENDING)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                datalist.clear();
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    model obj = d.toObject(model.class);
                    datalist.add(obj);
                }
                myAdapter.notifyDataSetChanged();

            }
        });


    }
    //<---------------------------------------------------------------------->
    private void datachangeDecending(RadioButton rb,String name) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("month").document(rb.getText().toString())
                .collection("records").orderBy(name, Query.Direction.DESCENDING)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                datalist.clear();
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    model obj = d.toObject(model.class);
                    datalist.add(obj);
                }
                myAdapter.notifyDataSetChanged();

            }
        });


    }

}