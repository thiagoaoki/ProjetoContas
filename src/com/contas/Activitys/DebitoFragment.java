package com.contas.Activitys;

import java.util.List;

import com.contas.Classes.Conta;
import com.example.Databases.*;
import com.example.teste2.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class DebitoFragment extends Fragment {
	private ContasOperations contasDBoperation;
	private GridView gridView;
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){		
		rootView = inflater.inflate(R.layout.debitoactivity, container, false);
		getActivity().setTitle("Lançamentos de Débito");

		gridView          = (GridView) rootView.findViewById(R.id.gridList);		
		contasDBoperation = new ContasOperations(rootView.getContext());
		contasDBoperation.open();

		List values = contasDBoperation.getAllConta(" _tipo = '" + "DEBITO" + "'");
		
		ArrayAdapter adapter = new ArrayAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, values);
		gridView.setAdapter(adapter);

		Button btnSaveDebito = (Button) rootView.findViewById(R.id.btnSaveDebito);
		btnSaveDebito.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addUser(v);
			}
		});
		
		return rootView;
	}	
	
	public DebitoFragment(){
		// 
	}

	public void addUser(View view) {
		ArrayAdapter adapter  = (ArrayAdapter) gridView.getAdapter();
		EditText edtDescricao = (EditText) rootView.findViewById(R.id.txtDescricao);
		EditText edtValor     = (EditText) rootView.findViewById(R.id.txtValor);
		Conta conta           = contasDBoperation.addConta(edtDescricao.getText().toString(), edtValor.getText().toString(), "DEBITO");
		adapter.add(conta.getdescricao());
		adapter.add(conta.getvalor());
	}
}
