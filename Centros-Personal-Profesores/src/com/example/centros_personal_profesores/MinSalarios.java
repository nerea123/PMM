package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MinSalarios extends Activity {

	ListView min;
	SQLiteDatabase db;
	String datosmin[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_min_salarios);
		
		min=(ListView)findViewById(R.id.listamin);
		ClientesSQLiteHelper sqlite=new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
		db=sqlite.getReadableDatabase();
		getMinSalario();
		
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datosmin);
		min.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.min_salarios, menu);
		return true;
	}
	
	private void getMinSalario(){
//		Cursor cur=db.query( "personal", new String[]{"MIN(salario)"}, null, null, null, null,null);
//		cur.moveToFirst();
//		Float salario=cur.getFloat(0);
//		String[] args=new String[]{String.valueOf(salario)};
//		String[] campos=new String[]{"apellidos","salario"};
		Cursor c =db.rawQuery("select apellidos, salario from personal where salario in (select MIN(salario) from personal)", null);
		datosmin=new String[c.getCount()];
		int i=0;
		if(c.moveToFirst()){
			do{
				datosmin[i]=c.getString(0)+" sueldo "+ String.valueOf(c.getFloat(1));
				i++;
			}while(c.moveToNext());
		}
	}

}
