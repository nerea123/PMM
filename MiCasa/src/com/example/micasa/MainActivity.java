package com.example.micasa;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new CasaView(this));
	}
	
	public class CasaView extends View{

		String accion="accion";
		float x=50;
		float y=50;
		Path path= new Path();
		public CasaView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		public void onDraw(Canvas canvas){
	
			/*ShapeDrawable miDrawable=new ShapeDrawable(new OvalShape());
			miDrawable.setBounds(10, 10, 100, 100);
			miDrawable.draw(canvas);*/
			Paint pincel=new Paint();
			Path trazo = new Path();
			pincel.setColor(Color.RED);
			pincel.setStrokeWidth(15);
			pincel.setStyle(Style.FILL);
			pincel.setStrokeWidth(1);
			canvas.drawRect(20, 150, 170, 300, pincel);
			pincel.setColor(Color.MAGENTA);
			trazo.setFillType(FillType.EVEN_ODD);
			pincel.setAntiAlias(true);
			//canvas.drawLine(20, 150, 95, 50, pincel);
			//canvas.drawLine(170, 150, 95, 50, pincel);
			trazo.moveTo(20, 150);
			trazo.lineTo(95, 50);
			//trazo.moveTo(170, 150);
			trazo.lineTo(170, 150);
			trazo.lineTo(20, 150);
			trazo.close();
			canvas.drawPath(trazo, pincel);
			pincel.setColor(Color.BLUE);
			canvas.drawRect(58,178,78,200,pincel);
			canvas.drawRect(118,178,138,200,pincel);
			pincel.setColor(Color.BLACK);
			canvas.drawLine(68, 178, 68, 200, pincel);
			canvas.drawLine(58,189,78,189,pincel);
			canvas.drawLine(128, 178, 128, 200, pincel);
			canvas.drawLine(118,189,138,189,pincel);
			pincel.setColor(Color.LTGRAY);
			canvas.drawRect(83, 250, 113, 300, pincel);
			pincel.setColor(Color.YELLOW);
			canvas.drawCircle(108, 265, 2, pincel);
			pincel.setColor(Color.BLUE);
			//canvas.drawLine(95, 50,210,50, pincel);
			Path trazo2=new Path();
			trazo2.moveTo(95, 50);
			trazo2.lineTo(210, 50);
			trazo2.lineTo(275, 150);
			trazo2.lineTo(170, 150);
			trazo2.lineTo(95, 50);
			canvas.drawPath(trazo2, pincel);
			trazo2.close();
			pincel.setColor(Color.MAGENTA);
			Path trazo3=new Path();
			trazo3.moveTo(170, 150);
			trazo3.lineTo(170, 300);
			trazo3.lineTo(275, 275);
			trazo3.lineTo(275, 150);
			trazo3.lineTo(170, 150);
			canvas.drawPath(trazo3, pincel);
			trazo3.close();
			//trazo.lineTo(275, 275);
			//canvas.drawLine(210,50,275,150, pincel);
			//canvas.drawLine(170, 150, 275, 150, pincel);
			//canvas.drawLine(275, 150,275,275, pincel);
			pincel.setStrokeWidth(2);
			//canvas.drawLine(170, 300, 275, 275, pincel);
			
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
