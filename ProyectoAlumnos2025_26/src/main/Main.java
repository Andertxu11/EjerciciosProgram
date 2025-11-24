package main;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Alumno;
import modelo.Ciclo;
import utilidades.Util;

public class Main {
	
	public static void main(String[] args) {
		Alumno[] alumnos = new Alumno[100];
		int opcion;
		
		do {
		menu();
		
		switch (opcion = Util.leerInt("Introduce una opcion: ")) {
		
		case 1: // matricular alumno
			MatricularAlumno(alumnos);
			break;
		case 2: // listar alumnos
			ListarAlumnos(alumnos);
			break;
		case 3:	// listar informacion alumno
			ListarInformacionAlumno(alumnos);
			break;
		case 4: // modificar informacion alumno
			ModificarInformacionAlumno(alumnos);
		    break;
		case 5: // modificar el  campo repetidor de los alumnos de DAW con una edad determinada
			ModificarCampoRepetidor(alumnos);
			break;
		case 6: // dar de baja alumno
			DarDeBajaAlumno(alumnos);
			break;
		case 7: // salir
			System.out.println("Saliendo...");
			break;
		default:
			System.out.println("Opcion no valida");
			break;		
		}
		} while (opcion != 7);
	}

	private static void DarDeBajaAlumno(Alumno[] alumnos) {

	    String nif = Util.introducirCadena("Introduce el NIF del alumno que deseas dar de baja: ");

	    // Guardar posiciones donde está ese alumno
	    ArrayList<Integer> posiciones = new ArrayList<>();

	    for (int i = 0; i < alumnos.length; i++) {
	        if (alumnos[i] != null && alumnos[i].getNif().equalsIgnoreCase(nif)) {
	            posiciones.add(i);
	        }
	    }

	    if (posiciones.isEmpty()) {
	        System.out.println("⚠ No existe ningún alumno con ese NIF.");
	        return;
	    }

	    System.out.println("\nAlumno encontrado. Información de la(s) matrícula(s):\n");

	    for (int idx : posiciones) {
	        mostrarFicha(alumnos[idx]);
	    }

	    String confirmar = Util.introducirCadena("¿Deseas eliminar todas sus matrículas? (s/n): ");

	    if (!confirmar.equalsIgnoreCase("s")) {
	        System.out.println("❌ Baja cancelada.");
	        return;
	    }

	    // Borrar los alumnos encontrados
	    for (int idx : posiciones) {
	        alumnos[idx] = null;
	    }

	    System.out.println("✔ Alumno/a dado de baja correctamente.\n");
	}

	private static void ModificarCampoRepetidor(Alumno[] alumnos) {

	    int edadBuscada = Util.leerInt("Introduce la edad de los alumnos de DAW que deseas modificar: ");
	    boolean encontrado = false;

	    for (Alumno a : alumnos) {
	        if (a != null && a.getCiclo() == Ciclo.DAW) {

	            int edad = java.time.Period.between(a.getFechaNac(), LocalDate.now()).getYears();

	            if (edad == edadBuscada) {
	                encontrado = true;

	                System.out.println("\nAlumno encontrado:");
	                mostrarFicha(a);

	                String resp = Util.introducirCadena("¿Es repetidor? (s/n): ");
	                boolean repetidor = resp.equalsIgnoreCase("s");

	                a.setRepetidos(repetidor);

	                System.out.println("✔ Campo repetidor actualizado.");
	            }
	        }
	    }

	    if (!encontrado) {
	        System.out.println("⚠ No existen alumnos de DAW con esa edad.");
	    }
	}

