package com.android.beertracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beertracker.R;
import com.android.beertracker.entity.Style;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.StyleViewHolder> {

    private List<Style> itemsList;
    private onStyleClickListener onStyleClickListener;
    private Context context;

    public StyleAdapter(List<Style> itemsList, onStyleClickListener onStyleClickListener) {
        this.itemsList = itemsList;
        this.onStyleClickListener = onStyleClickListener;
    }

    @Override
    public StyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_beerstyle, null);
        return new StyleViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(StyleViewHolder holder, int position) {
        Style style = itemsList.get(position);
        holder.styleTitle.setText(style.getNomeEstilo());
        Picasso
            .with(context)
            .load(style.getImagem())
            .resize(250, 250)
            .error(R.drawable.ic_error_outline_black_36dp)
            .placeholder(R.drawable.progress_animation)
            .into(holder.styleImage);
    }

    @Override
    public int getItemCount() {
        return (itemsList == null) ? 0 : itemsList.size();
    }

    class StyleViewHolder extends RecyclerView.ViewHolder{

        ImageView styleImage;
        TextView styleTitle;

        public StyleViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStyleClickListener.onStyleSelected(itemsList.get(getLayoutPosition()));
                }
            });
            styleImage = itemView.findViewById(R.id.style_image);
            styleTitle = itemView.findViewById(R.id.style_title);
        }
    }

    public interface onStyleClickListener {
        void onStyleSelected(Style style);
    }

}
