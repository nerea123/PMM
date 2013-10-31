package com.example.mimenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ComponentesBasicas extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_componentes_basicas);
		final Button radio=(Button)findViewById(R.id.Radio);
		final Button checkBox=(Button)findViewById(R.id.check);
		
		radio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ComponentesBasicas.this,Radio.class);
				startActivity(intent);
				
			}
		});
		
		checkBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ComponentesBasicas.this,Check.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.componentes_basicas, menu);
		return true;
	}

}
