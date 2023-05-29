package com.example.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Usuarios(usuario varchar(100), senha varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table Usuarios");
    }

    public boolean novo_user(String usuario, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("usuario", usuario);
        contentValues.put("senha", senha);

        long resultado = db.insert("Usuarios", null, contentValues);

        if(resultado == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor get_dados(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Usuarios", null);

        return cursor;
    }
}
