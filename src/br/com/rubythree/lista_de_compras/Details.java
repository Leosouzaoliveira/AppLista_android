package br.com.rubythree.lista_de_compras;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        
        TextView itemD = (TextView) findViewById(R.id.datails_item);
        TextView qtdD = (TextView) findViewById(R.id.qtd_datails_item);
        
        Bundle extra = getIntent().getExtras();
        String item = extra.getString("item");
        String qtdItem = extra.getString("quantidade_item");
        
        itemD.setText(getString(R.string.item_select,item));
        qtdD.setText(getString(R.string.qtd_item_select, qtdItem));
	
	
	}

}
