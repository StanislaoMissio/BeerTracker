package com.android.beertracker.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beertracker.R;

/**
 * Created by Bruno on 03/12/2017.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    private TextView nameDriver;
    private ImageView delete;
    private String nome;
    private Drawable res;

    public DriverAdapter (String nome){this.nome = nome;}

    @Override
    public DriverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_drivers,null);
        return new DriverViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(DriverViewHolder holder, int position) {
        nameDriver.setText(nome);
        delete.setImageDrawable(res);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DriverViewHolder extends RecyclerView.ViewHolder {
        public DriverViewHolder(View itemView) {
            super(itemView);
            nameDriver = itemView.findViewById(R.id.name_driver);
            delete = itemView.findViewById(R.id.delete);
            res = itemView.getResources().getDrawable(R.drawable.delete);
        }
    }
}
