package com.example.areafiguras;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dibujo extends Activity {

	TextView txt;
	TextView txt2;
	String figura;
	int color;
	EditText lado_radio;
	EditText altura;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dibujo);
		txt=(TextView)findViewById(R.id.texto);
		txt2=(TextView)findViewById(R.id.texto2);
		lado_radio=(EditText)findViewById(R.id.lado);
		altura=(EditText)findViewById(R.id.altura);
		Button boton=(Button)findViewById(R.id.boton);
		Bundle b=getIntent().getExtras();
		figura=b.getString("figura");
		color=b.getInt("Color");
		
		if(esCuadrado()){
			txt.setText("Introduce el lado del cuadrado");
			lado_radio.setVisibility(View.VISIBLE);}
		else if(esRectangulo()){
			txt.setText("Introduce la base del rectangulo");
			lado_radio.setVisibility(View.VISIBLE);
			altura.setVisibility(View.VISIBLE);
			txt2.setVisibility(View.VISIBLE);
			txt2.setText("Introduce la altura del rectangulo");
		}
		else{
			txt.setText("Introduce el radio del circulo");
			lado_radio.setVisibility(View.VISIBLE);
			}
		
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(esCuadrado()){
					String lado=lado_radio.getText().toString();
					Bundle b=new Bundle();
					b.putString("lado", lado);
					b.putString("figura", figura);
					b.putInt("Color", color);
					Intent intent=new Intent(Dibujo.this,Dibuja_Figura.class);
					intent.putExtras(b);
					startActivity(intent);
					}
				else if(esRectangulo()){
					String lado=lado_radio.getText().toString();
					String alt=altura.getText().toString();
					Bundle b=new Bundle();
					b.putString("lado", lado);
					b.putString("alt", alt);
					b.putString("figura", figura);
					b.putInt("Color", color);
					Intent intent=new Intent(Dibujo.this,Dibuja_Figura.class);
					intent.putExtras(b);
					startActivity(intent);
				}
				else{
					String lado=lado_radio.getText().toString();
					Bundle b=new Bundle();
					b.putString("lado", lado);
					b.putString("figura", figura);
					b.putInt("Color", color);
					Intent intent=new Intent(Dibujo.this,Dibuja_Figura.class);
					intent.putExtras(b);
					startActivity(intent);
				}
				
			}
		});
		
	}

	public boolean esCuadrado(){
		if(figura.equalsIgnoreCase("cuadrado"))
			return true;
		else
			return false;
	}
	
	public boolean esRectangulo(){
		if(figura.equalsIgnoreCase("rectangulo"))
			return true;
		else
			return false;
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dibujo, menu);
		return true;
	}

}
