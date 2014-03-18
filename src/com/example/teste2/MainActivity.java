package com.example.teste2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	int request_Code = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
               
        Button btnDebito  = (Button) findViewById(R.id.btnDebito);
        Button btnCredito = (Button) findViewById(R.id.btnCredito);
    }    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View view) {
    	switch (view.getId()) {
    	case R.id.btnDebito:
    		startActivity(new Intent("com.example.teste2.DebitoActivity"));
            break;
        case R.id.btnCredito:            
        	startActivity(new Intent("com.example.teste2.CreditoActivity"));
            break;
    	}
        
    }
}