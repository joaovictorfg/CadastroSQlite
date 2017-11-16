package com.example.joovictorfirmino.cadastrosqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.joovictorfirmino.cadastrosqlite.modelo.User;

import java.util.ArrayList;

/**
 * Created by João Victor Firmino on 15/11/2017.
 */

public class UserDAO extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "DBUser.db";
    private static final int VERSION = 1;
    private static final String TABELA = "user";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";
    private static final String EMAIL = "email";
    private static final String CPF = "cpf";


    public UserDAO(Context context) {
        super(context, NOME_BANCO, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ( " +
                " " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " " + NOME + " TEXT, " + TELEFONE + " TEXT, " +
                " " + EMAIL + " TEXT, " + CPF + " NUMBER );";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }
    public long salvarUser(User u){
        ContentValues values = new ContentValues();
        long retornaDB;
        values.put(NOME, u.getNome());
        values.put(TELEFONE, u.getTelefone());
        values.put(EMAIL, u.getEmail());
        values.put(CPF, u.getCpf());

        retornaDB = getWritableDatabase().insert(TABELA, null, values);

        return retornaDB;
    }

    public long alterarUser(User u){
        ContentValues values = new ContentValues();
        long retornaDB;
        values.put(NOME, u.getNome());
        values.put(TELEFONE, u.getTelefone());
        values.put(EMAIL, u.getEmail());
        values.put(CPF, u.getCpf());

        String[] args = {String.valueOf(u.getId())};

        retornaDB = getWritableDatabase().update(TABELA, values, "id=?", args);

        return retornaDB;
    }

    public long deletarUser(User u){
        long retornaDB;
        String[] args = {String.valueOf(u.getId())};
        retornaDB = getWritableDatabase().delete(TABELA, "id=?", args);

        return retornaDB;
    }

    public ArrayList<User> selectAllUser(){
        String[] colunas = {ID, NOME, TELEFONE, EMAIL, CPF};
        Cursor cursor = getWritableDatabase().query(TABELA, colunas, null, null, null, null, "upper (nome) ASC", null);
        ArrayList<User> listUser = new ArrayList<User>();
        while (cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setNome(cursor.getString(1));
            user.setTelefone(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setCpf(cursor.getString(4));

            listUser.add(user);
        }
        return listUser;
    }
}
