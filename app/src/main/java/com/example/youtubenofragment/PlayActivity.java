package com.example.youtubenofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    TextView textView;
    VideoView videoView;
    MyRecyclerAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> name_inside,desc_inside;
    ArrayList<Integer> img;
    MydbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String desc = i.getStringExtra("desc");
        int imageid = i.getIntExtra("image",0);

        textView = findViewById(R.id.desctext);
        videoView = findViewById(R.id.videoView);
        db = new MydbHandler(this);

        textView.setText(desc);
        String apath = "android.resource://"+getPackageName()+"/raw/"+name;
        Uri videouri = Uri.parse(apath);

        videoView.setVideoURI(videouri);
        videoView.start();

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        recyclerView = findViewById(R.id.recyclerViewinside);
        name_inside = new ArrayList<>();
        desc_inside = new ArrayList<>();
        img = new ArrayList<>();


        adapter = new MyRecyclerAdapter(this,name_inside,desc_inside,img);
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
                name_inside.add(c.getString(0));
                desc_inside.add(c.getString(1));
                img.add(c.getInt(2));
            }
        }
    }
}