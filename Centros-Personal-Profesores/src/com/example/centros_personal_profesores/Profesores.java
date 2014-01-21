package com.example.centros_personal_profesores;

public class Profesores {
	
	int cod;
	int dni;
	String apellidos;
	String especialidad;

	public Profesores(int cod, int dni, String apellidos, String especialidad){
	this.apellidos=apellidos;
	this.cod=cod;
	this.dni=dni;
	this.especialidad=especialidad;
	}

	public int getCod() {
		return cod;
	}

	public int getDni() {
		return dni;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getEspecialidad() {
		return especialidad;
	}
}
