package modelo;

import java.time.LocalDate;

public class Libro extends Publicacion {
	
	// atributos
	private String isbn;
	private boolean esPremiado;
	
	// constructor
	public Libro() {

	}
	
	public Libro(LocalDate fechaPublicacion, String titulo, String isbn, boolean esPremiado) {
		super(fechaPublicacion, titulo);
		this.isbn = isbn;
		this.esPremiado = esPremiado;
	}
	
	// getter y setter
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public boolean isEsPremiado() {
		return esPremiado;
	}
	public void setEsPremiado(boolean esPremiado) {
		this.esPremiado = esPremiado;
	}
	
	// toString
	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", esPremiado=" + esPremiado + ", getFechaPublicacion()=" + getFechaPublicacion()
				+ ", getTitulo()=" + getTitulo() + "]";
	}
}
