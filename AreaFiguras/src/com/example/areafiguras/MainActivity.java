package com.example.areafiguras;





import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	String figura;
	int color;
	
	static class ViewHolder{
		TextView figura;
	}
	
	Figuras[] datos= new Figuras[]{new Figuras("Selecciona una figura"),
			new Figuras("Cuadrado"),
			new Figuras("Rectangulo"),
			new Figuras("Circulo"),
			new Figuras("Pintar")}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Spinner spin=(Spinner)findViewById(R.id.spin);
		AdaptadorFiguras adap=new AdaptadorFiguras(this);
		spin.setAdapter(adap);
		
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			
				figura=((Figuras)arg0.getAdapter().getItem(arg2)).getFigura();
				if(figura.equals("Pintar")){
					Intent i=new Intent(MainActivity.this,Pintar.class);
					Bundle bundle=new Bundle();
					bundle.putInt("Color", color);
					i.putExtras(bundle);
					startActivity(i);
				}
				else{
					Intent intent=new Intent(MainActivity.this,Dibujo.class);
					Bundle b=new Bundle();
					b.putString("figura", figura);
					b.putInt("Color", color);
					intent.putExtras(b);
					if(arg2>0){
						startActivity(intent);
						}
				}
				
			}
			
			public boolean esPintar(){
				if(figura.equalsIgnoreCase("pintar"))
					return true;
				else
					return false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	class AdaptadorFiguras extends ArrayAdapter<Figuras>{

		Activity context;
		public AdaptadorFiguras(Activity context) {
			super(context,R.layout.spiner,datos);
			this.context=context;
			// TODO Auto-generated constructor stub
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
				holder.figura=(TextView)item.findViewById(R.id.txt);
				item.setTag(holder);
				
			}
			else
				holder=(ViewHolder)item.getTag();
			
			holder.figura.setText(datos[posicion].getFigura());
			return item;
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.amarillo:
		color=1;
		return true;
		case R.id.azul:
			System.out.println("holaaaa");
				
		color=2;
		System.out.println(color+"holaaaa");
		return true;
		case R.id.rojo:
		color=3;	
		return true;
		case R.id.verde:
		color=4;	
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
	}


	
}
