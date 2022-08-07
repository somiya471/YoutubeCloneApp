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

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList name_set,desc_set,imageid_set;

    public MyRecyclerAdapter(Context context, ArrayList name_set, ArrayList desc_set, ArrayList imageid_set) {
        this.context = context;
        this.name_set = name_set;
        this.desc_set = desc_set;
        this.imageid_set = imageid_set;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.inside_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.img_set.setImageResource((Integer)imageid_set.get(position));
        holder.name_insideset.setText(String.valueOf(name_set.get(position)));
        holder.desc_insideset.setText(String.valueOf(desc_set.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(context,PlayActivity.class);
                i2.putExtra("name",String.valueOf(name_set.get(position)));
                i2.putExtra("desc",String.valueOf(desc_set.get(position)));
                i2.putExtra("image",(Integer) imageid_set.get(position));
                context.startActivity(i2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name_set.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_insideset,desc_insideset;
        ImageView img_set;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_insideset = itemView.findViewById(R.id.name_inside);
            desc_insideset = itemView.findViewById(R.id.desc_inside);
            img_set = itemView.findViewById(R.id.img);
        }
    }
}
