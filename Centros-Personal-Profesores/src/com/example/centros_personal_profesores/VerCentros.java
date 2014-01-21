package com.example.centros_personal_profesores;

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

public class VerCentros extends Activity {
	
	 static class ViewHolder{
         TextView nombre;
         TextView telefono;
         TextView dir;
         TextView plazas;
         TextView tipo;
         TextView cod;
         
 
 }
	 SQLiteDatabase db;
	 Centros datos[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_centros);
		
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getReadableDatabase();
        leerRegistros();
        
        final Spinner spi=(Spinner)findViewById(R.id.spin);
        AdaptadorCentro adap=new AdaptadorCentro(this);
        spi.setAdapter(adap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_centros, menu);
		return true;
	}
	
	public void leerRegistros(){
        String[] campos = new String[] {"cod_centro","tipo_centro","nombre","direccion", "telefono","num_plazas"};

        Cursor c = db.query("centros", campos, null, null, null, null, null);
        datos=new Centros[c.getCount()];
        int i=0;
        if (c.moveToFirst()) {
                
                do {
                		int cod=c.getInt(0);
                		String tipo=c.getString(1);
                        String nombre = c.getString(2);
                        String dir=c.getString(3);
                        String telefono = c.getString(4);
                        int plazas=c.getInt(5);
                        
                        datos[i]=new Centros(cod,tipo,nombre,dir,telefono,plazas);
                        i++;
                        
                } while (c.moveToNext());
        }
}
	
	 class AdaptadorCentro extends ArrayAdapter{

         Activity context;
         @SuppressWarnings("unchecked")
         public AdaptadorCentro(Activity context) {
                 super(context, R.layout.spinercentro,datos);
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
                         item=inflater.inflate(R.layout.spinercentro,null);
                         
                         holder=new ViewHolder();
                         holder.cod=(TextView)item.findViewById(R.id.cod);
                         holder.tipo=(TextView)item.findViewById(R.id.tipo);
                         holder.nombre=(TextView)item.findViewById(R.id.nombre);
                         holder.dir=(TextView)item.findViewById(R.id.direccion);
                         holder.telefono=(TextView)item.findViewById(R.id.telf);
                         holder.plazas=(TextView)item.findViewById(R.id.plazas);
                         
                         
                         item.setTag(holder);
                         
                 }
                 else
                         holder=(ViewHolder)item.getTag();
                 
                 holder.cod.setText("Cod: "+String.valueOf(datos[posicion].getCod()));
                 holder.tipo.setText("Tipo: "+datos[posicion].getTipo());
                 holder.nombre.setText("Nombre: "+datos[posicion].getNombre());
                 holder.dir.setText("Direccion: "+datos[posicion].getDireccion());
                 holder.telefono.setText("Telf: "+datos[posicion].getTelefono());
                 holder.plazas.setText("Plazas: "+String.valueOf(datos[posicion].getPlazas()));
                 
                 return item;
         }
         
 }

}
