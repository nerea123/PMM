package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Estadisticas extends Activity {

	
	Button min;
	Button grupos;
	Button max;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadisticas);
		max=(Button)findViewById(R.id.bmaxsal);
		min=(Button)findViewById(R.id.bminsal);
		grupos=(Button)findViewById(R.id.bagrupados);
		
		max.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Estadisticas.this,MaxSalarios.class);
				startActivity(i);
				
			}
		});
		
		min.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Estadisticas.this,MinSalarios.class);
				startActivity(i);
				
			}
		});
		
		grupos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Estadisticas.this,MaxSalGrupos.class);
				startActivity(i);
				
			}
		});
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estadisticas, menu);
		return true;
	}
	
	

}
