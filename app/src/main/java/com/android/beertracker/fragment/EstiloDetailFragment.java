package com.android.beertracker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beertracker.R;
import com.android.beertracker.entity.Estilo;
import com.squareup.picasso.Picasso;

public class EstiloDetailFragment extends Fragment {

    private static final String SELECTED_ESTILO =  "selected_estilo";
    private Context context;

    public EstiloDetailFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details_page_style, container, false);
        Estilo estilo = loadEstilo();
        if(estilo != null){
            ImageView imageEstilo = rootView.findViewById(R.id.image_style_details);
            TextView titleEstilo = rootView.findViewById(R.id.title_style_details);
            TextView descricaoEstilo = rootView.findViewById(R.id.description_style_details);

            Picasso
                .with(context)
                .load(estilo.getImagem())
                .into(imageEstilo);
            titleEstilo.setText(estilo.getNomeEstilo());
            descricaoEstilo.setText(estilo.getDescricao());
        }
        return rootView;
    }

    private Estilo loadEstilo(){
        if(getArguments() != null && getArguments().containsKey(SELECTED_ESTILO)){
            return getArguments().getParcelable(SELECTED_ESTILO);
        }
        else if(getActivity().getIntent().getExtras()
                != null && getActivity().getIntent().getExtras().containsKey(SELECTED_ESTILO)){
            return getActivity().getIntent().getExtras().getParcelable(SELECTED_ESTILO);
        }
        return null;
    }
}
