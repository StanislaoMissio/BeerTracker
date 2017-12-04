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
import com.android.beertracker.entity.Style;
import com.squareup.picasso.Picasso;

public class StyleDetailFragment extends Fragment {

    private static final String SELECTED_STYLE =  "selected_style";
    private Context context;

    public StyleDetailFragment(){}

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
        Style style = loadStyle();
        if(style != null){
            ImageView imageStyle = rootView.findViewById(R.id.image_style_details);
            TextView titleStyle = rootView.findViewById(R.id.title_style_details);
            TextView descriptionStyle = rootView.findViewById(R.id.description_style_details);

            Picasso
                .with(context)
                .load(style.getImagem())
                .into(imageStyle);
            titleStyle.setText(style.getNomeEstilo());
            descriptionStyle.setText(style.getDescricao());
        }
        return rootView;
    }

    private Style loadStyle(){
        if(getActivity().getIntent().getExtras()
                != null && getActivity().getIntent().getExtras().containsKey(SELECTED_STYLE)){
            return getActivity().getIntent().getExtras().getParcelable(SELECTED_STYLE);
        }
        return null;
    }
}
