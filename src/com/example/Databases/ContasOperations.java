package com.example.Databases;

import java.util.ArrayList;
import java.util.List;

import com.example.teste2.Debito;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContasOperations {

	 // Database fields
	 private DataBaseWrapper dbHelper;
	 private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapper.CONTAS_ID, DataBaseWrapper.CONTAS_DESCRICAO };
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

	 public Debito addStudent(String name) {
	     ContentValues values = new ContentValues();
	     values.put(DataBaseWrapper.CONTAS_DESCRICAO, name);
	     values.put(DataBaseWrapper.CONTAS_VALOR, "1");
	     long studId = database.insert(DataBaseWrapper.CONTAS, null, values);

	     // now that the student is created return it ...
	     Cursor cursor = database.query(DataBaseWrapper.CONTAS,
	             STUDENT_TABLE_COLUMNS, DataBaseWrapper.CONTAS_ID + " = "
	                     + studId, null, null, null, null);

	     cursor.moveToFirst();

	     Debito newComment = parseStudent(cursor);
	     cursor.close();
	     return newComment;
	 }

	 public void deleteStudent(Debito comment) {
	     long id = comment.getId();
	     System.out.println("Comment deleted with id: " + id);
	     database.delete(DataBaseWrapper.CONTAS, DataBaseWrapper.CONTAS_ID
	             + " = " + id, null);
	 }

	 public List<String> getAllStudents() {
	     List students = new ArrayList();

	     Cursor cursor = database.query(DataBaseWrapper.CONTAS,
	             STUDENT_TABLE_COLUMNS, null, null, null, null, null);
				 
	     cursor.moveToFirst();
	     while (!cursor.isAfterLast()) {
	         Debito student = parseStudent(cursor);
	         students.add(student);
	         Log.i("TESTE2", student.getdescricao());
	         cursor.moveToNext();
	     }
	     cursor.close();
	     return students;
	 }

	 private Debito parseStudent(Cursor cursor) {
		 Debito student = new Debito();
	     student.setId((cursor.getInt(0)));
	     student.setdescricao(cursor.getString(1));
	     return student;
	 }

}
