package com.example.basedatosspinner;

public class Cliente {
	
	String nombre;
	String telefono;
	
	public Cliente(String nombre,String telefono){
		this.nombre=nombre;
		this.telefono=telefono;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getTelf(){
		return telefono;
	}

}
