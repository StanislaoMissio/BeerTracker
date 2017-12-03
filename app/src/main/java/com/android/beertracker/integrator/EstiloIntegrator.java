package com.android.beertracker.integrator;

import android.content.ContentValues;
import android.content.Context;

import com.android.beertracker.activity.StyleResponse;
import com.android.beertracker.entity.Style;
import com.android.beertracker.infrastructure.Constants;
import com.android.beertracker.table.EstiloContract;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class EstiloIntegrator extends BaseIntegrator{

    public EstiloIntegrator(Context context){
        super(context);
    }

    public List<Style> loadAllEstilos(){
        final EstiloAPI api = RetrofitClient.getClient(Constants.BeerTrackerAPI.HOST).create(EstiloAPI.class);
        final Call<StyleResponse> request = api.getAllEstilos();
        try {
            final retrofit2.Response<StyleResponse> response = request.execute();
            return response.body().getData();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Style> saveAllEstilos(List<Style> styles) {
        openDatabase();
        try {
            if(styles == null || styles.isEmpty()){
                return null;
            }

            for (Style style : styles){
                final ContentValues values = new ContentValues();
                values.put(EstiloContract.EstiloEntry.COLUMN_NAME, style.getNomeEstilo());
                values.put(EstiloContract.EstiloEntry.COLUMN_DESCRICAO, style.getDescricao());
                values.put(EstiloContract.EstiloEntry.COLUMN_PRECO, style.getPreco());
                values.put(EstiloContract.EstiloEntry.COLUMN_IMAGE, style.getImagem());

                final long ID = database.insert(EstiloContract.EstiloEntry.TABLE_NAME, null, values);
            }
        } finally {
            releaseDatabase();
        }
        return styles;
    }

//    public List<Style> loadEstilosFromDatabase() {
//        openDatabase();
//        Cursor cursor = database.query(EstiloContract.EstiloEntry.TABLE_NAME, null, null, null, null, null, null);
//        cursor.moveToFirst();
//        final List<Style> places = new ArrayList<>();
//        while (!cursor.isAfterLast()) {
//            places.add(new Style(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        releaseDatabase();
//        return places;
//    }
//
//    public void cleanTable(){
//        openDatabase();
//        this.database.delete(EstiloContract.EstiloEntry.TABLE_NAME, null, null);
//        releaseDatabase();
//    }
}
