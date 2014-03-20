package com.example.Databases;

import java.util.ArrayList;
import java.util.List;

import com.contas.Classes.Conta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContasOperations {

	 // Database fields
	 private DataBaseWrapper dbHelper;
	 private String[] CONTAS_TABLE_COLUMNS = { DataBaseWrapper.CONTAS_ID, DataBaseWrapper.CONTAS_DESCRICAO, DataBaseWrapper.CONTAS_VALOR, DataBaseWrapper.CONTAS_TIPO };
	 private SQLiteDatabase database;

	 public ContasOperations(Context context) {
	     dbHelper = new DataBaseWrapper(context);
	 }

	 public void open() throws SQLException {
	     database = dbHelper.getWritableDatabase();
	 }

	 public void close() {
	     dbHelper.close();
	 }

	 public Conta addConta(String descricao, String valor, String tipo) {
	     ContentValues values = new ContentValues();
	     values.put(DataBaseWrapper.CONTAS_DESCRICAO, descricao);
	     values.put(DataBaseWrapper.CONTAS_VALOR, valor);
	     values.put(DataBaseWrapper.CONTAS_TIPO, tipo);
	     long contaId = database.insert(DataBaseWrapper.CONTAS, null, values);

	     // now that the student is created return it ...
	     Cursor cursor = database.query(DataBaseWrapper.CONTAS,  CONTAS_TABLE_COLUMNS, DataBaseWrapper.CONTAS_ID + " = " 
	                     + contaId, null, null, null, null);

	     cursor.moveToFirst();

	     Conta newComment = parseStudent(cursor);
	     cursor.close();
	     return newComment;
	 }

	 public void deleteConta(Conta comment) {
	     long id = comment.getId();
	     System.out.println("Comment deleted with id: " + id);
	     database.delete(DataBaseWrapper.CONTAS, DataBaseWrapper.CONTAS_ID
	             + " = " + id, null);
	 }

	 public List<String> getAllConta() {
	     List contas = new ArrayList();

	     Cursor cursor = database.query(DataBaseWrapper.CONTAS,
	             CONTAS_TABLE_COLUMNS, null, null, null, null, null);
				 
	     cursor.moveToFirst();
	     while (!cursor.isAfterLast()) {
	         Conta conta = parseStudent(cursor);
	         contas.add(conta);
	         //Log.i("TESTE2", student.getdescricao());
	         cursor.moveToNext();
	     }
	     cursor.close();
	     return contas;
	 }

	 private Conta parseStudent(Cursor cursor) {
		 Conta conta = new Conta();
	     conta.setId((cursor.getInt(0)));
	     conta.setdescricao(cursor.getString(1));
	     conta.setValor(cursor.getString(1));
	     conta.setTipo(cursor.getString(1));
	     return conta;
	 }

}
