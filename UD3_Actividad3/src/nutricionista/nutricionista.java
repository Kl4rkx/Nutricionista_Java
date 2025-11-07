package nutricionista; // Declaración del paquete donde se encuentra la clase

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.text.NumberFormat;
import java.text.ParseException;

public class nutricionista {

    public static void main(String[] args) { 
    	// Declaración de variables
        int opcion = 0; 
        Scanner sc = new Scanner(System.in);
        String linea = null;
        String[] campos = new String[0]; // Array para almacenar campos individuales de la línea
        String[] arrayNombre = new String[0];
        String[] arrayEstado = new String[0];
        double[] arrayCalorias = new double[0];
        double[] arrayGrasas = new double[0];
        double[] arrayProteinas = new double[0];
        double[] arrayCarbohidratos = new double[0];
        String[] arrayTipos = new String[0];

        // Archivo de texto que contiene los datos de los alimentos
        File file = new File("./src/nutricionista/janariak.txt");
        try {
            int i = 0;
            Scanner scFile1 = new Scanner(file); // Inicializamos un scanner para leer el archivo

            // Contamos cuántas líneas hay en el archivo
            while (scFile1.hasNextLine()) {
                linea = scFile1.nextLine(); // Leemos cada línea del archivo
                i++;
            }
            scFile1.close(); // Cerramos el scanner del archivo

            // Inicializamos los arrays con el tamaño dependiendo el numero de lineas
            campos = new String[i];
            arrayNombre = new String[i];
            arrayEstado = new String[i];
            arrayCalorias = new double[i];
            arrayGrasas = new double[i];
            arrayProteinas = new double[i];
            arrayCarbohidratos = new double[i];
            arrayTipos = new String[i];

            // Volvemos a leer el archivo para almacenar los datos en los arrays
            Scanner scFile = new Scanner(file);
            i = 0; // Reiniciamos el contador

            // Leemos los datos y los almacenamos en los arrays
            while (scFile.hasNext()) {
                linea = scFile.nextLine(); // Leemos cada línea
                campos = linea.split(";"); // Dividimos la línea en campos separados por punto y coma
                arrayNombre[i] = campos[0]; // Almacenamos el nombre del alimento
                arrayEstado[i] = campos[1]; // Almacenamos el estado del alimento
                // Convertimos los datos nutricionales a doble y los almacenamos
                // Con NumberFomat para parsear, tiene en cuenta el país y convierte los "." en ","
                arrayCalorias[i] = NumberFormat.getInstance().parse(campos[2]).doubleValue();
                arrayGrasas[i] = NumberFormat.getInstance().parse(campos[3]).doubleValue();
                arrayProteinas[i] = NumberFormat.getInstance().parse(campos[4]).doubleValue();
                arrayCarbohidratos[i] = NumberFormat.getInstance().parse(campos[5]).doubleValue();
                arrayTipos[i] = campos[6];
                i++;
            }

            scFile.close();
        } catch (FileNotFoundException e) { // Manejo de excepción si el archivo no se encuentra
            e.printStackTrace(); 
        } catch (ParseException e) { // Manejo de excepción si no se puede parsear el número
            e.printStackTrace();
        }

        // Bucle para mostrar el menú de opciones
        do {
            System.out.println("-------------------------");
            System.out.println("Selecciona una opción");
            System.out.println("-------------------------");
            System.out.println("0. Salir");
            System.out.println("1. Dado un tipo de alimento, mostrar todos los alimentos de ese tipo.");
            System.out.println("2. Dado un tipo de alimento, mostrar la media de calorías de los alimentos de ese tipo.");
            System.out.println("3. Dado un tipo de alimento, mostrar la cantidad de ese tipo de alimentos.");
            System.out.println("4. Dado el alimento y el estado, mostrar sus valores nutricionales.");
            System.out.println("5. Dado el alimento, mostrar los valores nutricionales según el estado.");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            opcion = Integer.parseInt(sc.nextLine()); // Leemos la opción elegida por el usuario
            // Switch para ejecutar la función correspondiente a la opción elegida
            switch (opcion) {
                case 1:
                    primeraOpcion(sc, arrayTipos, arrayNombre, arrayEstado); // Mostrar alimentos de un tipo
                    break;
                case 2:
                    segundaOpcion(sc, arrayTipos, arrayNombre, arrayEstado, arrayCalorias); // Mostrar media de calorías
                    break;
                case 3:
                    terceraOpcion(sc, arrayTipos, arrayNombre); // Mostrar cantidad de un tipo de alimento
                    break;
                case 4:
                    cuartaOpcion(sc, arrayNombre, arrayEstado, arrayCalorias, arrayGrasas, arrayProteinas, arrayCarbohidratos); // Mostrar valores nutricionales por alimento y estado
                    break;
                case 5:
                    quintaOpcion(sc, arrayNombre, arrayEstado, arrayCalorias, arrayGrasas, arrayProteinas, arrayCarbohidratos); // Mostrar valores nutricionales de un alimento según estado
                    break;
            }
        } while (opcion != 0);
        System.out.println("Fin del programa."); 
        sc.close(); // Cerramos el scanner de entrada
    }

