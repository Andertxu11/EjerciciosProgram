package modelo;

import java.time.LocalDate;

public class Alumno {
	
	// variables
	private String nif;
	private String nombre;
	private LocalDate fechaNac;
	private Ciclo ciclo;
	private boolean repetidos = false;
	
	// constructor
	public Alumno() {
		
	}
	
	public Alumno(String nif, String nombre, LocalDate fechaNac, Ciclo ciclo, boolean repetidos) {
		this.nif = nif;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.ciclo = ciclo;
		this.repetidos = repetidos;
	}
	
	// getters and setters
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Ciclo getCiclo() {
		return ciclo;
	}
	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}
	public boolean isRepetidos() {
		return repetidos;
	}
	public void setRepetidos(boolean repetidos) {
		this.repetidos = repetidos;
	}
	
	// toString
	@Override
	public String toString() {
		return "Alumno [nif=" + nif + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", ciclo=" + ciclo
				+ ", repetidos=" + repetidos + "]";
	}
	
}
