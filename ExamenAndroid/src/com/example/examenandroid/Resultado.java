package com.example.examenandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.graphics.Typeface;

public class Resultado extends Activity {
	
	int precio,billetes[];
	String cadena="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		final TextView text1=(TextView)findViewById(R.id.rZona);
		final TextView text2=(TextView)findViewById(R.id.rTarifa);
		final TextView text3=(TextView)findViewById(R.id.rPeso);
		final TextView text4=(TextView)findViewById(R.id.rDecoracion);
		final TextView text5=(TextView)findViewById(R.id.rFinal);
		final Button cambio=(Button)findViewById(R.id.cambio);
		final ImageView foto=(ImageView)findViewById(R.id.rfoto);
		billetes=new int[]{500,200,100,50,20,10,5,2,1};
		
		
		Bundle b=getIntent().getExtras();
		if(b.getString("zona").equalsIgnoreCase("zona a"))
			foto.setImageResource(R.drawable.asia_oceania);
		else if(b.getString("zona").equalsIgnoreCase("zona b"))
			foto.setImageResource(R.drawable.america);
		else
			foto.setImageResource(R.drawable.europa);
		
		text1.setText(text1.getText()+b.getString("zona"));
		text2.setText(text2.getText()+b.getString("tarifa"));
		text3.setText(text3.getText()+b.getString("peso"));
		if(b.getString("regalo")!=null)
			text4.setText(text4.getText()+" "+b.getString("regalo"));
		if(b.getString("dedicatoria")!=null)
			text4.setText(text4.getText()+" "+b.getString("dedicatoria"));
		text5.setText(text5.getText()+String.valueOf(b.getDouble("precio")));
		precio=(int) b.getDouble("precio");
		
		ponNegrita(text1,0,6);
		ponNegrita(text2,0,8);
		ponNegrita(text3,0,6);
		ponNegrita(text4,0,12);
		ponNegrita(text5,0,13);
		
		cambio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				serararBilletes();
				Intent i=new Intent(Resultado.this,Cambio.class);
				Bundle b=new Bundle();
				b.putString("cambio", cadena);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}

	public void ponNegrita(TextView t,int inicio,int fin){
		
		SpannableStringBuilder ssb1 = new SpannableStringBuilder(t.getText());
		StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
		ssb1.setSpan(boldSpan, inicio,fin, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		t.setText(ssb1, BufferType.SPANNABLE);
	}
	public void serararBilletes(){
		int aux=0;
		while(precio!=0){
			for(int i : billetes){
				if(precio>=i){
					aux=precio/i;
					if(i==2 || i==1)
						cadena+="_"+aux+" monedas de "+i+"€\n";
					else
						cadena+="_"+aux+" billetes de "+i+"€\n";
					precio=precio%i;
				}
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	}

}
