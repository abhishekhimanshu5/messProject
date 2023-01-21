package com.example.messin_charge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class eachPersonBill extends AppCompatActivity {

    String[] users = {"Anish","Himanshu","Jagannath","Kunal","Nikesh","Rakesh","Ramdeep"};
    String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    AutoCompleteTextView dropbox1,dropboxmonth;
    String user,month;
    int total1=0,Grand_total1=0,Grand_total2=0,total2=0;
    ArrayAdapter<String> arrayAdapter1,arrayAdapter2;
    FirebaseFirestore db;
    TextView room_amount,masi_amount,mess_amount,other_amount,grand_total,paid_amount,payable_amount,apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_person_bill);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // casting
        dropbox1 = findViewById(R.id.dropbox1);
        dropboxmonth = findViewById(R.id.dropboxmonth);
        room_amount = findViewById(R.id.room_amount);
        masi_amount = findViewById(R.id.masi_amount);
        mess_amount = findViewById(R.id.mess_amount);
        other_amount = findViewById(R.id.other_amount);
        grand_total = findViewById(R.id.grand_total);
        paid_amount = findViewById(R.id.paid_amount);
        payable_amount = findViewById(R.id.payable_amount);
        apply = findViewById(R.id.apply);


        arrayAdapter1 = new ArrayAdapter<String>(this,R.layout.dropdownitem,users);
        arrayAdapter2 = new ArrayAdapter<String>(this,R.layout.dropdownitem,months);

        dropbox1.setAdapter(arrayAdapter1);
        dropboxmonth.setAdapter(arrayAdapter2);
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        //------------>dropbox item selection <----------------

        dropbox1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = parent.getItemAtPosition(position).toString();

            }
        });

        // for month selection --------------------------

        dropboxmonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                month = parent.getItemAtPosition(position).toString();


            }
        });


        Grand_total1=Grand_total2=0;


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // collecting data fro fire store data base .........................
                // for  rent and masi--------------------------------
                // <---------------------------------------------------------------->
                db.collection("roomrent").document(user).collection(user)
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        int rent=0,masi=0;
                        Grand_total1=0;
                        for (QueryDocumentSnapshot d : queryDocumentSnapshots)
                        {
                            rent = Integer.valueOf(d.getString("rent"));
                            masi = Integer.valueOf(d.getString("masi"));
                            Grand_total1=rent+masi;

                        }
                        masi_amount.setText(String.valueOf(masi));
                        room_amount.setText(String.valueOf(rent));
                        Grand_total1=Grand_total1+0;


                    }
                });

                //-------------  for other and mess --------------------//

                db.collection("month")
                        .document(month).collection("MessAndOther")
                        .document(user).collection(user).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                int mess=0,other=0;
                                Grand_total2=0;
                                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                                {
                                    mess = Integer.valueOf(d.getString("mess"));
                                    other = Integer.valueOf(d.getString("other"));
                                    Grand_total2=mess+other;
                                }
                                mess_amount.setText(String.valueOf(mess));
                                other_amount.setText(String.valueOf(other));
                                grand_total.setText("Grand Total = ₹"+String.valueOf(Grand_total2+Grand_total1)+"/-");
                            }
                 });

                // setting  value for paid ---------------------------

                db.collection("month")
                        .document(month).collection("records")
                        .whereEqualTo("name",user)
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        int dairypaid=0;
                        total1=0;
                        for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                        {
                            dairypaid = Integer.valueOf(d.getString("price"));
                            total1=total1+dairypaid;
                        }
                        int p=0;
                        //<---------- collecting advance paid ---------------->

                        db.collection("month").document(month)
                                .collection("advancepaid")
                                .document(user).collection("advance")
                                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                total2 = 0;
                                int advance = 0;
                                for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                                {
                                    advance = Integer.valueOf(d.getString("amount"));
                                    total2=total2+advance;
                                }

                            }
                        });

                        p=Grand_total1+Grand_total2-total1-total2;
                        paid_amount.setText("Paid = ₹"+String.valueOf(total1+total2)+"/-");
                        payable_amount.setText("Payable Amount = ₹"+String.valueOf(p)+"/-");

                    }
                });


            }
        });


    }
}

