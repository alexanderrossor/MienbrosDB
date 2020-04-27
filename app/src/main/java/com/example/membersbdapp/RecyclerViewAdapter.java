package com.example.membersbdapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    ArrayList<Miembros> listaMiembro;
    private AdapterView.OnItemClickListener mListener;

    public RecyclerViewAdapter(Context mContext, ArrayList<Miembros> listaMiembro) {
        this.mContext = mContext;
        this.listaMiembro = listaMiembro;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.ivFoto.setImageBitmap(listaMiembro.get(position).getImagen());
        holder.tvNombre.setText(listaMiembro.get(position).getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount()
    {
        return listaMiembro.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView ivFoto;
        TextView tvNombre;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            cardView = itemView.findViewById(R.id.cardView1);
        }
    }
}
