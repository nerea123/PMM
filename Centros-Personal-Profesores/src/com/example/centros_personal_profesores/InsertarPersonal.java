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

public class InsertarPersonal extends Activity {

	TextView dni;
	TextView ape;
	TextView salario;
	TextView funcion;
	Spinner spi;
	SQLiteDatabase db;
	String datos[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_personal);
		
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getWritableDatabase();
		dni=(EditText)findViewById(R.id.instDNIPer);
        ape=(EditText)findViewById(R.id.insApePer);
        salario=(EditText)findViewById(R.id.insSalarioPer);
        funcion=(EditText)findViewById(R.id.insFuncionPer);
        spi=(Spinner)findViewById(R.id.spicondPer);
        getCod();
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(adaptador);
        
        Button b=(Button)findViewById(R.id.binsPer);
        

        b.setOnClickListener(new OnClickListener() {
			
    			@Override
    			public void onClick(View arg0) {
    				
    				 isertar((String)spi.getSelectedItem().toString(),(String) dni.getText().toString()
    						 ,(String)ape.getText().toString()
    			        		,(String)funcion.getText().toString()
    			        		,(String)salario.getText().toString());
    				 Intent i=new Intent(InsertarPersonal.this,MainActivity.class);
    				 db.close();
    				 startActivity(i);
    			}
    		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_personal, menu);
		return true;
		
		
	}
	
	public void getCod(){
		 String[] campos = new String[] {"cod_centro"};

	        Cursor c = db.query("centros", campos, null, null, null, null, null);
	        datos=new String [c.getCount()];
	        int i=0;
	        if (c.moveToFirst()) {
	                
	                do {
	                        datos[i]=String.valueOf(c.getInt(0));
	                        	                        
	                        i++;
	                       
	                } while (c.moveToNext());
	                
	        }
	}
	
public void isertar(String cod,String dni,String ape,String funcion,String salario){
		
		
		db.execSQL("insert into personal values( "+Integer.parseInt(cod)+","+Integer.parseInt(dni)+", '"+ape+"', '"+funcion+"',"+Float.parseFloat(salario)+" )");
		
	}

}
