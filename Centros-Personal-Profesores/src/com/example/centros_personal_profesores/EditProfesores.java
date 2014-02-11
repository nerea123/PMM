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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditProfesores extends Activity {

	Spinner spi;
    TextView ape;
    TextView espe;
    SQLiteDatabase db;
	SQLiteDatabase dbr;
	String datos[];
	Bundle bun;
	String centro;
	int centroSelec=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profesores);
		bun=getIntent().getExtras();
		
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getWritableDatabase();
        dbr = cliBDh.getReadableDatabase();
        
    	bun=getIntent().getExtras();
        getCod();
        spi=(Spinner)findViewById(R.id.spinEditProf);
        ape=(EditText)findViewById(R.id.editApeProf);
        espe=(EditText)findViewById(R.id.EditEspeProf);
        ponDatos();
        System.out.println(ape.getText());
        Button b=(Button)findViewById(R.id.bEditProf);
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(adaptador);
        System.out.println(centroSelec);
        spi.setSelection(centroSelec);
        
 b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				db.execSQL("UPDATE profesores SET cod_centro="+Integer.parseInt(spi.getSelectedItem().toString())+","
						
								+ "apellidos='"+ape.getText().toString()+"',"
										+ "especialidad='"+espe.getText().toString()+"'"
												+ "WHERE dni="+Integer.parseInt(bun.getString("dni"))+";");
				
				db.close();
				
				finish();
				
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profesores, menu);
		return true;
	}
	
	public void getCod(){
		 String[] campos = new String[] {"cod_centro"};

		 	String centro=bun.getString("codP");
	        Cursor c = db.query("centros", campos, null, null, null, null, null);
	        datos=new String [c.getCount()];
	        int i=0;
	        if (c.moveToFirst()) {
	                
	                do {
	                        datos[i]=String.valueOf(c.getInt(0));
	                        //si el cod que pasamos es igual al codigo que insertamos en el espiner guardamos su indice para inicializar el spiner con el
	                        if(datos[i].equalsIgnoreCase(centro))
	                        	centroSelec=i;
	                        
	                        i++;
	                       
	                } while (c.moveToNext());
	                
	        }
	}
		private void ponDatos(){
		ape.setText(bun.getString("apeP"));
		espe.setText(bun.getString("espeP"));
	}

}