    // Método para mostrar todos los alimentos de un tipo
    public static void primeraOpcion(Scanner sc, String[] arrayTipos, String[] arrayNombre, String[] arrayEstado) {
        System.out.println("1. Dado un tipo de alimento, mostrar todos los alimentos de ese tipo.");
        System.out.println("Introduce un tipo de alimento: ");
        String input = sc.nextLine(); // Leemos el tipo de alimento introducido

        // Buscamos en el array y mostramos los alimentos que coinciden
        for (int i = 0; i < arrayNombre.length; i++) {
            if (arrayTipos[i].equalsIgnoreCase(input)) {
                System.out.println(arrayNombre[i] + " " + arrayEstado[i]); // Mostramos el nombre y estado del alimento
            }
        }
    }

    // Método para mostrar la media de calorías de los alimentos de un tipo
    public static void segundaOpcion(Scanner sc, String[] arrayTipos, String[] arrayNombre, String[] arrayEstado,
            double[] arrayCalorias) {
        double suma = 0; 
        double media = 0; 
        int contador = 0; 
        int indice = 0; 

        System.out.println("2. Dado un tipo de alimento, mostrar la media de calorías de los alimentos de ese tipo.");
        System.out.println("Introduce un tipo de alimento: ");
        String input = sc.nextLine(); // Leemos el tipo de alimento introducido

        // Buscamos las calorías de los alimentos de ese tipo
        for (int i = 0; i < arrayNombre.length; i++) {
            if (arrayTipos[i].equalsIgnoreCase(input)) {
                suma += arrayCalorias[i]; // Sumar las calorías
                indice = i; // Guardamos el índice del último alimento encontrado
                contador++; 
            }
        }

        // Calculamos y mostramos la media
        if (contador > 0) {
            media = suma / contador;
            System.out.println("La media de calorías de los alimentos del tipo: " + arrayTipos[indice] + " es " + media);
        } else {
            System.out.println("No se encontraron alimentos de ese tipo."); // Mensaje si no hay alimentos
        }
    }

    // Método para mostrar la cantidad de un tipo de alimento
    public static void terceraOpcion(Scanner sc, String[] arrayTipos, String[] arrayNombre) {
        int contador = 0; 
        int indice = 0; 

        System.out.println("3. Dado un tipo de alimento, mostrar la cantidad de ese tipo de alimentos.");
        System.out.println("Introduce un tipo de alimento: ");
        String input = sc.nextLine(); // Leemos el tipo de alimento introducido

        // Buscamos en el array y contamos los alimentos que coinciden
        for (int i = 0; i < arrayNombre.length; i++) {
            if (arrayTipos[i].equalsIgnoreCase(input)) {
                contador++; // Incrementamos el contador
                indice = i; // Guardamos el índice del último alimento encontrado
            }
        }

        // Mostramos la cantidad
        System.out.println("La cantidad de alimentos de tipo: " + arrayTipos[indice] + " es " + contador);
    }

    // Método para mostrar valores nutricionales por alimento y estado
    public static void cuartaOpcion(Scanner sc, String[] arrayNombre, String[] arrayEstado, double[] arrayCalorias,
            double[] arrayGrasas, double[] arrayProteinas, double[] arrayCarbohidratos) {
        String inputAlimento = ""; // Alimento introducido
        String inputEstado = ""; // Estado introducido
        System.out.println("4. Dado el alimento y el estado, mostrar sus valores nutricionales.");
        System.out.println("Introduce el nombre del alimento: ");
        inputAlimento = sc.nextLine().toLowerCase(); // Leemos y convertimos a minúsculas
        System.out.println("Introduce el estado del alimento: ");
        inputEstado = sc.nextLine().toLowerCase(); // Leemos y convertimos a minúsculas

        // Buscamos el alimento y su estado y mostramos sus valores nutricionales
        for (int i = 0; i < arrayNombre.length; i++) {
            if (arrayNombre[i].equalsIgnoreCase(inputAlimento) && arrayEstado[i].equalsIgnoreCase(inputEstado)) {
                System.out.println(" Calorias: " + arrayCalorias[i] + " | Grasas: " + arrayGrasas[i] + " | Proteinas: "
                        + arrayProteinas[i] + " | Carbohidratos:  " + arrayCarbohidratos[i]);
            }
        }
    }

    // Método para mostrar valores nutricionales de un alimento según su estado
    public static void quintaOpcion(Scanner sc, String[] arrayNombre, String[] arrayEstado, double[] arrayCalorias,
            double[] arrayGrasas, double[] arrayProteinas, double[] arrayCarbohidratos) {
        String inputAlimento = ""; // Alimento introducido
        System.out.println("5. Dado el alimento, mostrar los valores nutricionales según el estado.");
        System.out.println("Introduce el nombre del alimento: ");
        inputAlimento = sc.nextLine().toLowerCase(); // Leemos y convertimos a minúsculas

        // Buscamos el alimento y mostramos sus valores nutricionales
        for (int i = 0; i < arrayNombre.length; i++) {
            if (arrayNombre[i].equalsIgnoreCase(inputAlimento)) {
                System.out.println("Estado:  " + arrayEstado[i] + " | Calorias: " + arrayCalorias[i] + " | Grasas: " + arrayGrasas[i] + " | Proteinas: "
                        + arrayProteinas[i] + " | Carbohidratos:  " + arrayCarbohidratos[i]);
            }
        }
    }
}