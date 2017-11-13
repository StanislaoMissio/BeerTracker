package com.android.beertracker.table;

import android.provider.BaseColumns;

public class EstiloContract extends BaseContract {

    public static abstract class EstiloEntry implements BaseColumns{
        public static final String TABLE_NAME = "Estilo";
        public static final String COLUMN_NAME = "Nome";
        public static final String COLUMN_DESCRICAO = "Descricao";
        public static final String COLUMN_PRECO = "Preco";
        public static final String COLUMN_IMAGE = "Imagem";
        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
                "(" + _ID + INTEGER + PRIMARY_KEY + COMMA_SEPARATION +
                COLUMN_NAME + TEXT + COMMA_SEPARATION +
                COLUMN_DESCRICAO + TEXT + COMMA_SEPARATION +
                COLUMN_PRECO + TEXT + COMMA_SEPARATION +
                COLUMN_IMAGE + TEXT + ");";
    }
}
