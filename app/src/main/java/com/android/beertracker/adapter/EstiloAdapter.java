package com.android.beertracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beertracker.R;
import com.android.beertracker.entity.Estilo;

import java.util.List;

public class EstiloAdapter extends RecyclerView.Adapter<EstiloAdapter.EstiloViewHolder> {

    private List<Estilo> itemsList;
    private onEstiloClickListener onEstiloClickListener;

    public EstiloAdapter(List<Estilo> itemsList, onEstiloClickListener onEstiloClickListener) {
        this.itemsList = itemsList;
        this.onEstiloClickListener = onEstiloClickListener;
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

    class EstiloViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView styleImage;
        TextView styleTitle;

        public EstiloViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            styleImage = itemView.findViewById(R.id.style_image);
            styleTitle = itemView.findViewById(R.id.style_title);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), getLayoutPosition(),Toast.LENGTH_LONG).show();
        }
    }

    public interface onEstiloClickListener{
        void onEstiloSelected(Estilo estilo);
    }

}
