package com.android.beertracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beertracker.R;
import com.android.beertracker.entity.Harmony;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HarmonyAdapter extends RecyclerView.Adapter<HarmonyAdapter.HarmonyViewHolder> {

    private List<Harmony> itemList;
    private Context context;

    public HarmonyAdapter(List<Harmony> itemList){
        this.itemList = itemList;
    }

    @Override
    public HarmonyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_harmony_list, null);
        return new HarmonyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(HarmonyViewHolder holder, int position) {
        Harmony harmony = itemList.get(position);
        holder.nameHarmony.setText(harmony.getNomeHarmonia());
        holder.descriptionHarmony.setText(harmony.getDescricao());
        Picasso.with(context)
                .load(harmony.getImagem())
                .into(holder.imageHarmony);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HarmonyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageHarmony;
        private TextView nameHarmony;
        private TextView descriptionHarmony;

        public HarmonyViewHolder(View itemView) {
            super(itemView);

            imageHarmony = itemView.findViewById(R.id.harmony_type);
            nameHarmony = itemView.findViewById(R.id.harmony_name);
            descriptionHarmony = itemView.findViewById(R.id.harmony_description);
        }

    }
}
