package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
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

public class InsertaProfesores extends Activity {

	SQLiteDatabase db;
	SQLiteDatabase dbr;
	String datos[];
	Spinner spi;
    TextView ape;
    TextView espe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inserta_profesores);
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getWritableDatabase();
        dbr = cliBDh.getReadableDatabase();
        getCod();
        spi=(Spinner)findViewById(R.id.spinInsProf);
        ape=(EditText)findViewById(R.id.insApe);
        espe=(EditText)findViewById(R.id.insEspe);
        Button b=(Button)findViewById(R.id.bins);
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(adaptador);
       
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				 isertar((String) spi.getSelectedItem(),(String)ape.getText()
			        		,(String)espe.getText()
			        		,getDni((String)ape.getText()));
				
			}
		});
        
       
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inserta_profesores, menu);
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
	                        System.out.println(c.getInt(0));
	                } while (c.moveToNext());
	                db.close();
	        }
	}
	
	public String getDni(String ape){
		 String[] campos = new String[] {"dni"};
		 String[]args=new String[]{ape};
		 String dni = null;
	        Cursor c = db.query("personal", campos, "nombre=?", args, null, null, null);
	        
	        int i=0;
	        if (c.moveToFirst()) {
	                
	                do {
	                        dni=String.valueOf(c.getInt(0));
	                        
	                        System.out.println(c.getInt(0));
	                } while (c.moveToNext());
	                db.close();
	        }
	        return dni;
	}
	
	public void isertar(String ape, String espe, String cod,String dni){
		
		
		db.execSQL("insert into profesores values( "+Integer.parseInt(cod)+", "+Integer.parseInt(dni)+", '"+ape+"', '"+espe+"' )");
		db.close();
	}

}
