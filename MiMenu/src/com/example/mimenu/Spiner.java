package com.example.mimenu;

import com.example.mimenu.Listas.ViewHolder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Spiner extends Activity {

	
	static class ViewHolder{
		TextView nombre;
		TextView especie;
		ImageView foto;
	}
	
	Animales[] datos=new Animales[]{new Animales("Selecciona una opcion","Nada seleccionado",R.drawable.interrogante),
			new Animales("Delfin","Stenella dubia",R.drawable.d),
			new Animales("Lobo","Canis lupus baileyi",R.drawable.lobo),
			new Animales("Gato","Felis catus",R.drawable.g)};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spiner);
		final Spinner spin=(Spinner)findViewById(R.id.spin);
		AdaptadorAnimales adap=new AdaptadorAnimales(this);
		spin.setAdapter(adap);
		
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Intent intent=new Intent(Spiner.this,Pspiner.class);
				String nombre=((Animales)arg0.getAdapter().getItem(arg2)).getNombre();
				String especie=((Animales)arg0.getAdapter().getItem(arg2)).getEspecie();
				int foto=((Animales)arg0.getAdapter().getItem(arg2)).getFoto();
				Bundle b=new Bundle();
				b.putString("marca",nombre);
				b.putString("modelo",especie);
				b.putInt("foto", foto);
				intent.putExtras(b);
				if(arg2>0){
				startActivity(intent);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	class AdaptadorAnimales extends ArrayAdapter<Animales>{

		Activity context;
		public AdaptadorAnimales(Activity context) {
			super(context, R.layout.listas,datos);
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
				item=inflater.inflate(R.layout.listas,null);
				
				holder=new ViewHolder();
				holder.nombre=(TextView)item.findViewById(R.id.marca);
				holder.especie=(TextView)item.findViewById(R.id.modelo);
				holder.foto=(ImageView)item.findViewById(R.id.foto);
				
				item.setTag(holder);
				
			}
			else
				holder=(ViewHolder)item.getTag();
			
			holder.nombre.setText(datos[posicion].getNombre());
			holder.especie.setText(datos[posicion].getEspecie());
			holder.foto.setImageResource(datos[posicion].getFoto());
			
			return item;
		}

	
	}

}
