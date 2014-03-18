package com.example.teste2;

import java.util.List;

import com.example.Databases.ContasOperations;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class DebitoActivity extends Activity {
	private ContasOperations contasDBoperation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debitoactivity);
		// getActionBar().setDisplayHomeAsUpEnabled(true);

		contasDBoperation = new ContasOperations(this);
		contasDBoperation.open();

		List values = contasDBoperation.getAllStudents();
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, values);
		//setListAdapter(adapter);

		Button btnSaveDebito = (Button) findViewById(R.id.btnSaveDebito);
		btnSaveDebito.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addUser(v);
			}
		});
		
		Button btn = (Button) findViewById(R.id.btnList);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				List<String> values = contasDBoperation.getAllStudents();			
				Log.i("TESTE", values.toString());
			}
		});


	}

	public void addUser(View view) {
		//ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
		EditText text = (EditText) findViewById(R.id.txtDescricao);
		Debito conta = contasDBoperation.addStudent(text.getText().toString());	
		//adapter.add(conta);
	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	// onBackPressed();
	// return true;
	// }
}
