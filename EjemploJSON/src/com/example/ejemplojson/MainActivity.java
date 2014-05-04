package com.example.ejemplojson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText longitud;
	private EditText latitud;
	private Button boton;
	private TextView texto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		longitud=(EditText)findViewById(R.id.longitud);
		latitud=(EditText)findViewById(R.id.latitud);
		boton=(Button)findViewById(R.id.boton);
		
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String longitudCad=longitud.getText().toString();
				String latitudCad=latitud.getText().toString();
				String resultado= busquedaGoogle(longitudCad,latitudCad);
				texto.append(resultado);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	String busquedaGoogle(String longitud, String lattud){
		String devuelve="";
		HttpClient comunicacion= new DefaultHttpClient();
		HttpGet peticion = new HttpGet("http://maps.googleapis.com/maps/api/geocode/json?" +
				"latlng=" + latitud + "," + longitud + "&sensor=false");

		peticion.setHeader("content-type", "application/json");
		
		try{
			HttpResponse respuesta= comunicacion.execute(peticion);
			String respuestaCad=EntityUtils.toString(respuesta.getEntity());
			JSONObject respuestaJSON = new JSONObject(respuestaCad);
			JSONArray resultJSON = respuestaJSON.getJSONArray("results");
			
			String direccion="SIN DATOS";
			if(resultJSON.length() >0){
				direccion= resultJSON.getJSONObject(0).getString("format_address");
			}
			devuelve="Direccion: "+direccion;
			
		}catch(Exception e){
			Log.e("Error ies REST", "ERROR!!",e);
		}
		return devuelve;
	}

}
