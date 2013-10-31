package com.example.mimenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Pspiner extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pspiner);
		final TextView t1=(TextView)findViewById(R.id.pnombre);
		final TextView t2=(TextView)findViewById(R.id.pespecie);
		final ImageView ima=(ImageView)findViewById(R.id.pfoto2);
		
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
		getMenuInflater().inflate(R.menu.pspiner, menu);
		return true;
	}

}
