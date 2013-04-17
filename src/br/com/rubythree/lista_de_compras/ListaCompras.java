package br.com.rubythree.lista_de_compras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListaCompras extends Activity {
	static String n;
	static String q;
	static String response = makeRequest("http://192.168.0.11:3000/items.json");
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.lista);
	        
	        ListView list = (ListView) findViewById(R.id.list);
	        
	      //Criando a array list que vai receber o resultado do json 
	        final ArrayList<String> myList = new ArrayList<String>();
	        
	        ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	       
	        
	        try {
	        	JSONArray json = new JSONArray(response);	        	
	        	for(int i = 0; i < json.length(); i++){
	        		n = json.getJSONObject(i).getString("item");
	        		q = json.getJSONObject(i).getString("quantidade_item");
	             	myList.add(n);
	             	
	        	}
	            
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	      //Fazendo nossa ListView receber a arraylist
	    	ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this,
	        		android.R.layout.simple_list_item_1, myList);
	        list.setAdapter(myarrayAdapter); 
	      //Fazendo o arraylist receber a acao
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					JSONArray json;
					try {
						json = new JSONArray(response);
						n = json.getJSONObject(position).getString("item");
		        		q = json.getJSONObject(position).getString("quantidade_item");
		//Passando os conteudos para minha classe Details
						Intent intent = new Intent(ListaCompras.this, Details.class);
						intent.putExtra("item",n);
						intent.putExtra("quantidade_item",q);
						startActivity(intent);	             
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
					
				}
			});
	       
	        
	        
	 }

	private static String makeRequest(String urlAddress) {
			HttpURLConnection con = null;
			URL url = null;
			String response = null;
			try {
				url = new URL(urlAddress);
				con = (HttpURLConnection) url.openConnection();
				response = readStream(con.getInputStream());
				
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				con.disconnect();
			}
			return response;
	    }
	 private static String readStream(InputStream in) {
				BufferedReader reader = null;
				StringBuilder builder = new StringBuilder();
				
				try{
					reader = new BufferedReader(new InputStreamReader(in));
					String line = null;
					while ((line = reader.readLine()) != null) {
						builder.append(line + "\n");
					}
				}catch (IOException e){
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						}catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return builder.toString();
			}
}
