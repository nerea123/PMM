package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditCentros extends Activity {

	SQLiteDatabase db;
    
    TextView tipo;
    TextView nom;
    TextView tel;
    TextView dir;
    TextView plazas;
    Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_centros);
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getWritableDatabase();
        
        b=getIntent().getExtras();
        
       
        tipo=(EditText)findViewById(R.id.editTipoCentro);
        nom=(EditText)findViewById(R.id.editNombreCentro);
        tel=(EditText)findViewById(R.id.editTelfCentro);
        dir=(EditText)findViewById(R.id.editDirCentro);
        plazas=(EditText)findViewById(R.id.editPlazasCentro);
        Button button=(Button)findViewById(R.id.binseditCentro);
        setTexto();
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				db.execSQL("UPDATE centros SET nombre='"+nom.getText().toString()+"',"
						+ "tipo_centro='"+tipo.getText().toString()+"',"
								+ "telefono='"+dir.getText().toString()+"',"
										+ "num_plazas="+Integer.parseInt(plazas.getText().toString())+" "
												+ "WHERE cod_centro="+Integer.parseInt(b.getString("cod")));
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_centros, menu);
		return true;
	}
	
	private void setTexto(){
		
		tipo.setText(b.getString("tipo"));
		nom.setText(b.getString("nombre"));
		dir.setText(b.getString("dir"));
		tel.setText(b.getString("telf"));
		plazas.setText(b.getString("pazas"));
	}

}
