
package main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Articulo;
import modelo.Departamento;
import modelo.Libro;
import modelo.Profesor;
import modelo.Publicacion;
import utilidades.Util;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Profesor> profesor = new HashMap<>();
        int opcion;

        do {
            menu();

            switch (opcion = Util.leerInt("Seleccione una opción: ")) {
                case 1:
                    aniadirProfesor(profesor);
                    break;
                case 2:
                    aniadirPublicacion(profesor);
                    break;
                case 3:
                    mostrarLibrosPremiados(profesor);
                    break;
                case 4:
                    listadoPorDepartamento(profesor);
                    break;
                case 5:
                    listarProfesores(profesor);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }

        } while (opcion != 0);
    }

private static void añadirArticulo(Profesor prof) {
    String titulo = Util.introducirCadena("Introduce el título del artículo: ");

    LocalDate fechaPublicacion = null;
    while (fechaPublicacion == null) {
        String fecha = Util.introducirCadena("Introduce la fecha de publicación (YYYY-MM-DD): ");
        try {
            fechaPublicacion = LocalDate.parse(fecha);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Inténtelo de nuevo.");
        }
    }

    String medio = Util.introducirCadena("Introduce el medio donde se publicó el artículo: ");
    Articulo articulo = new Articulo(fechaPublicacion, titulo, medio);
    prof.getPublicaciones().add(articulo);
    System.out.println("Artículo añadido.");
}

	 private static void añadirLibro(Profesor prof) {
		    String titulo = Util.introducirCadena("Introduce el título del libro: ");

		    LocalDate fechaPublicacion = null;
		    while (fechaPublicacion == null) {
		        String fecha = Util.introducirCadena("Introduce la fecha de publicación (YYYY-MM-DD): ");
		        try {
		            fechaPublicacion = LocalDate.parse(fecha);
		        } catch (Exception e) {
		            System.out.println("Formato de fecha incorrecto. Inténtelo de nuevo.");
		        }
		    }

		    String isbn = Util.introducirCadena("Introduce el ISBN: ");

		    Boolean premiado = null;
		    while (premiado == null) {
		        String resp = Util.introducirCadena("¿Es un libro premiado? (s/n): ").trim().toLowerCase();
		        if (resp.equals("s")) {
		            premiado = true;
		        } else if (resp.equals("n")) {
		            premiado = false;
		        } else {
		            System.out.println("Opción no válida. Escriba 's' o 'n'.");
		        }
		    }

		    Libro libro = new Libro(fechaPublicacion, titulo, isbn, premiado);
		    prof.getPublicaciones().add(libro);
		    System.out.println("Libro añadido.");
		}

    private static void listarProfesores(HashMap<String, Profesor> profesor) {
        for (Profesor prof : profesor.values()) {
            System.out.println(prof);
        }
    }

