package modelo;

public class Bibliotecario extends Socio {

    public static final double PLUS = 30.0;

    private Seccion seccion;

    public Bibliotecario(String dni, String nombreCompleto,
                         int mesAlta, int anioAlta,
                         int librosPermitidos, Seccion seccion) {

        super(dni, nombreCompleto, mesAlta, anioAlta, librosPermitidos);
        this.seccion = seccion;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    @Override
    public boolean esBibliotecario() {
        return true;
    }

    @Override
    public double getCuotaFinal() {
        return super.getCuotaFinal() + PLUS;
    }

    @Override
    public String toString() {
        return "Bibliotecario: " + nombreCompleto +
                " | DNI: " + dni +
                " | Sección: " + seccion +
                " | Alta: " + mesAlta + "/" + anioAlta +
                " | Libros permitidos: " + librosPermitidos +
                " | Cuota final: " + getCuotaFinal() + "€";
    }
}