	private static void ModificarInformacionAlumno(Alumno[] alumnos) {

	    String nif = Util.introducirCadena("Introduce el NIF del alumno que deseas modificar: ");

	    // Buscar coincidencias
	    ArrayList<Alumno> encontrados = new ArrayList<>();

	    for (Alumno a : alumnos) {
	        if (a != null && a.getNif().equalsIgnoreCase(nif)) {
	            encontrados.add(a);
	        }
	    }

	    if (encontrados.isEmpty()) {
	        System.out.println("⚠ No existe ningún alumno con ese NIF.");
	        return;
	    }

	    // Mostrar datos actuales del alumno
	    System.out.println("\nAlumno encontrado. Datos actuales:");
	    mostrarFicha(encontrados.get(0));  // La ficha sirve, incluye ciclo, pero NO lo cambiaremos

	    System.out.println("\nIntroduce los nuevos datos (deja vacío para no cambiar):");

	    // Nombre
	    String nuevoNombre = Util.introducirCadena("Nuevo nombre: ");
	    if (!nuevoNombre.trim().isEmpty()) {
	        for (Alumno a : encontrados) {
	            a.setNombre(nuevoNombre);
	        }
	    }

	    // Fecha de nacimiento
	    String cambiarFecha = Util.introducirCadena("¿Quieres modificar la fecha de nacimiento? (s/n): ");

	    if (cambiarFecha.equalsIgnoreCase("s")) {
	        int año = Util.leerInt("Año nacimiento: ");
	        int mes = Util.leerInt("Mes nacimiento: ");
	        int dia = Util.leerInt("Día nacimiento: ");

	        LocalDate nuevaFecha = LocalDate.of(año, mes, dia);

	        for (Alumno a : encontrados) {
	            a.setFechaNac(nuevaFecha);
	        }
	    }

	    // Campo repetidor
	    String cambiarRepetidor = Util.introducirCadena("¿Es repetidor? (s/n): ");
	    boolean repetidor = cambiarRepetidor.equalsIgnoreCase("s");

	    for (Alumno a : encontrados) {
	        a.setRepetidos(repetidor);
	    }

	    System.out.println("\n✔ Datos modificados correctamente.\n");
	}

	private static void ListarInformacionAlumno(Alumno[] alumnos) {

	    boolean hayAlumnos = false;

	    System.out.println("\n=======================================");
	    System.out.println("       INFORMACIÓN DE LOS ALUMNOS");
	    System.out.println("=======================================\n");

	    // Mostrar DAM
	    System.out.println("📘 ALUMNOS DE DAM:\n");
	    for (Alumno a : alumnos) {
	        if (a != null && a.getCiclo() == Ciclo.DAM) {
	            hayAlumnos = true;
	            mostrarFicha(a);
	        }
	    }
	    if (!hayAlumnos) System.out.println("   (ninguno)\n");

	    // Reiniciamos para DAW
	    hayAlumnos = false;

	    System.out.println("📗 ALUMNOS DE DAW:\n");
	    for (Alumno a : alumnos) {
	        if (a != null && a.getCiclo() == Ciclo.DAW) {
	            hayAlumnos = true;
	            mostrarFicha(a);
	        }
	    }
	    if (!hayAlumnos) System.out.println("   (ninguno)\n");

	    System.out.println("=======================================\n");
	}
	
	private static void mostrarFicha(Alumno a) {
	    int edad = java.time.Period.between(a.getFechaNac(), LocalDate.now()).getYears();

	    System.out.println("   ---------------------------");
	    System.out.println("   Nombre:     " + a.getNombre());
	    System.out.println("   NIF:        " + a.getNif());
	    System.out.println("   Fecha nac.: " + a.getFechaNac());
	    System.out.println("   Edad:       " + edad + " años");
	    System.out.println("   Ciclo:      " + a.getCiclo());
	    System.out.println("   Repetidor:  " + (a.isRepetidos() ? "Sí" : "No"));
	    System.out.println("   ---------------------------\n");
	}

	private static void ListarAlumnos(Alumno[] alumnos) {

	    boolean hayAlumnos = false;

	    System.out.println("\n=======================================");
	    System.out.println("           LISTADO DE ALUMNOS");
	    System.out.println("=======================================\n");

	    // Listas para evitar duplicados
	    ArrayList<String> dam = new ArrayList<>();
	    ArrayList<String> daw = new ArrayList<>();

	    for (Alumno a : alumnos) {
	        if (a != null) {
	            hayAlumnos = true;

	            if (a.getCiclo() == Ciclo.DAM) {
	                if (!dam.contains(a.getNombre())) {
	                    dam.add(a.getNombre());
	                }
	            } else if (a.getCiclo() == Ciclo.DAW) {
	                if (!daw.contains(a.getNombre())) {
	                    daw.add(a.getNombre());
	                }
	            }
	        }
	    }

	    if (!hayAlumnos) {
	        System.out.println("⚠ No hay alumnos matriculados.\n");
	        return;
	    }

	    // Mostrar DAM
	    System.out.println("📘 ALUMNOS MATRÍCULADOS EN DAM");
	    if (dam.isEmpty()) {
	        System.out.println("   (ninguno)");
	    } else {
	        for (String nombre : dam) {
	            System.out.println("   - " + nombre);
	        }
	    }

	    System.out.println("\n📗 ALUMNOS MATRÍCULADOS EN DAW");
	    if (daw.isEmpty()) {
	        System.out.println("   (ninguno)");
	    } else {
	        for (String nombre : daw) {
	            System.out.println("   - " + nombre);
	        }
	    }

	    System.out.println("\n=======================================\n");
	}

