package com.example.examenandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	String anterior="";
	double precio;
	String zona;
	String continente;
	String peso;
	String tarifa;
	boolean normal=true;
	boolean dec;
	boolean tarj;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Spinner spin=(Spinner)findViewById(R.id.spin);
		final CheckBox c1=(CheckBox)findViewById(R.id.regalo);
		final CheckBox c2=(CheckBox)findViewById(R.id.tarjeta);
		final RadioButton r1=(RadioButton)findViewById(R.id.normal);
		final RadioButton r2=(RadioButton)findViewById(R.id.urgente);
		final TextView text=(TextView)findViewById(R.id.editPeso);
		final Button calcular=(Button)findViewById(R.id.calcular);
		
		anterior = String.valueOf(text.getText());
		System.out.println(anterior);
		AdaptadorDestino adaptador=new AdaptadorDestino(this);
		spin.setAdapter(adaptador);
		
		text.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(String.valueOf(text.getText()).equalsIgnoreCase(anterior)){
					text.setText("");
					text.setTextColor(Color.BLACK);
				}
				return false;
			}
		});
		
		text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(String.valueOf(text.getText()).equalsIgnoreCase(anterior)){
					text.setTextColor(Color.BLACK);
					text.setText("");
				}
				
			}
		});
		
		c1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(c1.isChecked())
					dec=true;
				else
					dec=false;
				
			}
		});
		
c2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(c2.isChecked())
					tarj=true;
				else
					tarj=false;
				
			}
		});
		
		r1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(r1.isChecked())
					normal=true;
				else
					normal=false;
				
			}
		});
		
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				precio=((Destino)arg0.getAdapter().getItem(arg2)).getPrecio();
				zona=((Destino)arg0.getAdapter().getItem(arg2)).getZona();
				continente=((Destino)arg0.getAdapter().getItem(arg2)).getContinente();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		calcular.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,Resultado.class);
				Bundle b=new Bundle();

				
				if(zona.equalsIgnoreCase("zona a"))
					precio=30;
				
				else if(zona.equalsIgnoreCase("zona b"))
					precio=20;
				else if(zona.equalsIgnoreCase("zona b"))
					precio=10;
				
				peso=String.valueOf(text.getText());
				if(peso.isEmpty() || peso.equalsIgnoreCase(anterior)){
					text.setTextColor(Color.RED);
					text.setText("Introduce el peso del paquete");
					return;
				}
				double precioInt=Double.parseDouble(peso);
				if(precioInt <=5)
					precio+=1*precioInt;
				else if(precioInt <=10 )
					precio+=1.5*precioInt;
				else
					precio+=2*precioInt;
				
				if(!normal){
					precio+=precio*30/100;
					tarifa="urgente";
				}
				else
					tarifa="normal";
				
				b.putString("zona",zona );
				b.putString("tarifa",tarifa );
				b.putString("peso", peso);
				b.putDouble("precio", precio);
				if(dec)
					b.putString("regalo", "Con caja regalo");
				if(tarj)
					b.putString("dedicatoria", "dedicatoria");
				
				i.putExtras(b);
				startActivity(i);
				
				
			}
		});
	
	}
	
	Destino[] datos=new Destino[]{new Destino("Zona A","Asia y Oceanía",30),
			new Destino("Zona B","America y Africa",20),
			new Destino("Zona C","Europa",10),};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	static class ViewHolder{
		TextView zona;
		TextView continente;
		TextView precio;
	}
	
	class AdaptadorDestino extends ArrayAdapter<Destino>{

		Activity context;
		public AdaptadorDestino(Activity context) {
			super(context, R.layout.spiner,datos);
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
				item=inflater.inflate(R.layout.spiner,null);
				
				holder=new ViewHolder();
				holder.zona=(TextView)item.findViewById(R.id.zona);
				holder.continente=(TextView)item.findViewById(R.id.continente);
				holder.precio=(TextView)item.findViewById(R.id.precio);
				
				item.setTag(holder);
				
			}
			else
				holder=(ViewHolder)item.getTag();
			
			
			holder.zona.setText(datos[posicion].getZona());
			holder.continente.setText(datos[posicion].getContinente());
			double precio=datos[posicion].getPrecio();
			holder.precio.setText(String.valueOf(precio));
			
			
			return item;
		}
		
		
	
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.acerca:
			Toast.makeText(this,"Nerea Muñoz", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.dibujo:
		Intent intent=new Intent(MainActivity.this,Dibujo.class);
		startActivity(intent);
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
	}


}
