package com.example.unomesq.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unomesq.R;
import com.example.unomesq.model.Carta;

import java.util.ArrayList;
import java.util.List;

public class MaoAdapter extends RecyclerView.Adapter<MaoAdapter.MyViewHolder> {

    private List<Carta> mao = new ArrayList<Carta>();

    private Context context;

    public MaoAdapter(Context context, List<Carta> mao) {
        this.context = context;
        this.mao = mao;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageDrawable
                (AppCompatResources.getDrawable
                        (this.context, mao.get(position).getImagem()));
    }

    @Override
    public int getItemCount() {
        return mao.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivCartarv);
        }
    }
}