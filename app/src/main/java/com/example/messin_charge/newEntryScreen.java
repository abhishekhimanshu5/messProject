package com.example.messin_charge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class newEntryScreen extends AppCompatActivity {

    AutoCompleteTextView dropbox;
    TextView addtodiary;
    String[] users = {"Anish","Himanshu","Jagannath","Kunal","Nikesh","Rakesh","Ramdeep","Mess","Mess(Due)"};
    ArrayAdapter<String> arrayAdapter;
    String user;
    EditText password,itemname,price;
    ProgressBar progressBar;
    boolean check=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        password =(EditText) findViewById(R.id.password);
        itemname = (EditText) findViewById(R.id.input_item);
        price = findViewById(R.id.input_price);
        addtodiary = findViewById(R.id.addtodiary);

        //conversion --------> edittext to string <---------------

        //--------------->data fitting in dropbox <-----------
        dropbox = findViewById(R.id.dropbox);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.dropdownitem,users);

        dropbox.setAdapter(arrayAdapter);


        //------------>dropbox item selection <----------------

        dropbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = parent.getItemAtPosition(position).toString();

            }
        });
        //<------------------------------------------------->

        //<------------------------------------------------->
        //checking for password and data provided

        addtodiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              finalCheck(user);
            }
        });

    }
    //<---------------------- end of main function -------------------------->

    //------------------> final check function <-------------------

    private void finalCheck(String user)
    {
       if (user.equals("Anish"))
       {
           if (password.getText().toString().equals("2024"))
           {
               check=dataFieldCheck();
               if (check)
               {
                   String u;
                   u="https://firebasestorage.googleapis.com/v0/b/mess-5581e.appspot.com/o/image%2FAnish.jpeg?alt=media&token=56e5ecf7-33e8-4d58-8cf6-5435aceaaaed";
                   dataEntryFunction(u);

               }
               else
                   Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
           }
           else
               Toast.makeText(this, "wrong password", Toast.LENGTH_SHORT).show();
       }
        else if (user.equals("Himanshu"))
        {
            if (password.getText().toString().equals("3064"))
            {
                check=dataFieldCheck();
                if (check)
                {
                    String u;
                    u="https://firebasestorage.googleapis.com/v0/b/mess-5581e.appspot.com/o/image%2FHimanshu.jpeg?alt=media&token=996f46b7-9ac4-4d7e-93b0-b7a7501e2f17";
                    dataEntryFunction(u);

                }
                else
                    Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
        }
        else if (user.equals("Jagannath"))
        {
            if (password.getText().toString().equals("3036"))
            {
                check=dataFieldCheck();
                if (check)
                {
                    String u;
                    u="https://firebasestorage.googleapis.com/v0/b/mess-5581e.appspot.com/o/image%2FJagannath.jpeg?alt=media&token=f4270a2c-ed8b-4e5d-b211-0d5a3c3d7b8c";
                    dataEntryFunction(u);

                }
                else
                    Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
        }
       else if (user.equals("Kunal"))
       {
           if (password.getText().toString().equals("2022"))
           {
               check=dataFieldCheck();
               if (check)
               {
                   String u;
                   u="https://firebasestorage.googleapis.com/v0/b/mess-5581e.appspot.com/o/image%2FKunal%2Fkunal.jpg?alt=media&token=8104be4b-1b67-42f1-b75e-c91ed76c0157";
                   dataEntryFunction(u);

               }
               else
                   Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
           } else
               Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
       }
       else if (user.equals("Nikesh"))
       {
           if (password.getText().toString().equals("2021"))
           {
               check=dataFieldCheck();
               if (check)
               {
                   String u;
                   u="https://firebasestorage.googleapis.com/v0/b/mess-5581e.appspot.com/o/image%2FNikesh.jpeg?alt=media&token=d628bb82-223c-4696-9b42-b008649c0f2e";
                   dataEntryFunction(u);

               }
               else
                   Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
           } else
               Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
       }
       else if (user.equals("Rakesh"))
       {
           if (password.getText().toString().equals("2202")) {
               check=dataFieldCheck();
               if (check)
               {
                   String u;
                   u="https://firebasestorage.googleapis.com/v0/b/mess-5581e.appspot.com/o/image%2FRakesh.jpeg?alt=media&token=e92c00d1-ca50-4f23-b2b9-bdc079a57987";
                   dataEntryFunction(u);

               }
               else
                   Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
           } else
               Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
       }
       else if (user.equals("Ramdeep"))
       {
           if (password.getText().toString().equals("2404")) {
               check=dataFieldCheck();
               if (check)
               {
                   String u;
                   u="https://firebasestorage.googleapis.com/v0/b/mess-5581e.appspot.com/o/image%2FRamdeep.jpeg?alt=media&token=cfdacf1a-50d1-4aa9-9302-f0ba2993b4cb";
                   dataEntryFunction(u);

               }
               else
                   Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
           } else
               Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
       }
       else if (user.equals("Mess") || user.equals("Mess(Due)"))
       {
           if (password.getText().toString().equals("0000")) {
               check=dataFieldCheck();
               if (check)
               {
                   String u;
                   u="https://akm-img-a-in.tosshub.com/indiatoday/images/story/201708/dish-story_647_081417052301.jpg?size=770:433";
                   dataEntryFunction(u);

               }
               else
                   Toast.makeText(this, "Data field can't be empty", Toast.LENGTH_SHORT).show();
           } else
               Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
       }


    }
    //----------------> data field checking <------------------

    private boolean dataFieldCheck(){
        if (itemname.getText().toString().equals("") || price.getText().toString().equals(""))
        {
            return false;
        }
        else
            return true;


        }

        private void dataEntryFunction(String u)
        {
            //<----------- initializing progress bar ------------>

            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // getting month name
            Calendar cal=Calendar.getInstance();
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
            String month_name = month_date.format(cal.getTime());


            // getting date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date());


            Map<String,Object> obj = new HashMap<>();
            obj.put("item",itemname.getText().toString().toUpperCase().trim());
            obj.put("price",price.getText().toString().toUpperCase().trim());
            obj.put("name",user);
            obj.put("date",date);
            obj.put("url",u);
            obj.put("timestamp", FieldValue.serverTimestamp());

            db.collection("month")
                    .document(month_name)
                    .collection("records")
                    .add(obj).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {

                    progressBar.setVisibility(View.INVISIBLE);

                    itemname.setText("");
                    password.setText("");
                    price.setText("");

                    Toast.makeText(newEntryScreen.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
        });
    }
}