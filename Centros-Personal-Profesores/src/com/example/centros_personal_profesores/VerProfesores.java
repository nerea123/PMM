package com.example.centros_personal_profesores;

import com.example.centros_personal_profesores.VerPersonal.AdaptadorPersonal;
import com.example.centros_personal_profesores.VerPersonal.ViewHolder;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class VerProfesores extends Activity {
	 static class ViewHolder{
         TextView dni;
         TextView especialidad;
         TextView apellido;
         TextView cod;
         
 
 }
	 SQLiteDatabase db;
	 Profesores datos[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_profesores);
		
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getReadableDatabase();
        leerRegistros();
        
        final Spinner spi=(Spinner)findViewById(R.id.spinerprofesores);
        AdaptadorPersonal adap=new AdaptadorPersonal(this);
        spi.setAdapter(adap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_profesores, menu);
		return true;
	}
	
	public void leerRegistros(){
        String[] campos = new String[] {"cod_centro","dni","apellidos","especialidad"};

        Cursor c = db.query("profesores", campos, null, null, null, null, null);
        datos=new Profesores[c.getCount()];
        int i=0;
        if (c.moveToFirst()) {
                
                do {
                		int cod=c.getInt(0);
                		int dni=c.getInt(1);
                        String ape = c.getString(2);
                        String espe=c.getString(3);
                       
                       
                        
                        datos[i]=new Profesores(cod,dni,ape,espe);
                        i++;
                        
                } while (c.moveToNext());
        }
}
	
	 class AdaptadorPersonal extends ArrayAdapter{

         Activity context;
         @SuppressWarnings("unchecked")
         public AdaptadorPersonal(Activity context) {
                 super(context, R.layout.spinprofesores,datos);
                 this.context=context;
                 
         }
         
         public View getDropDownView(int posicion,View convertView,ViewGroup parent){
                 return getView(posicion,convertView,parent);
         }
         
         public View getView(int posicion,View convertView,ViewGroup parent){
                 View item=convertView;
                 ViewHolder holder;
                 if(item==null){
                         LayoutInflater inflater=context.getLayoutInflater();
                         item=inflater.inflate(R.layout.spinprofesores,null);
                         
                         holder=new ViewHolder();
                         holder.cod=(TextView)item.findViewById(R.id.codProf);
                         holder.dni=(TextView)item.findViewById(R.id.dniProf);
                         holder.apellido=(TextView)item.findViewById(R.id.apellidosProf);
                         holder.especialidad=(TextView)item.findViewById(R.id.especialidad);
                         
                     
                         
                         
                         item.setTag(holder);
                         
                 }
                 else
                         holder=(ViewHolder)item.getTag();
                 
                 holder.cod.setText("Cod: "+String.valueOf(datos[posicion].getCod()));
                 holder.dni.setText("DNI: "+String.valueOf(datos[posicion].getDni()));
                 holder.apellido.setText("Apellido: "+datos[posicion].getApellidos());
                 holder.especialidad.setText("Especialidad: "+datos[posicion].getEspecialidad());
                 
                 
                 
                 return item;
         }
         
 }

}
