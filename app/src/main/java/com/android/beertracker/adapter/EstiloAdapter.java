package com.android.beertracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.beertracker.R;
import com.android.beertracker.model.Estilo;

import java.util.List;

public class EstiloAdapter extends RecyclerView.Adapter<EstiloViewHolder>{

    private List<Estilo> itemsList;

    public EstiloAdapter(List<Estilo> itemsList){
        this.itemsList = itemsList;
    }

    @Override
    public EstiloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_beerstyle, null);
        return new EstiloViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(EstiloViewHolder holder, int position) {
        Estilo estilo = itemsList.get(position);
        holder.styleTitle.setText(estilo.getNomeEstilo());
    }

    @Override
    public int getItemCount() {
        return this.itemsList.size();
    }

    public void updateEstilo(Estilo estilo) {
        itemsList. = items;
        notifyDataSetChanged();
    }
}
