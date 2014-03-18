package com.example.teste2;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class CreditoActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.creditoactivity);
	  
	  getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		onBackPressed();
		return true;
	}
}
