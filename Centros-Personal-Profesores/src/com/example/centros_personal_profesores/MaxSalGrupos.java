package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MaxSalGrupos extends Activity {

	ListView max;
	SQLiteDatabase db;
	String datosmax[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_max_sal_grupos);
		
		max=(ListView)findViewById(R.id.listmax);
		ClientesSQLiteHelper sqlite=new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
		db=sqlite.getReadableDatabase();
		getMaxSalario();
		
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datosmax);
		
		
		max.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.max_sal_grupos, menu);
		return true;
	}
	
	private void getMaxSalario(){
		String select="SELECT apellidos,salario,funcion from personal where salario in (select MAX(salario) from personal group by funcion) ";
		Cursor c =db.rawQuery(select,null);
		datosmax=new String[c.getCount()];
		System.out.println(c.getCount());
		int i=0;
		if(c.moveToFirst()){
			do{
				datosmax[i]=c.getString(0)+" "+c.getString(2)+"  sueldo "+ String.valueOf(c.getFloat(1));
				i++;
			}while(c.moveToNext());
		}
	}

}
