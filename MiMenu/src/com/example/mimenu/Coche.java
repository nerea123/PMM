package com.example.mimenu;

public class Coche {

	String marca;
	String modelo;
	int foto;
	
	public Coche(String m, String mo,int f){
		marca=m;
		modelo=mo;
		foto=f;
	}
	
	public String getMarca(){
		return marca;
	}
	public String getModelo(){
		return modelo;
	}
	public int getFoto(){
		return foto;
	}
}
