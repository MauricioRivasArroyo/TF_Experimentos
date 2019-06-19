package com.ventas.services;

public class Producto {
	private int id;	
	private String nombre;
	private int categoria;
	private String codigo;

	
	public Producto() {
	}
	
	public Producto(int id,String nombre, int categoria, String codigo) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.codigo = codigo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}	
	
}
