package modelo;

import java.util.ArrayList;
import java.util.List;

public class Profesor {

	// atributos
	private String gmail;
	private String nombre;
	private Departamento nombreDept;
	private List<Publicacion> publicaciones;
	
	// constructor
	public Profesor() {
		
	}
	
	public Profesor(String gmail, String nombre, Departamento nombreDept, List<Publicacion> publicaciones) {
	    this.gmail = gmail;
	    this.nombre = nombre;
	    this.nombreDept = nombreDept;
	    this.publicaciones = (publicaciones != null) ? publicaciones : new ArrayList<>();
	}
	
	// getter y setter
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Departamento getNombreDept() {
		return nombreDept;
	}
	public void setNombreDept(Departamento nombreDept) {
		this.nombreDept = nombreDept;
	}
	public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
	
	// toString
	@Override
	public String toString() {
		return "Profesor [gmail=" + gmail + ", nombre=" + nombre + ", nombreDept=" + nombreDept + "]";
	}
}
