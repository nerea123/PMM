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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AccionesProfesores extends Activity {

	String ape;
	String dni;
	ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
	SQLiteDatabase db;
	Bundle bun;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acciones_profesores);
		final ListView opciones=(ListView)findViewById(R.id.listaProfesores);
		bun=getIntent().getExtras();
		ape=bun.getString("apeP");
		dni=bun.getString("dni");
		final String[] datos =
				new String[]{"Insertar nuevo profesor","Modificar profesor","Eliminar provesor"};
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
		opciones.setAdapter(adaptador);
		
		opciones.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				switch (arg2){
				
				case 0:
					Intent i = new Intent(AccionesProfesores.this,InsertaProfesores.class);
					startActivity(i);
					finish();
					break;
				case 1:
					
					Intent in = new Intent(AccionesProfesores.this,EditProfesores.class);
					in.putExtras(bun);
					startActivity(in);
					finish();
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
		getMenuInflater().inflate(R.menu.acciones_profesores, menu);
		return true;
	}
	
	private void creaDialogo(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		 
		dialog.setMessage("¿Eliminar profesor "+ape+" con dni "+dni+" ?");
		dialog.setCancelable(false);
		dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
		 
		  @Override
		  public void onClick(DialogInterface dialog, int which) {
		   
			  db=cliBDh.getWritableDatabase();
			  db.execSQL("DELETE FROM profesores WHERE dni="+dni);
			  db.close();
			  muestraToast();
			 
			  finish();
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
		Toast.makeText(this, "Profesor "+ape+" eliminado", Toast.LENGTH_SHORT).show();;
		
	}

}
