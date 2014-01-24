package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AccionesCentro extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acciones_centro);
final ListView opciones=(ListView)findViewById(R.id.listaCentros);
		
		final String[] datos =
				new String[]{"Insertar nuevo centro","Modificar centro","Eliminar centro"};
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
		opciones.setAdapter(adaptador);
		
		opciones.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				switch (arg2){
				
				case 0:
					Intent i = new Intent(AccionesCentro.this,InsertarCentros.class);
					startActivity(i);
					break;
				case 1:
					Intent in = new Intent(AccionesCentro.this,EditCentros.class);
					Bundle b=getIntent().getExtras();
					in.putExtras(b);
					startActivity(in);
					break;
				case 2:
					break;
				
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acciones_centro, menu);
		return true;
	}

}
