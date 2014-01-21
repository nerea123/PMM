package com.example.centros_personal_profesores;

public class Centros {
	
	int cod;
	String tipo;
	String nombre;
	String direccion;
	String telefono;
	int plazas;
	
	public Centros(int cod,String tipo,String nombre,String direccion, String telefono, int plazas ){
		this.cod=cod;
		this.tipo=tipo;
		this.nombre=nombre;
		this.direccion=direccion;
		this.telefono=telefono;
		this.plazas=plazas;
	}
	
	public int getCod(){
		return cod;
	}

	public String getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public int getPlazas() {
		return plazas;
	}

}
