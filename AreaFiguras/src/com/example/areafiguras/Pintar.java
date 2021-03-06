package com.example.areafiguras;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Pintar extends Activity {
	int color;
	Paint pincel;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new LibreP(this));
		
	}
	
	class LibreP extends View{

	
		boolean accion=false;
		float x=50;
		float y=50;
		Path path= new Path();
		
		public LibreP(Context context) {
			super(context);
		
			
		}
		
		public void onDraw(Canvas canvas){
			pincel=new Paint();
			pincel.setStyle(Style.STROKE);
			pincel.setStrokeWidth(6);
			
			setcolor();
			
			if(!accion){
				path.moveTo(x, y);
			}
			
			if(accion){
				path.lineTo(x, y);
			}
			
			canvas.drawPath(path, pincel);
		}
		
		public boolean onTouchEvent(MotionEvent e){
			
			x=e.getX();
			y=e.getY();
			
			if(e.getAction() == MotionEvent.ACTION_DOWN){
				accion=false;
			}
			
			if(e.getAction() == MotionEvent.ACTION_MOVE){
				accion=true;
			}
			
			invalidate();
			
			return true;
			
		}
		
		public void setcolor(){
			
			Bundle b=getIntent().getExtras();
			color=b.getInt("Color");
			
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

}
