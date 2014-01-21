package com.example.centros_personal_profesores;

public class Personal {
	int cod;
	public int getCod() {
		return cod;
	}

	public int getDni() {
		return dni;
	}

	public String getApellido() {
		return apellido;
	}

	public String getFuncion() {
		return funcion;
	}

	public float getSalario() {
		return salario;
	}

	int dni;
	String apellido;
	String funcion;
	float salario;

	public Personal(int cod, int dni, String apellido, String funcion, float salario){
		this.apellido=apellido;
		this.cod=cod;
		this.dni=dni;
		this.funcion=funcion;
		this.salario=salario;
	}
}
