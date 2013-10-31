package com.example.mimenu;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Listas extends Activity {

	static class ViewHolder{
		TextView marca;
		TextView modelo;
		ImageView foto;
	}
	Coche[] datos=new Coche[]{new Coche("Lamborghini","Veneno Roadster",R.drawable.l),
			new Coche("Porsche","Cayenne S Diesel Tiptronic S ",R.drawable.p),
			new Coche("Volkswagen","Polo R WRC",R.drawable.v)};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listas);
		final TextView t1=(TextView)findViewById(R.id.marca);
		final TextView t2=(TextView)findViewById(R.id.modelo);
		final ImageView ima=(ImageView)findViewById(R.id.foto);
		final ListView lista=(ListView)findViewById(R.id.list);
		AdaptadorCoche coche=new AdaptadorCoche(this);
		lista.setAdapter(coche);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent inten=new Intent(Listas.this,Plista.class);
				Bundle b=new Bundle();
				String marca=((Coche)arg0.getAdapter().getItem(arg2)).getMarca();
				String modelo=((Coche)arg0.getAdapter().getItem(arg2)).getModelo();
				int foto=((Coche)arg0.getAdapter().getItem(arg2)).getFoto();
				b.putString("marca",marca);
				b.putString("modelo",modelo);
				b.putInt("foto", foto);
				inten.putExtras(b);
				
				startActivity(inten);
				
			}
		});

		}
	
	class AdaptadorCoche extends ArrayAdapter<Coche>{

		Activity context;
		public AdaptadorCoche(Activity context) {
			super(context, R.layout.listas,datos);
			this.context=context;
		}
		
		public View getView(int posicion,View convertView,ViewGroup parent){
			View item=convertView;
			ViewHolder holder;
			if(item==null){
				LayoutInflater inflater=context.getLayoutInflater();
				item=inflater.inflate(R.layout.listas,null);
				
				holder=new ViewHolder();
				holder.marca=(TextView)item.findViewById(R.id.marca);
				holder.modelo=(TextView)item.findViewById(R.id.modelo);
				holder.foto=(ImageView)item.findViewById(R.id.foto);
				
				item.setTag(holder);
				
			}
			else
				holder=(ViewHolder)item.getTag();
			
			holder.marca.setText(datos[posicion].getMarca());
			holder.modelo.setText(datos[posicion].getModelo());
			holder.foto.setImageResource(datos[posicion].getFoto());
			
			return item;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listas, menu);
		return true;
	}

}
