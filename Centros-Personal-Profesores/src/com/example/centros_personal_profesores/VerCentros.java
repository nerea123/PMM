package com.example.centros_personal_profesores;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class VerCentros extends Activity {
	
	Spinner spi;
	AdaptadorCentro adap;
	
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
        
        spi=(Spinner)findViewById(R.id.spin);
        
        adap=new AdaptadorCentro(this);
        adap.notifyDataSetChanged();
        spi.setAdapter(adap);
        
       
        
        spi.setOnItemSelectedListener(new OnItemSelectedListener() {
    	public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			
			if(arg2 != 0){
			
				Intent i=new Intent(VerCentros.this,AccionesCentro.class);
				Bundle b=new Bundle();
				String cod=String.valueOf(datos[arg2].getCod());
				String tipo=datos[arg2].getTipo();
				String nombre=datos[arg2].getNombre();;
				String dir=datos[arg2].getDireccion();
				String telf=datos[arg2].getTelefono();
				String plazas=String.valueOf(datos[arg2].getPlazas());
				
				b.putString("cod",cod);
				b.putString("tipo", tipo);
				b.putString("nombre", nombre);
				b.putString("dir", dir);
				b.putString("telf", telf);
				b.putString("pazas", plazas);
				
				i.putExtras(b);
				
				startActivity(i);
			}
			
			adap.notifyDataSetChanged();
			
		}
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});

		
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
        datos=new Centros[c.getCount()+1];
        datos[0]= new Centros(0,"","Ver nombre","Ver dir","",0);
        int i=1;
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
