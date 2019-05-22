package com.parallaxsolutions.aula1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parallaxsolutions.aula1.models.User;
import com.parallaxsolutions.aula1.persistence.UserDB;

import java.util.ArrayList;

public class UserDAO {
    private SQLiteDatabase db;
    private UserDB banco;

    public UserDAO(Context context) {
        banco = new UserDB(context);
    }

    public String insereDado(User user) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", user.nome);
        valores.put("email", user.email);
        valores.put("senha", user.senha);
        valores.put("diaCadastro", user.diaCadastro);

        resultado = db.insert(UserDB.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public ArrayList<User> list() {
        Cursor cursor;
        String[] campos = {"nome", "email", "diaCadastro"};
        db = banco.getReadableDatabase();
        cursor = db.query("user", campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < cursor.getCount(); i++) {
            User user = new User();
            user.nome = cursor.getString(cursor.getColumnIndex("nome"));
            user.email = cursor.getString(cursor.getColumnIndex("email"));
            user.diaCadastro = cursor.getString(cursor.getColumnIndex("diaCadastro"));
            users.add(user);
            cursor.moveToNext();
        }
        return users;
    }
}
