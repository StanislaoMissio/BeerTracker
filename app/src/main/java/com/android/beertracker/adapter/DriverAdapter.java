package com.android.beertracker.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beertracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno on 03/12/2017.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    private List<String> nomes;
    private Drawable res;

    public DriverAdapter (){
        this.nomes = new ArrayList<>();
    }

    public void addDriver(String nome){
        this.nomes.add(nome);
    }

    @Override
    public DriverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_drivers,null);
        return new DriverViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final DriverViewHolder holder, int position) {
        holder.nameDriver.setText(this.nomes.get(position));
        holder.delete.setImageDrawable(res);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeDriverAtPosition(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return nomes.size();
    }

    public String getDriverAtPosition(int position){
        return nomes.get(position);
    }

    private void removeDriverAtPosition(int position){
        nomes.remove(position);
        notifyItemRemoved(position);
    }

    class DriverViewHolder extends RecyclerView.ViewHolder {

        private TextView nameDriver;
        private ImageView delete;

        private DriverViewHolder(View itemView) {
            super(itemView);
            nameDriver = itemView.findViewById(R.id.name_driver);
            delete = itemView.findViewById(R.id.delete);
            res = itemView.getResources().getDrawable(R.drawable.delete);
        }
    }
}
