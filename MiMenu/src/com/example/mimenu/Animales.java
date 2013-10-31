package com.example.mimenu;

public class Animales {

	String nombre;
	String especie;
	int foto;
	
	public Animales(String n,String e, int f){
		nombre=n;
		especie=e;
		foto=f;
	}
	
	public String getNombre(){
		return nombre;
	}
	public String getEspecie(){
		return especie;
	}
	public int getFoto(){
		return foto;
	}
}
