package com.example.mimenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Plista extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plista);
		final TextView t1=(TextView)findViewById(R.id.pmarca);
		final TextView t2=(TextView)findViewById(R.id.pmodelo);
		final ImageView ima=(ImageView)findViewById(R.id.pfoto);
		
		Bundle b=getIntent().getExtras();
		String marca=b.getString("marca");
		String modelo=b.getString("modelo");
		int foto=b.getInt("foto");
		
		t1.setText(marca);
		t2.setText(modelo);
		ima.setImageResource(foto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plista, menu);
		return true;
	}

}
