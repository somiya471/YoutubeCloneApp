package com.example.youtubenofragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList name_set,desc_set,imageid_set;

    public RecyclerAdapter(Context context, ArrayList name, ArrayList desc, ArrayList imageid) {
        this.context = context;
        this.name_set = name;
        this.desc_set = desc;
        this.imageid_set = imageid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource((Integer)imageid_set.get(position));
        holder.textView.setText(String.valueOf(desc_set.get(position)));
        holder.nametext.setText(String.valueOf(name_set.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,PlayActivity.class);
                i.putExtra("name",String.valueOf(name_set.get(position)));
                i.putExtra("desc",String.valueOf(desc_set.get(position)));
                i.putExtra("image",(Integer) imageid_set.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name_set.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView,nametext;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            nametext = itemView.findViewById(R.id.text_name);
        }
    }
}
