package com.parallaxsolutions.aula1.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "user";
    private static final int VERSAO = 1;

    public UserDB(Context context) {
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+" ("
                + "id integer primary key autoincrement,"
                + "nome text,"
                + "email text,"
                + "senha text,"
                + "diaCadastro text"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
