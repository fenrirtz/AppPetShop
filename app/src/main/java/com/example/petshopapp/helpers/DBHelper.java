package com.example.petshopapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "PetShopDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, senha TEXT)");
        db.execSQL("CREATE TABLE agendamentos(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, pet TEXT, servico TEXT, data TEXT, hora TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS agendamentos");
        onCreate(db);
    }

    public boolean register(String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if (c.getCount() > 0) return false;

        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("senha", senha);
        db.insert("users", null, cv);
        return true;
    }

    public boolean login(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE email = ? AND senha = ?", new String[]{email, senha});
        return c.getCount() > 0;
    }

    public void insertSchedule(String email, String cliente, String pet, String servico, String data, String hora) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("pet", pet);
        cv.put("servico", servico);
        cv.put("data", data);
        cv.put("hora", hora);
        db.insert("agendamentos", null, cv);
    }

    public Cursor getUserSchedules(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM agendamentos WHERE email = ?", new String[]{email});
    }
}
