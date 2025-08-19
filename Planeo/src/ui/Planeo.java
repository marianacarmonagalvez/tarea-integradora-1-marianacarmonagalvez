package ui;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Planeo {

    private Scanner sc;
    private boolean cursoRegistrado = false; // Si se coloca en el método se "borra" cada q se utiliza pq siempre estaría en false, entonces toca definirla como atributo de la clase

    private String codigoCurso, nombreCurso, profesorAsignado, salonEspecifico;
    private int numeroCreditos;

    // Formato de fecha (con ayuda de IA)
    private final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Arreglos (todos de 5 pq son máximo 5 actividades)
    private String[] nombresActividades = new String[5];
    private LocalDate[] fechasActividades = new LocalDate[5];
    private double[] porcentajesActividades = new double[5];
    private Double[] notasActividades = new Double[5]; // null = sin nota
    private int contadorActividades = 0;
    private double porcentajeTotal = 0;

    public static void main(String[] args) {
        Planeo run = new Planeo();
        run.init();
    }

    public void init() {
        this.sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Registrar curso");
            System.out.println("2. Asociar actividades a curso");
            System.out.println("3. Cargar notas");
            System.out.println("4. Visualizar actividades");
            System.out.println("5. Consultar promedio del curso");
            System.out.println("6. Salir");
            System.out.println("Ingrese una opción: ");
            int opcion = this.sc.nextInt();

            if (opcion == 1) {
                System.out.println("Registrar curso seleccionado");
                registrarCurso();
            } else if (opcion == 2) {
                System.out.println("Asociar actividades a curso seleccionado");
                asociarActividad();
            } else if (opcion == 3) {
                System.out.println("Cargar notas seleccionado");
                cargarNotas();
            } else if (opcion == 4) {
                System.out.println("Visualizar actividades seleccionado");
                visualizarActividades();
            } else if (opcion == 5) {
                System.out.println("Consultar promedio del curso seleccionado");
                consultarPromedioCurso();
            } else if (opcion == 6) {
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Opción no válida");
            }
        }
    }

    /**
     * Registra un único curso: código, nombre, créditos, profesor y salón.
     * Si ya existe un curso registrado, no permite registrar otro.
     */
    public void registrarCurso() {
        if (cursoRegistrado) {
            System.out.println("Ya existe un curso registrado. No es posible registrar más."); 
            return; 
        }

        System.out.println("Ingrese el código del curso que desea registrar: ");
        codigoCurso = this.sc.nextLine();

        System.out.print("Nombre del curso: ");
        nombreCurso = sc.nextLine();

        System.out.print("Número de créditos: ");
        numeroCreditos = sc.nextInt();

        System.out.print("Nombre del profesor: ");
        profesorAsignado = sc.nextLine();

        System.out.print("Salón: ");
        salonEspecifico = sc.nextLine();

        cursoRegistrado = true;
        System.out.println("Curso registrado con éxito.");
    }

    /**
     * Asocia una actividad (nombre, fecha, porcentaje) hasta un máximo de 5.
     * Valida que la suma de porcentajes no supere 100%.
     */
    public void asociarActividad() {
        if (!cursoRegistrado) { 
            System.out.println("Primero registre un curso");
            return;
        }
        if (contadorActividades >= 5) {
            System.out.println("Ya hay 5 actividades");
            return;
        }
    
        System.out.print("Nombre de la actividad: ");
        nombresActividades[contadorActividades] = sc.nextLine();
    
        System.out.print("Fecha (dd/MM/yyyy): ");
        fechasActividades[contadorActividades] = LocalDate.parse(sc.nextLine(), FMT); // LocalDate.parse convierte lo que el usuario escribió a un objeto de fecha (IA)
    
        System.out.print("Porcentaje: ");
        double porcentaje = sc.nextDouble(); 
        sc.nextLine(); // Limpia el buffer
    
        if (porcentajeTotal + porcentaje > 100) {
            System.out.println("No se puede pasar de 100%");
            return;
        }
    
        porcentajesActividades[contadorActividades] = porcentaje; // Guarda el porcentaje en el arreglo
        porcentajeTotal += porcentaje; // Suma ese porcentaje en el total
        contadorActividades = contadorActividades + 1; // Contador de actividades
        System.out.println("Actividad registrada");
    }

    /**
     * Carga la nota (0.0 a 5.0) para una actividad seleccionada por índice.
     */
    public void cargarNotas() {
        if (contadorActividades == 0) {
            System.out.println("No hay actividades");
            return;
        }
    
        System.out.print("Número de actividad (1-" + contadorActividades + "): ");
        int n = sc.nextInt(); sc.nextLine();
    
        if (n < 1 || n > contadorActividades) {
            System.out.println("Actividad inválida");
            return;
        }
    
        System.out.print("Nota (0.0 a 5.0): ");
        double nota = sc.nextDouble(); sc.nextLine();
    
        if (nota < 0.0 || nota > 5.0) {
            System.out.println("Nota fuera de rango");
            return;
        }
    
        notasActividades[n - 1] = nota;
        System.out.println("Nota registrada");
    }

    /**
     * Muestra todas las actividades: nombre, fecha, porcentaje y nota (o Pendiente).
     */
    public void visualizarActividades() {
     
    }

    /**
     * Calcula y muestra el promedio ponderado del curso.
     * Requiere que el total de porcentajes sea exactamente 100%.
     */
    public void consultarPromedioCurso() {
     
    }

}
