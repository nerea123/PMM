package com.example.areafiguras;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Dibuja_Figura extends Activity {

	String lado;
	String figura;
	String altura;
	int color;
	View pantalla; 
	Paint pincel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pantalla =new EjemploView(this);
		setContentView(pantalla);
		Bundle b=getIntent().getExtras();
		lado=b.getString("lado");
		figura=b.getString("figura");
		altura=b.getString("alt");
		color=b.getInt("Color");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dibuja__figura, menu);
		return true;
	}
	public class EjemploView extends View{

		public EjemploView(Context context) {
			super(context);
			
		}

		
		public double getAncho(){
			return pantalla.getWidth();
		}
		
		public double getAlto(){
			return pantalla.getHeight();
		}
		public void onDraw(Canvas canvas){
			pincel=new Paint();
			setcolor();
			pincel.setStrokeWidth(15);
			pincel.setStyle(Style.FILL);
			if(esCuadrado()){
				float lado2=Float.parseFloat(lado);
				double area=lado2*lado2;
				
				if(lado2+10>getAncho()||lado2+10>getAlto()){
					for(double i=lado2+10;i>getAncho();){
						lado2=(lado2+10)/2;
						i=lado2;
					}
				}
				canvas.drawRect(10, 10, lado2, lado2, pincel);
				canvas.drawText("El area es: "+area,10, lado2+50, pincel);
			}
			else if(esRectangulo()){
				float lado2=Float.parseFloat(lado);
				float alt=Float.parseFloat(altura);
				double area=lado2*alt;
				if(lado2+10>getAncho()){
					for(double i=lado2+10;i>getAncho();){
						lado2=(lado2+10)/2;
						alt=(alt+10)/2;
						i=lado2;
					}
				}else if(alt+10>getAlto()){
					for(double i=alt+10;i>getAlto();){
						lado2=(lado2+10)/2;
						alt=(alt+10)/2;
						i=alt;
					}
				}
				canvas.drawRect(10, 10, lado2, alt, pincel);
				canvas.drawText("El area es: "+area, 10, alt+50, pincel);
			}
			else{
				
				float radio=Float.parseFloat(lado);
				double area=Math.PI*radio*radio;
				if(radio+20>getAncho()){
					for(double i=radio+20;i>getAncho();){
						radio=(radio+20)/4;
						i=radio;
					}
				}else if(radio+20>getAlto()){
					for(double i=radio+20;i>getAncho();){
						radio=(radio+20)/4;
						i=radio;
					}
				}
				canvas.drawCircle(radio+20, radio+20, radio, pincel);
				pincel.setStrokeWidth(30);
				canvas.drawText("El area es: "+area, 10, radio*2+50, pincel);
			}
		}
		
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
	
	public void setcolor(){
		
		switch(color){
		case 1:
			pincel.setColor(Color.YELLOW);
			break;
		case 2:
			pincel.setColor(Color.BLUE);
			break;
		case 3:
			pincel.setColor(Color.RED);
			break;
		case 4:
			pincel.setColor(Color.GREEN);
			break;
		default:
			pincel.setColor(Color.BLACK);
		}
	}

}
