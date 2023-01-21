package com.example.messin_charge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class homeScreen extends AppCompatActivity {

    TextView dateText,dailyTotal,paid1,paid2,paid3,paid4,paid5,paid6,paid7,advanceTotal;
    TextView billbtn,viewDairy,addNew;
    RecyclerView notification_recview;
    ArrayList<notification_model> list;
    notification_adapter notification_adapter;
    LinearLayoutManager lm;
    SwipeRefreshLayout homeRefresher;
    int total=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);



        //<------------type casting------------------->
        dateText = findViewById(R.id.dateText);
        dailyTotal = findViewById(R.id.dailyTotal);
        paid1 = findViewById(R.id.paid1);
        paid2 = findViewById(R.id.paid2);
        paid3 = findViewById(R.id.paid3);
        paid4 = findViewById(R.id.paid4);
        paid5 = findViewById(R.id.paid5);
        paid6 = findViewById(R.id.paid6);
        paid7 = findViewById(R.id.paid7);
        billbtn = findViewById(R.id.bill);
        viewDairy = findViewById(R.id.viewDairy);
        addNew = findViewById(R.id.addNew);
        homeRefresher = findViewById(R.id.homeRefresher);

        notification_recview = findViewById(R.id.notification_recview);
        assert notification_recview != null;

        //<-------------setting of recycler view----------->

        list = new ArrayList<notification_model>();
        notification_adapter = new notification_adapter(list);
        lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        notification_recview.setLayoutManager(lm);
        notification_recview.setAdapter(notification_adapter);

        notification_recview.post(new Runnable() {
            @Override
            public void run() {
                // Call smooth scroll
                notification_recview.smoothScrollToPosition(notification_adapter.getItemCount() - 1);
            }
        });


        mainFunctionForThisPageHandling();

        homeRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                total=0;
                mainFunctionForThisPageHandling();
                homeRefresher.setRefreshing(false);
            }
        });




    }

    private void mainFunctionForThisPageHandling() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //<--------------- notification data filling ------------------>

        db.collection("month").document("May")
                .collection("notification").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        list.clear();
                        List<DocumentSnapshot> dc = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d:dc)
                        {
                            notification_model obj = d.toObject(notification_model.class);
                            list.add(obj);
                        }
                        notification_adapter.notifyDataSetChanged();
                    }
                });

        // <------------------------- total expense data filling ------------------------>

        // month name
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

        //collecting data.......................

        db.collection("month").document(month_name).collection("records")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot document:queryDocumentSnapshots)
                {
                    int i = Integer.valueOf(document.getString("price"));
                    total = total+i;
                }
                dailyTotal.setText("Total Diary Expense : ₹ "+String.valueOf(total)+"/-");
            }
        });

        //<----------------------- advance paid --------------------->

        //<------------------  Ramdeep-------------------->
        db.collection("month").document(month_name)
                .collection("advancepaid")
                .document("Ramdeep").collection("advance")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                int i = 0;
                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                {
                    i = Integer.valueOf(d.getString("amount"));


                }
                paid1.setText("Ramdeep : ₹ "+String.valueOf(i));

            }
        });

        //<------------------  Rakesh-------------------->
        db.collection("month").document(month_name)
                .collection("advancepaid")
                .document("Rakesh").collection("advance")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                int i = 0;
                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                {
                    i = Integer.valueOf(d.getString("amount"));


                }
                paid2.setText("Rakesh : ₹ "+String.valueOf(i));

            }
        });

        //<------------------  Nikesh-------------------->
        db.collection("month").document(month_name)
                .collection("advancepaid")
                .document("Nikesh").collection("advance")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                int i = 0;
                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                {
                    i = Integer.valueOf(d.getString("amount"));


                }
                paid3.setText("Nikesh : ₹ "+String.valueOf(i));

            }
        });


        //<------------------  Kunal-------------------->
        db.collection("month").document(month_name)
                .collection("advancepaid")
                .document("Kunal").collection("advance")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                int i = 0;
                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                {
                    i = Integer.valueOf(d.getString("amount"));


                }
                paid4.setText("Kunal : ₹ "+String.valueOf(i));

            }
        });

        //<------------------  Jagannath-------------------->
        db.collection("month").document(month_name)
                .collection("advancepaid")
                .document("Jagannath").collection("advance")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                int i = 0;
                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                {
                    i = Integer.valueOf(d.getString("amount"));


                }

                paid5.setText("Jagannath : ₹ "+String.valueOf(i));

            }
        });

        //<------------------  Himanshu-------------------->
        db.collection("month").document(month_name)
                .collection("advancepaid")
                .document("Himanshu").collection("advance")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                int i = 0;
                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                {
                    i = Integer.valueOf(d.getString("amount"));


                }

                paid6.setText("Himanshu : ₹ "+String.valueOf(i));

            }
        }); //<------------------  Anish -------------------->
        db.collection("month").document(month_name)
                .collection("advancepaid")
                .document("Anish").collection("advance")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                int i = 0;
                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                {
                    i = Integer.valueOf(d.getString("amount"));


                }

                paid7.setText("Anish : ₹ "+String.valueOf(i));

            }
        });



        //<----------------------- Button function ------------------------->

        billbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeScreen.this,eachPersonBill.class));
            }
        });

        viewDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeScreen.this,MainActivity.class));
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeScreen.this,newEntryScreen.class));
            }
        });



        //<----------------------------------- upper date text filling ------------------------------->
        // getting date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String Date = sdf.format(new Date());
        dateText.setText(month_name+"("+Date+")");

        //<-------------- total advance ---------------->
    }
}