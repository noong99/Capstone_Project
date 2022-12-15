package com.example.BugWiki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Bug> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Bug> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.iv_image);
         //-> 서버로부터 이미지 받아와서 삽입됨

        holder.tv_name.setText(arrayList.get(position).getName()); // arraylist 여러개..
        holder.tv_breed.setText(String.valueOf(arrayList.get(position).getBreed()));
        holder.tv_habit.setText(arrayList.get(position).getHabit());
        holder.tv_harmfulness.setText(String.valueOf(arrayList.get(position).getHarmfulness()));
        holder.tv_disease.setText(arrayList.get(position).getDisease());
        holder.tv_eradication.setText(arrayList.get(position).getEradication());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tv_name;
        TextView tv_breed;
        TextView tv_habit;
        TextView tv_harmfulness;
        TextView tv_disease;
        TextView tv_eradication;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_image = itemView.findViewById(R.id.iv_image);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_breed = itemView.findViewById(R.id.tv_breed);
            this.tv_habit = itemView.findViewById(R.id.tv_habit);
            this.tv_harmfulness = itemView.findViewById(R.id.tv_harmfulness);
            this.tv_disease = itemView.findViewById(R.id.tv_disease);
            this.tv_eradication = itemView.findViewById(R.id.tv_eradication);
        }
    }
}
