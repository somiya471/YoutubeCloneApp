package com.example.youtubenofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MydbHandler db;
    RecyclerAdapter adapter;
    ArrayList<String> name,desc;
    ArrayList<Integer> img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MydbHandler(this);
        //db.insertuserdata("enna_sona","Enna Sona..from Ok Jaanu Movie",R.drawable.enna_sona);
        /*db.insertuserdata("hawayein","Hawayein..from Jab Harry Met Sejal",R.drawable.hawayein);
        db.insertuserdata("ishq_bulava","Ishq Bulava..from Hasee Toh Fasee",R.drawable.hasee);
        db.insertuserdata("jab_mila_tu","Jab Mila Tu..from I Hate Luv Story",R.drawable.jabmilatu);
        db.insertuserdata("pani_da","Pani Da Rang..from Vicky Donor",R.drawable.pani_da);*/

        recyclerView = findViewById(R.id.recyclerView);
        name = new ArrayList<>();
        desc = new ArrayList<>();
        img = new ArrayList<>();


        adapter = new RecyclerAdapter(this,name,desc,img);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

    }


    private void displayData() {
        Cursor c = db.getdata();
        if(c.getCount()==0){
            Toast.makeText(this,"No Entry exist",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            while (c.moveToNext()){
                name.add(c.getString(0));
                desc.add(c.getString(1));
                img.add(c.getInt(2));
            }
        }
    }

    }
