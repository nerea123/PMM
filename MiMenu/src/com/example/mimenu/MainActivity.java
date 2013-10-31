package com.example.mimenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt=(TextView)findViewById(R.id.textmain);
		txt.setText("Ejercicio de menu");
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.CS:
			Intent intent2=new Intent(MainActivity.this,ComponentesSeleccion.class);
			startActivity(intent2);
			//txt.setText("");
			return true;
		case R.id.CB:
		Intent intent=new Intent(MainActivity.this,ComponentesBasicas.class);
		startActivity(intent);
		//txt.setText("");
		return true;
		case R.id.ad:
			Intent intent3=new Intent(MainActivity.this,AcercaDe.class);
			startActivity(intent3);
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
		}


}
