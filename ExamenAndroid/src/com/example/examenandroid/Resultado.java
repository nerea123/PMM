package com.example.examenandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Resultado extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		final TextView text1=(TextView)findViewById(R.id.rZona);
		final TextView text2=(TextView)findViewById(R.id.rTarifa);
		final TextView text3=(TextView)findViewById(R.id.rPeso);
		final TextView text4=(TextView)findViewById(R.id.rDecoracion);
		final TextView text5=(TextView)findViewById(R.id.rFinal);
		
		Bundle b=getIntent().getExtras();
		text1.setText(text1.getText()+b.getString("zona"));
		text2.setText(text2.getText()+b.getString("tarifa"));
		text3.setText(text3.getText()+b.getString("peso"));
		if(b.getString("regalo")!=null)
			text4.setText(text4.getText()+" "+b.getString("regalo"));
		if(b.getString("dedicatoria")!=null)
			text4.setText(text4.getText()+" "+b.getString("dedicatoria"));
		text5.setText(text5.getText()+String.valueOf(b.getDouble("precio")));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	}

}