	private static void MatricularAlumno(Alumno[] alumnos) {
	    char continuar;

	    do {
	        String nif = Util.introducirCadena("Introduce el NIF del alumno: ");

	        // Comprobar cuántas veces está matriculado ya
	        int count = 0;
	        Ciclo cicloYaMatriculado = null;

	        for (Alumno a : alumnos) {
	            if (a != null && a.getNif().equalsIgnoreCase(nif)) {
	                count++;
	                cicloYaMatriculado = a.getCiclo();
	            }
	        }

	        if (count == 2) {
	            System.out.println("El alumno/a ya está matriculado en DAM y DAW.");
	        } else {
	            Ciclo nuevoCiclo;

	            if (count == 1) {
	                // Ya está en un ciclo, asignar el otro automáticamente
	                if (cicloYaMatriculado == Ciclo.DAM) {
	                    nuevoCiclo = Ciclo.DAW;
	                    System.out.println("El alumno ya estaba en DAM, ahora se matriculará en DAW.");
	                } else {
	                    nuevoCiclo = Ciclo.DAM;
	                    System.out.println("El alumno ya estaba en DAW, ahora se matriculará en DAM.");
	                }

	            } else {
	                // No está matriculado → pedir ciclo
	                int opcionCiclo;
	                do {
	                    System.out.println("Selecciona ciclo: 1-DAM | 2-DAW");
	                    opcionCiclo = Util.leerInt("Opción: ");
	                } while (opcionCiclo != 1 && opcionCiclo != 2);

	                nuevoCiclo = (opcionCiclo == 1) ? Ciclo.DAM : Ciclo.DAW;
	            }

	            // Si no estaba matriculado antes, pedir nombre y fecha
	            String nombre = "";
	            LocalDate fechaNac = null;

	            if (count == 0) {
	                nombre = Util.introducirCadena("Introduce el nombre: ");
	                int año = Util.leerInt("Año nacimiento: ");
	                int mes = Util.leerInt("Mes nacimiento: ");
	                int dia = Util.leerInt("Día nacimiento: ");
	                fechaNac = LocalDate.of(año, mes, dia);
	            } else {
	                // Si ya estaba matriculado, usamos los datos existentes
	                for (Alumno a : alumnos) {
	                    if (a != null && a.getNif().equalsIgnoreCase(nif)) {
	                        nombre = a.getNombre();
	                        fechaNac = a.getFechaNac();
	                        break;
	                    }
	                }
	            }

	            // Crear nuevo alumno (segunda inscripción si ya existía uno)
	            Alumno nuevo = new Alumno(nif, nombre, fechaNac, nuevoCiclo, false);

	            // Insertarlo en el array
	            for (int i = 0; i < alumnos.length; i++) {
	                if (alumnos[i] == null) {
	                    alumnos[i] = nuevo;
	                    break;
	                }
	            }

	            System.out.println("Alumno matriculado correctamente en " + nuevoCiclo);
	        }

	        continuar = Util.leerChar("¿Desea introducir otro alumno? (s/n): ");

	    } while (continuar == 's' || continuar == 'S');
	}

	private static void menu() {
		System.out.println("----- MENU -----");
		System.out.println("1. matricular alumno: ");
		System.out.println("2. listar alumnos: ");
		System.out.println("3. listar informacion alumno: ");
		System.out.println("4. Modificar informacion alumno: ");
		System.out.println("5. Modificar el  campo repetidor de los alumnos de DAW con una edad determinada: ");
		System.out.println("6. Dar de baja alumno: ");
		System.out.println("7. Salir");
	}
}