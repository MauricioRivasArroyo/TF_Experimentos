package model;

import model.Categoria.CategoriaBuilder;

public class Categoria {
	private int id;	
	private String nombre;
	
	public Categoria() {
	}
	
	public Categoria(int id,String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public static CategoriaBuilder builder() {
		return new CategoriaBuilder();
	}
	
	public static class CategoriaBuilder{
		private int id;	
		private String nombre;
		
		public CategoriaBuilder setId(int id) {
		this.id = id;
		return this;
		}
		
		public CategoriaBuilder setNombre(String nombre) {
		this.nombre = nombre;
		return this;
		}
		
		public Categoria build() {
			return new Categoria(id,nombre);
		}

	}
}
