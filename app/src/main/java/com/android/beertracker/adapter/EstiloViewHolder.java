package com.android.beertracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beertracker.R;

class EstiloViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView styleImage;
    public TextView  styleTitle;

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
