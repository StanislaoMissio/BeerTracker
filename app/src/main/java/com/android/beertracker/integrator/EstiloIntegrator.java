package com.android.beertracker.integrator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.beertracker.activity.Response;
import com.android.beertracker.entity.Estilo;
import com.android.beertracker.infrastructure.Contants;
import com.android.beertracker.table.EstiloContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class EstiloIntegrator extends BaseIntegrator{

    public EstiloIntegrator(Context context){
        super(context);
    }

    public List<Estilo> loadAllEstilos(){
        final EstiloAPI api = RetrofitClient.getClient(Contants.BeerTrackerAPI.HOST).create(EstiloAPI.class);
        final Call<Response> request = api.getAllEstilos();
        try {
            final retrofit2.Response<Response> response = request.execute();
            return response.body().getDataEstilos();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Estilo> saveAllEstilos(List<Estilo> estilos) {
        openDatabase();
        try {
            if(estilos == null || estilos.isEmpty()){
                return null;
            }

            for (Estilo estilo : estilos){
                final ContentValues values = new ContentValues();
                values.put(EstiloContract.EstiloEntry.COLUMN_NAME, estilo.getNomeEstilo());
                values.put(EstiloContract.EstiloEntry.COLUMN_DESCRICAO, estilo.getDescricao());
                values.put(EstiloContract.EstiloEntry.COLUMN_PRECO, estilo.getPreco());
                values.put(EstiloContract.EstiloEntry.COLUMN_IMAGE, estilo.getImagem());

                final long ID = database.insert(EstiloContract.EstiloEntry.TABLE_NAME, null, values);
            }
        } finally {
            releaseDatabase();
        }
        return estilos;
    }

//    public List<Estilo> loadEstilosFromDatabase() {
//        openDatabase();
//        Cursor cursor = database.query(EstiloContract.EstiloEntry.TABLE_NAME, null, null, null, null, null, null);
//        cursor.moveToFirst();
//        final List<Estilo> places = new ArrayList<>();
//        while (!cursor.isAfterLast()) {
//            places.add(new Estilo(cursor));
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
