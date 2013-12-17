package com.example.examenandroid;


	import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

	public class Dibujo extends Activity {

		View view;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(new CasaView(this));
		}
		
		public class CasaView extends View{

			public CasaView(Context context) {
				super(context);
				// TODO Auto-generated constructor stub
			}
			
			public void onDraw(Canvas canvas){
		
				Paint pincel=new Paint();
				Path trazo = new Path();
				pincel.setStyle(Style.FILL);
				pincel.setStrokeWidth(15);
				pincel.setColor(Color.MAGENTA);
				trazo.setFillType(FillType.EVEN_ODD);
				pincel.setAntiAlias(true);
				canvas.drawCircle(300, 200, 50, pincel);
				pincel.setColor(Color.BLUE);
				canvas.drawCircle(280, 180, 2, pincel);
				canvas.drawCircle(310, 180, 2, pincel);
				canvas.drawCircle(300, 220, 10, pincel);
				
				pincel.setColor(Color.RED);
				trazo.moveTo(250, 160);
				trazo.lineTo(350, 160);
				trazo.lineTo(300, 50);
				trazo.lineTo(250, 160);
				trazo.close();
				canvas.drawPath(trazo,pincel);
				
				Path trazo2 = new Path();
				pincel.setColor(Color.CYAN);
				trazo2.moveTo(300, 250);
				trazo2.lineTo(200, 350);
				trazo2.lineTo(400, 350);
				trazo2.lineTo(300, 250);
				
				canvas.drawPath(trazo2,pincel);
				pincel.setStrokeWidth(2);
				pincel.setColor(Color.BLUE);
				canvas.drawLine(270, 280, 200, 300, pincel);
				canvas.drawLine(320, 280, 400, 300, pincel);
				canvas.drawLine(300, 350, 300, 450, pincel);
				canvas.drawLine(310, 350, 310, 450, pincel);

				
			}
		
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

	}

