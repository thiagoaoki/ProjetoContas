package com.contas.Activitys;

import java.util.List;

import com.contas.Classes.Conta;
import com.example.Databases.ContasOperations;
import com.example.teste2.R;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class DebitoActivity extends ListActivity {
	private ContasOperations contasDBoperation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debitoactivity);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		contasDBoperation = new ContasOperations(this);
		contasDBoperation.open();

		List values = contasDBoperation.getAllConta(" _tipo = '" + "DEBITO" + "'");
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

		Button btnSaveDebito = (Button) findViewById(R.id.btnSaveDebito);
		btnSaveDebito.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addUser(v);
			}
		});
	}

	public void addUser(View view) {
		ArrayAdapter adapter  = (ArrayAdapter) getListAdapter();
		EditText edtDescricao = (EditText) findViewById(R.id.txtDescricao);
		EditText edtValor     = (EditText) findViewById(R.id.txtValor);
		Conta conta = contasDBoperation.addConta(edtDescricao.getText().toString(), edtValor.getText().toString(), "DEBITO");	
		adapter.add(conta);
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	    onBackPressed();
	    return true;
	}
}
