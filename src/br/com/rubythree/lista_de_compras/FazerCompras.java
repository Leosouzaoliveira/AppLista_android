package br.com.rubythree.lista_de_compras;

import static us.monoid.web.Resty.data;
import static us.monoid.web.Resty.form;

import java.io.IOException;

import us.monoid.web.Resty;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FazerCompras extends Activity{
	
	final Resty r = new Resty();

     	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.compra);
	    
	       
	        Button save = (Button) findViewById(R.id.save);
	        final EditText itemEditText = (EditText) findViewById(R.id.item_edit_text);
		    final EditText qtdEditText = (EditText) findViewById(R.id.qtd_item_edit_text);
		     
	       
	        
	        save.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						 String item = itemEditText.getEditableText().toString();
					     String qtdItem = qtdEditText.getEditableText().toString();
					   	
						r.json("http://192.168.0.11:3000/items",
								form(
								data("item[item]",item),
								data("item[quantidade_item]",qtdItem)));
						Intent in = new Intent(FazerCompras.this, MainActivity.class);
						startActivity(in);
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
		     }
			});
	        
	 }

}
