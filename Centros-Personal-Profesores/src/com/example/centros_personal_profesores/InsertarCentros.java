package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class InsertarCentros extends Activity {

	SQLiteDatabase db;
    TextView cod;
    TextView tipo;
    TextView nom;
    TextView tel;
    TextView dir;
    TextView plazas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_centros);
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getWritableDatabase();
        
        cod=(EditText)findViewById(R.id.insCodCentro);
        tipo=(EditText)findViewById(R.id.insTipoCentro);
        nom=(EditText)findViewById(R.id.insNombreCentro);
        tel=(EditText)findViewById(R.id.insTelfCentro);
        dir=(EditText)findViewById(R.id.insDirCentro);
        plazas=(EditText)findViewById(R.id.insPlazasCentro);
        Button b=(Button)findViewById(R.id.binsCentro);
		
        b.setOnClickListener(new OnClickListener() {
			
    			@Override
    			public void onClick(View arg0) {
    				String s= cod.getText().toString();
    				 isertar((String) cod.getText().toString()
    						 ,(String)tipo.getText().toString()
    			        		,(String)nom.getText().toString()
    			        		,(String)dir.getText().toString(),(String)tel.getText().toString(),(String)plazas.getText().toString());
    				 Intent i=new Intent(InsertarCentros.this,VerCentros.class);
    				 startActivity(i);
    			}
    		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_centros, menu);
		return true;
	}
	
public void isertar(String cod,String tipo,String nom,String dir,String tlf,String plazas){
		
		
		db.execSQL("insert into centros values( "+Integer.parseInt(cod)+", '"+tipo+"', '"+nom+"', '"+dir+"','"+tlf+"',"+Integer.parseInt(plazas)+" )");
		db.close();
	}

}