private static void listadoPorDepartamento(HashMap<String, Profesor> profesores) {
    // Agrupar profesores por departamento
    HashMap<Departamento, List<Profesor>> porDepto = new HashMap<>();
    for (Profesor prof : profesores.values()) {
        porDepto.computeIfAbsent(prof.getNombreDept(), k -> new java.util.ArrayList<>()).add(prof);
    }

    for (Departamento dept : porDepto.keySet()) {
        List<Profesor> lista = porDepto.get(dept);
        int max = lista.stream()
                .mapToInt(p -> p.getPublicaciones().size())
                .max()
                .orElse(0);

        // Filtrar los profesores con ese máximo
        List<Profesor> top = lista.stream()
                .filter(p -> p.getPublicaciones().size() == max)
                .toList();

        System.out.println("Departamento: " + dept);
        if (max == 0) {
            System.out.println("  Ningún profesor tiene publicaciones.");
        } else {
            System.out.println("  Profesor(es) con más publicaciones (" + max + "):");
            for (Profesor p : top) {
                System.out.println("    - " + p.getNombre());
            }
        }
    }
}

    private static void mostrarLibrosPremiados(HashMap<String, Profesor> profesores) {
        int anio = Util.leerInt("Introduce el año a consultar: ");
        boolean encontrado = false;

        for (Profesor prof : profesores.values()) {
            for (Publicacion pub : prof.getPublicaciones()) {
                if (pub instanceof Libro) {
                    Libro libro = (Libro) pub;
                    if (libro.isEsPremiado() && libro.getFechaPublicacion().getYear() == anio) {
                        if (!encontrado) {
                            System.out.println("Libros premiados en " + anio + ":");
                            encontrado = true;
                        }
                        System.out.println("- " + libro + " (Profesor: " + prof.getNombre() + ")");
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron libros premiados para ese año.");
        }
    }

    private static void aniadirPublicacion(HashMap<String, Profesor> profesores) {
        String gmail;
        Profesor prof = null;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9.-]+\\.)+[a-zA-Z]{2,}$");

        while (true) {
            gmail = Util.introducirCadena("Introduce el gmail del profesor: ");
            Matcher matcher = pattern.matcher(gmail);

            if (!matcher.matches()) {
                System.out.println("Formato de gmail no válido. Inténtelo de nuevo.");
                continue;
            }
            if (!profesores.containsKey(gmail)) {
                System.out.println("No existe ningún profesor con ese email.");
                return;
            }
            prof = profesores.get(gmail);
            break;
        }

        System.out.println("Profesor encontrado:");
        System.out.println(prof);

        boolean continuar;
        do {
            int opcion = 0;
            // Validar opción de publicación
            while (opcion != 1 && opcion != 2) {
                System.out.println("\n¿Qué tipo de publicación desea añadir?");
                System.out.println("1. Libro");
                System.out.println("2. Artículo");
                opcion = Util.leerInt("Elija opción (1/2): ");
                if (opcion != 1 && opcion != 2) {
                    System.out.println("Opción no válida.");
                }
            }

            if (opcion == 1) {
                añadirLibro(prof);
            } else {
                añadirArticulo(prof);
            }

            System.out.println("¿Desea añadir otra publicación? (s/n)");
            continuar = Util.leerRespuesta("");
        } while (continuar);

        System.out.println("Publicaciones añadidas correctamente.");
    }

    private static void aniadirProfesor(HashMap<String, Profesor> profesor) {
        String gmail = "";
        String nombre;
        Departamento nombreDept = null;
        List<Publicacion> publicaciones = null;
        String departamento;
        boolean continuar;

        do {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@([a-zA-Z0-9.-]+\\.)+[a-zA-Z]{2,}$");
            boolean gmailValido = false;

            while (!gmailValido) {
                gmail = Util.introducirCadena("Introduce el gmail del profesor: ");
                Matcher matcher = pattern.matcher(gmail);

                if (matcher.matches()) {
                    if (profesor.containsKey(gmail)) {
                        System.out.println("El profesor con gmail " + gmail + " ya existe.");
                        return;
                    }
                    gmailValido = true;
                } else {
                    System.out.println("Formato de gmail no válido. Inténtelo de nuevo.");
                }
            }

            nombre = Util.introducirCadena("Introduce el nombre del profesor: ");

            boolean deptValido = false;
            while (!deptValido) {
                departamento = Util.introducirCadena("Introduce el nombre del departamento del profesor: ");
                try {
                    nombreDept = Departamento.valueOf(departamento.toUpperCase());
                    deptValido = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Departamento no válido. Debe ser uno de los siguientes:");
                    for (Departamento d : Departamento.values()) {
                        System.out.println("- " + d);
                    }
                }
            }

            Profesor nuevoProfesor = new Profesor(gmail, nombre, nombreDept, publicaciones);
            profesor.put(gmail, nuevoProfesor);

            System.out.println("Profesor añadido correctamente.");
            System.out.println("¿Desea añadir otro profesor? (s/n)");
            continuar = Util.leerRespuesta("");
        } while (continuar);
    }

    private static void menu() {
        System.out.println("----- MENÚ -----");
        System.out.println("1. Añadir profesor");
        System.out.println("2. Añadir publicación");
        System.out.println("3. Mostrar los libros galardonados de un año concreto");
        System.out.println("4. Mostrar un listado por departamento de los profesores con mayor número de publicaciones");
        System.out.println("5. Listado de los profes");
        System.out.println("0. Salir");
    }
}
