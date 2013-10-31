package com.example.mimenu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RadioButton;

public class Radio extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio_button);
		final TextView t1=(TextView)findViewById(R.id.txt);
		final TextView t2=(TextView)findViewById(R.id.text);
		final RadioButton r1=(RadioButton)findViewById(R.id.rojo);
		final RadioButton r2=(RadioButton)findViewById(R.id.azul);
		final RadioButton r3=(RadioButton)findViewById(R.id.amarillo);
		final LinearLayout li=(LinearLayout)findViewById(R.id.linear);
		
		r1.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(r1.isChecked()){
					li.setBackgroundColor(Color.RED);
					t2.setTextColor(Color.BLUE);
					t2.setText("Has cambiado a : "+r1.getText());
					
				}
			}

		});
		r2.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(r2.isChecked()){
					li.setBackgroundColor(Color.BLUE);
					t2.setTextColor(Color.YELLOW);
					t2.setText("Has cambiado a : "+r2.getText());	
				}
			}

		});
		r3.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(r3.isChecked()){
					li.setBackgroundColor(Color.YELLOW);
					t2.setTextColor(Color.RED);
					t2.setText("Has cambiado a : "+r3.getText());	
				}
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.radio, menu);
		return true;
	}

}
