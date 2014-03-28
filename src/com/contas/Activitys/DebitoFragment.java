package com.contas.Activitys;

import java.util.List;

import com.contas.Classes.Conta;
import com.contas.Classes.ContaAdapter;
import com.example.Databases.*;
import com.example.teste2.R;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.Telephony.Mms.Part;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DebitoFragment extends Fragment {
	private ContasOperations contasDBoperation;
	private ListView listView;
	private View rootView;
	private TextView txtTotal;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){		
		rootView = inflater.inflate(R.layout.debito_fragment, container, false);
		getActivity().setTitle("Lançamentos de Débito");

		listView          = (ListView) rootView.findViewById(R.id.gridList);
		txtTotal          = (TextView) rootView.findViewById(R.id.txtTotalDebito);
		contasDBoperation = new ContasOperations(rootView.getContext());
		contasDBoperation.open();

		List<Conta> values = contasDBoperation.getAllConta(" _tipo = '" + "DEBITO" + "'");
		
		ContaAdapter adapter = new ContaAdapter(rootView.getContext(), R.layout.single_list_item_conta, values);
		listView.setAdapter(adapter);
		txtTotal.setText(getTotalContas(values).toString());

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
		ContaAdapter adapter  = (ContaAdapter) listView.getAdapter();
		EditText edtDescricao = (EditText) rootView.findViewById(R.id.txtDescricao);
		EditText edtValor     = (EditText) rootView.findViewById(R.id.txtValor);
		txtTotal              = (TextView) rootView.findViewById(R.id.txtTotalDebito);
		
		Conta conta           = contasDBoperation.addConta(edtDescricao.getText().toString(), edtValor.getText().toString(), "DEBITO");
		adapter.add(conta);	 
	}
	
	public Double getTotalContas(List<Conta> obj){
		Double total = 0.0;
		
		for (Conta conta : obj) {
			total += conta.getvalor();
		}
		return total;
	}
	
	public void atualizaList(){
		
	}
}
