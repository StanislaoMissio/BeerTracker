package com.android.beertracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beertracker.R;
import com.android.beertracker.entity.Estilo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EstiloAdapter extends RecyclerView.Adapter<EstiloAdapter.EstiloViewHolder> {

    private List<Estilo> itemsList;
    private onEstiloClickListener onEstiloClickListener;
    Context context;

    public EstiloAdapter(List<Estilo> itemsList, onEstiloClickListener onEstiloClickListener) {
        this.itemsList = itemsList;
        this.onEstiloClickListener = onEstiloClickListener;
    }

    @Override
    public EstiloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_beerstyle, null);
        return new EstiloViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(EstiloViewHolder holder, int position) {
        Estilo estilo = itemsList.get(position);
        holder.styleTitle.setText(estilo.getNomeEstilo());
        Picasso
            .with(context)
            .load(estilo.getImagem())
            .resize(250, 250)
            .into(holder.styleImage);
    }

    @Override
    public int getItemCount() {
        return (itemsList == null) ? 0 : itemsList.size();
    }

    class EstiloViewHolder extends RecyclerView.ViewHolder{

        ImageView styleImage;
        TextView styleTitle;

        public EstiloViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEstiloClickListener.onEstiloSelected(itemsList.get(getLayoutPosition()));
                }
            });
            styleImage = itemView.findViewById(R.id.style_image);
            styleTitle = itemView.findViewById(R.id.style_title);
        }
    }

    public interface onEstiloClickListener{
        void onEstiloSelected(Estilo estilo);
    }

}
