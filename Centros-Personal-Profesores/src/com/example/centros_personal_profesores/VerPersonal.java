package com.example.centros_personal_profesores;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class VerPersonal extends Activity {
	 static class ViewHolder{
         TextView dni;
         TextView funcion;
         TextView salario;
         TextView apellido;
         TextView cod;
         
 
 }
	 SQLiteDatabase db;
	 Personal datos[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_personal);
		
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "CENTROS-PERSONAL-PROFESORES", null, 1);
        db = cliBDh.getReadableDatabase();
        leerRegistros();
        
        final Spinner spi=(Spinner)findViewById(R.id.spinPersonal);
        final AdaptadorPersonal adap=new AdaptadorPersonal(this);
        spi.setAdapter(adap);
        
        spi.setOnItemSelectedListener(new OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> arg0, View arg1,
    				int arg2, long arg3) {
    			
    			if(arg2 != 0){
    			
    				Intent i=new Intent(VerPersonal.this,Acciones_Personal.class);
    				Bundle b=new Bundle();
    				String cod=String.valueOf(datos[arg2].getCod());
    				String dni=String.valueOf(datos[arg2].getDni());
    				String ape=datos[arg2].getApellido();
    				String funcion=datos[arg2].getFuncion();
    				String salario=String.valueOf(datos[arg2].getSalario());
    				
    				
    				b.putString("cod",cod);
    				b.putString("dni", dni);
    				b.putString("ape", ape);
    				b.putString("salario", salario);
    				b.putString("funcion", funcion);
    				
    				
    				i.putExtras(b);
    				
    				startActivity(i);
    				finish();
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
		getMenuInflater().inflate(R.menu.ver_personal, menu);
		return true;
	}
	
	public void leerRegistros(){
        String[] campos = new String[] {"cod_centro","dni","apellidos","funcion", "salario"};

        Cursor c = db.query("personal", campos, null, null, null, null, null);
        datos=new Personal[c.getCount()+1];
        datos[0]= new Personal(0,0,"Ver nombre","Ver funcion",(float)0);
        int i=1;
        if (c.moveToFirst()) {
                
                do {
                		int cod=c.getInt(0);
                		int dni=c.getInt(1);
                        String ape = c.getString(2);
                        String fun=c.getString(3);
                        Float salario = c.getFloat(4);
                        
                        datos[i]=new Personal(cod,dni,ape,fun,salario);
                        i++;
                        
                } while (c.moveToNext());
        }
}
	
	 class AdaptadorPersonal extends ArrayAdapter{

         Activity context;
         @SuppressWarnings("unchecked")
         public AdaptadorPersonal(Activity context) {
                 super(context, R.layout.spinerpersonal,datos);
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
                         item=inflater.inflate(R.layout.spinerpersonal,null);
                         
                         holder=new ViewHolder();
                         holder.cod=(TextView)item.findViewById(R.id.codP);
                         holder.dni=(TextView)item.findViewById(R.id.dni);
                         holder.apellido=(TextView)item.findViewById(R.id.apellidos);
                         holder.funcion=(TextView)item.findViewById(R.id.funcion);
                         holder.salario=(TextView)item.findViewById(R.id.salario);
                     
                         
                         
                         item.setTag(holder);
                         
                 }
                 else
                         holder=(ViewHolder)item.getTag();
                 
                 holder.cod.setText("Cod: "+String.valueOf(datos[posicion].getCod()));
                 holder.dni.setText("DNI: "+String.valueOf(datos[posicion].getDni()));
                 holder.apellido.setText("Apellido: "+datos[posicion].getApellido());
                 holder.funcion.setText("Funcion: "+datos[posicion].getFuncion());
                 holder.salario.setText("Salario: "+String.valueOf(datos[posicion].getSalario()));
                 
                 
                 return item;
         }
         
 }

}
