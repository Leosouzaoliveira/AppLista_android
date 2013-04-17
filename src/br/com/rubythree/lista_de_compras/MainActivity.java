package br.com.rubythree.lista_de_compras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	Button comprasButton = (Button) findViewById(R.id.fazer_compras);
    	Button listaButton = (Button) findViewById(R.id.lista_compras);
    	
        
        comprasButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(MainActivity.this, FazerCompras.class);
				startActivity(in);
			
				
			}
		});
        
        listaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(MainActivity.this, ListaCompras.class);
				startActivity(in);
			
				
			}
		});
        
        
        
    }
}
