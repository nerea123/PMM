package com.example.examenandroid;

public class Destino {

	private String zona;
	private String continente;
	private double precio;
	
	Destino(String zona, String continente, int precio){
		this.continente=continente;
		this.precio=precio;
		this.zona=zona;
	}
	
	public String getZona(){
		return zona;
	}
	
	public String getContinente(){
		return continente;
	}
	
	public double getPrecio(){
		return precio;
	}
}
