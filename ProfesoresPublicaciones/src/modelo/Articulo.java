package modelo;

public class Articulo extends Publicacion {
	// atributos
	private String medio;
	
	// constructor
	public Articulo() {

	}
	
	public Articulo(java.time.LocalDate fechaPublicacion, String titulo, String medio) {
		super(fechaPublicacion, titulo);
		this.medio = medio;
	}
	
	// getter y setter
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	
	// toString
	@Override
	public String toString() {
		return "Articulo [medio=" + medio + ", getFechaPublicacion()=" + getFechaPublicacion() + ", getTitulo()="
				+ getTitulo() + "]";
	}
}
