package com.example.basedatosspinner;





import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	SQLiteDatabase db;
	
	static class ViewHolder{
		TextView nombre;
		TextView telefono;
	
	}
	
	Cliente datos[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "DBClientes", null, 1);
		db = cliBDh.getReadableDatabase();
		leerRegistros();
		
		final Spinner spi=(Spinner)findViewById(R.id.spiner);
		AdaptadorCliente adap=new AdaptadorCliente(this);
		spi.setAdapter(adap);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void leerRegistros(){
		String[] campos = new String[] {"nombre", "telefono"};
		String[] args = new String[] {"AAA1"};
		String cadSQL = "CREATE TABLE IF NOT EXISTS Clientes  (codigo INTEGER, nombre TEXT, telefono TEXT)";
		for (int cont=1; cont<=3; cont++) {
    		
    		int codigo = cont;
    		String nombre = "AAA" + cont;
    		String telefono = cont + "XXXXXXX";
    		
    		
    		db.execSQL("INSERT INTO Clientes (codigo, nombre, telefono) " +
    					"VALUES (" + codigo + ", '" + nombre + "', '" + telefono + "')");
		}
		db.execSQL(cadSQL);
		Cursor c = db.query("Clientes", campos, null, null, null, null, null);
		datos=new Cliente[c.getCount()];
		int i=0;
		if (c.moveToFirst()) {
			
			do {
				String nombreCli = c.getString(0);
				String telefonoCli = c.getString(1);
				
				datos[i]=new Cliente(nombreCli,telefonoCli);
				i++;
				
			} while (c.moveToNext());
		}
	}
	
	class AdaptadorCliente extends ArrayAdapter{

		Activity context;
		@SuppressWarnings("unchecked")
		public AdaptadorCliente(Activity context) {
			super(context, R.layout.downspiner,datos);
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
				item=inflater.inflate(R.layout.downspiner,null);
				
				holder=new ViewHolder();
				holder.nombre=(TextView)item.findViewById(R.id.nombre);
				holder.telefono=(TextView)item.findViewById(R.id.telf);
				
				
				item.setTag(holder);
				
			}
			else
				holder=(ViewHolder)item.getTag();
			
			holder.nombre.setText(datos[posicion].getNombre());
			holder.telefono.setText(datos[posicion].getTelf());
			
			return item;
		}
		
	}

}
