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
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new LibreP(this));
	}
	
	class LibreP extends View{

		String accion="accion";
		float x=50;
		float y=50;
		Path path= new Path();
		
		public LibreP(Context context) {
			super(context);
		
			
		}
		
		public void onDraw(Canvas canvas){
			Paint pincel=new Paint();
			pincel.setStyle(Style.STROKE);
			pincel.setStrokeWidth(6);
			pincel.setColor(Color.GREEN);
			
			if(accion=="down"){
				path.moveTo(x, y);
			}
			
			if(accion=="move"){
				path.lineTo(x, y);
			}
			
			canvas.drawPath(path, pincel);
		}
		
		public boolean onTouchEvent(MotionEvent e){
			
			x=e.getX();
			y=e.getY();
			
			if(e.getAction() == MotionEvent.ACTION_DOWN){
				accion="down";
			}
			
			if(e.getAction() == MotionEvent.ACTION_MOVE){
				accion="move";
			}
			
			invalidate();
			
			return true;
			
		}
	}

}
