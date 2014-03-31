package com.contas.Activitys;

import java.util.List;

import com.contas.Classes.Conta;
import com.contas.Classes.ContaAdapter;
import com.contas.Classes.SwipeDismissListViewTouchListener;
import com.example.Databases.ContasOperations;
import com.example.teste2.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CreditoFragment extends Fragment {
	private ContasOperations contasDBoperation;
	private ListView listView;
	private View rootView;
	private TextView txtTotal;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){		
		rootView = inflater.inflate(R.layout.credito_fragment, container, false);
		getActivity().setTitle("Lançamentos de Créditos");

		listView          = (ListView) rootView.findViewById(R.id.gridList);
		txtTotal          = (TextView) rootView.findViewById(R.id.txtTotalCreditos);
		contasDBoperation = new ContasOperations(rootView.getContext());
		contasDBoperation.open();

		List values = contasDBoperation.getAllConta(" _tipo = '" + "CREDITO" + "'");
 		
		ContaAdapter adapter = new ContaAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, values);

		SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                	removeUser(position); //adapter.remove(adapter.getItem(position));
                                }
                            }
                        });
		
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());
		
		listView.setAdapter(adapter);
		txtTotal.setText(getTotalContas(values).toString());

		Button btnSaveCredito = (Button) rootView.findViewById(R.id.btnSaveCredito);
		btnSaveCredito.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addUser(v);
			}
		});	  

		return rootView;
	}
	
	public CreditoFragment(){
		// 
	}
	
	public Double getTotalContas(List<Conta> obj){
		Double total = 0.0;
		
		for (Conta conta : obj) {
			total += conta.getvalor();
		}
		return total;
	}	
	
	public void addUser(View view) {
		ContaAdapter adapter  = (ContaAdapter) listView.getAdapter();
		EditText edtDescricao = (EditText) rootView.findViewById(R.id.txtDescricao);
		EditText edtValor     = (EditText) rootView.findViewById(R.id.txtValor);
		txtTotal              = (TextView) rootView.findViewById(R.id.txtTotalCreditos);
		Conta conta = contasDBoperation.addConta(edtDescricao.getText().toString(), edtValor.getText().toString(), "CREDITO");	
		adapter.add(conta);
		
		Double aux = Double.parseDouble(txtTotal.getText().toString());
		aux += conta.getvalor();
		txtTotal.setText(aux.toString());		
	}
	
	public void removeUser(Integer pos) {
		Double aux = Double.parseDouble(txtTotal.getText().toString());
		ContaAdapter adapter  = (ContaAdapter) listView.getAdapter();
		txtTotal              = (TextView) rootView.findViewById(R.id.txtTotalCreditos);
		Conta conta           = adapter.getItem(pos);		
		aux -= conta.getvalor();
		
		contasDBoperation.deleteConta(conta);
		adapter.remove(adapter.getItem(pos));
				
		txtTotal.setText(aux.toString());
		adapter.notifyDataSetChanged();
	}
}
