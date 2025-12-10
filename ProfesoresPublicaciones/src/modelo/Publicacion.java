package modelo;

import java.time.LocalDate;

public abstract class Publicacion {
	
	// atributos
	private LocalDate fechaPublicacion;
	private String titulo;
	
	// constructor
	public Publicacion() {

	}
	
	public Publicacion(LocalDate fechaPublicacion, String titulo) {
		this.fechaPublicacion = fechaPublicacion;
		this.titulo = titulo;
	}
	
	// getter y setter
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
