package model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ProductoResponse {
	private int id;
	private String cedula;
	private String nombre;
	private String apellido;
	private String genero;
	private String categoria;
	private String correo;

	
	public ProductoResponse() {
	}

	public ProductoResponse(int id, String cedula, String nombre, String apellido, String genero, String categoria,
			String correo) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.categoria = categoria;
		this.correo = correo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String toString() {
		return this.getId() + " " + 
				this.getCedula() + " " + 
				this.getNombre() + " " + 
				this.getApellido() + " " + 
				this.getGenero() + " " + 
				this.getCategoria() + " " + 
				this.getCorreo();
	}
}
