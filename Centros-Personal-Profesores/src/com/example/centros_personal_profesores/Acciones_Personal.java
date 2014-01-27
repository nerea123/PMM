package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Acciones_Personal extends Activity {

	String ape;
	String dni;
	ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
	SQLiteDatabase db;
	Bundle bun;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acciones__personal);
		final ListView opciones=(ListView)findViewById(R.id.listaPersonal);
		bun=getIntent().getExtras();
		ape=bun.getString("ape");
		dni=bun.getString("dni");
		final String[] datos =new String[]{"Insertar nuevo personal","Modificar personal","Eliminar personal"};
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
		opciones.setAdapter(adaptador);
		opciones.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				switch (arg2){
				
				case 0:
					Intent i = new Intent(Acciones_Personal.this,InsertarPersonal.class);
					startActivity(i);
					break;
				case 1:
					bun=getIntent().getExtras();
					Intent in = new Intent(Acciones_Personal.this,EditProfesores.class);
					in.putExtras(bun);
					startActivity(in);
					break;
				case 2:
					creaDialogo();
					break;
				
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acciones__personal, menu);
		return true;
	}
	private void creaDialogo(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		 
		dialog.setMessage("¿Eliminar personalr "+ape+" con dni "+dni+" ?");
		dialog.setCancelable(false);
		dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
		 
		  @Override
		  public void onClick(DialogInterface dialog, int which) {
		   
			  db=cliBDh.getWritableDatabase();
			  db.execSQL("DELETE FROM personal WHERE dni="+dni);
			  db.close();
			  muestraToast();
			  Intent i = new Intent(Acciones_Personal.this,MainActivity.class);
			  startActivity(i);
		  }
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
		 
		   @Override
		   public void onClick(DialogInterface dialog, int which) {
		      dialog.cancel();
		   }
		});
		dialog.show();
	}

	private void muestraToast(){
		Toast.makeText(this, "Personal "+ape+" eliminado", Toast.LENGTH_SHORT).show();;
		
	}

}
