package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MaxSalarios extends Activity {

	ListView max;
	SQLiteDatabase db;
	String datosmax[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_max_salarios);
		
		max=(ListView)findViewById(R.id.listamax);
		ClientesSQLiteHelper sqlite=new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
		db=sqlite.getReadableDatabase();
		getMaxSalario();
		
		
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datosmax);
		max.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.max_salarios, menu);
		return true;
	}

	private void getMaxSalario(){
		Cursor cur=db.query( "personal", new String[]{"MAX(salario)"}, null, null, null, null,null);
		cur.moveToFirst();
		Float salario=cur.getFloat(0);
		String[] args=new String[]{String.valueOf(salario)};
		String[] campos=new String[]{"apellidos","salario"};
		Cursor c =db.query("personal",campos,"salario=?",args,null,null,null);
		datosmax=new String[c.getCount()];
		int i=0;
		if(c.moveToFirst()){
			do{
				datosmax[i]=c.getString(0)+" sueldo "+ String.valueOf(c.getFloat(1));
				i++;
			}while(c.moveToNext());
		}
	}
}
