package modelo;

public class Socio {

    public static final String NOMBRE_BIBLIOTECA = "Lectura Viva";
    public static final double CUOTA_BASE = 12.0;

    protected String dni;
    protected String nombreCompleto;
    protected int mesAlta;
    protected int anioAlta;
    protected int librosPermitidos;

    public Socio(String dni, String nombreCompleto, int mesAlta, int anioAlta, int librosPermitidos) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.mesAlta = mesAlta;
        this.anioAlta = anioAlta;
        this.librosPermitidos = librosPermitidos;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombreCompleto; }
    public int getAnioAlta() { return anioAlta; }
    public int getMesAlta() { return mesAlta; }

    public boolean esBibliotecario() {
        return false;
    }

    public int getAntiguedad() {
        return 2025 - anioAlta;
    }

    public double getCuotaFinal() {
        double extra = 0;
        if (librosPermitidos > 3)
            extra = librosPermitidos - 3;

        double total = CUOTA_BASE + extra;

        if (getAntiguedad() >= 8)
            total -= 2;

        return total;
    }

    @Override
    public String toString() {
        return "Socio: " + nombreCompleto +
                " | DNI: " + dni +
                " | Alta: " + mesAlta + "/" + anioAlta +
                " | Libros permitidos: " + librosPermitidos +
                " | Cuota final: " + getCuotaFinal() + "€";
    }
}