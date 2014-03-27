package com.example.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {
	 public static final String CONTAS           = "Contas";
	 public static final String CONTAS_ID        = "_id";
	 public static final String CONTAS_DESCRICAO = "_descricao";
	 public static final String CONTAS_VALOR     = "_valor";
	 public static final String CONTAS_TIPO      = "_tipo";

	 private static final String DATABASE_NAME = "Contas.db";
	 private static final int DATABASE_VERSION = 1;

	 // creation SQLite statement
	 private static final String DATABASE_CREATE = "create table " + CONTAS + "(" + CONTAS_ID + " integer primary key autoincrement, "
	                                               + CONTAS_DESCRICAO + " text not null, " + CONTAS_VALOR + " real not null, " + CONTAS_TIPO + " text not null " + ");";

	 public DataBaseWrapper(Context context) {
	     super(context, DATABASE_NAME, null, DATABASE_VERSION);
	 }
	 
	 @Override
	 public void onCreate(SQLiteDatabase db) {
	     db.execSQL(DATABASE_CREATE);
	 }

	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	     // you should do some logging in here
	     // ..
	     db.execSQL("DROP TABLE IF EXISTS " + CONTAS);
	     onCreate(db);
	 }
}