package com.example.examenandroid;



import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class Cambio extends Activity {

	TransitionDrawable mi_transicion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cambio);
		final TextView text=(TextView)findViewById(R.id.billetes);
		final ImageView img1=(ImageView)findViewById(R.id.imagen);
		Bundle b=getIntent().getExtras();
		text.setText(b.getString("cambio"));
		
		mi_transicion = (TransitionDrawable)getResources().getDrawable(R.drawable.transicion);
		img1.setImageDrawable(mi_transicion);
		mi_transicion.startTransition(2000);
	}
	
	public boolean onTouchEvent(MotionEvent evento) {
    	//Al realizar una pulsación en pantalla
    	if (evento.getAction() == MotionEvent.ACTION_DOWN) {
    		//Ponemos en marcha la animación
    		mi_transicion.startTransition(2000);
    		return true;
    	}
    	return super.onTouchEvent(evento);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cambio, menu);
		return true;
	}

}
