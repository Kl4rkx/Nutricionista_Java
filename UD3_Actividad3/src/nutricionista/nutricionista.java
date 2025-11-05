package nutricionista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.text.ParseException;

public class nutricionista {

	public static void main(String[] args) {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		String linea = null;
		String[] campos = new String[0];
		String[] arrayNombre = new String[0];
		String[] arrayEstado = new String[0];
		double[] arrayCalorias = new double[0];
		double[] arrayGrasas = new double[0];
		double[] arrayProteinas = new double[0];
		double[] arrayCarbohidratos = new double[0];
		String[] arrayTipos = new String[0];

		File file = new File("./src/nutricionista/janariak.txt");
		try {
			int i = 0;
			Scanner scFile1 = new Scanner(file);

			while (scFile1.hasNextLine()) {
				linea = scFile1.nextLine();
				i++;
			}
			scFile1.close();

			campos = new String[i];
			arrayNombre = new String[i];
			arrayEstado = new String[i];
			arrayCalorias = new double[i];
			arrayGrasas = new double[i];
			arrayProteinas = new double[i];
			arrayCarbohidratos = new double[i];
			arrayTipos = new String[i];

			Scanner scFile = new Scanner(file);
			i = 0;

			while (scFile.hasNext()) {
				linea = scFile.nextLine();
				campos = linea.split(";");
				arrayNombre[i] = campos[0];
				arrayEstado[i] = campos[1];
				arrayCalorias[i] = NumberFormat.getInstance().parse(campos[2]).doubleValue();
				arrayGrasas[i] = NumberFormat.getInstance().parse(campos[3]).doubleValue();
				arrayProteinas[i] = NumberFormat.getInstance().parse(campos[4]).doubleValue();
				arrayCarbohidratos[i] = NumberFormat.getInstance().parse(campos[5]).doubleValue();
				arrayTipos[i] = campos[6];
				i++;
			}

			scFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		do {
			System.out.println("-------------------------");
			System.out.println("Selecciona una opción");
			System.out.println("-------------------------");
			System.out.println("0. Salir");
			System.out.println("1. Dado un tipo de alimento, mostrar todos los alimentos de ese tipo.");
			System.out
					.println("2. Dado un tipo de alimento, mostrar la media de calorías de los alimentos de ese tipo.");
			System.out.println("3. Dado un tipo de alimento, mostrar la cantidad de ese tipo de alimentos.");
			System.out.println("4. Dado el alimento y el estado, mostrar sus valores nutricionales.");
			System.out.println("5. Dado el alimento, mostrar los valores nutricionales según el estado.");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------");
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
			case 1:
				primeraOpcion(sc, arrayTipos, arrayNombre, arrayEstado);
				break;

			case 2:
				segundaOpcion(sc, arrayTipos, arrayNombre, arrayEstado, arrayCalorias);
				break;

			case 3:
				terceraOpcion(sc, arrayTipos, arrayNombre);
				break;

			case 4:
				cuartaOpcion(sc, arrayNombre, arrayEstado, arrayCalorias, arrayGrasas, arrayProteinas,
						arrayCarbohidratos);
				break;

			case 5:
				quintaOpcion(sc, arrayNombre, arrayEstado, arrayCalorias, arrayGrasas, arrayProteinas, arrayCarbohidratos);
				break;

			}
		} while (opcion != 0);
		System.out.println("Fin del programa.");
		sc.close();
	}

	public static void primeraOpcion(Scanner sc, String[] arrayTipos, String[] arrayNombre, String[] arrayEstado) {
		System.out.println("1. Dado un tipo de alimento, mostrar todos los alimentos de ese tipo.");
		System.out.println("Introduce un tipo de alimento: ");
		String input = sc.nextLine();

		for (int i = 0; i < arrayNombre.length; i++) {
			if (arrayTipos[i].equalsIgnoreCase(input)) {
				System.out.println(arrayNombre[i] + " " + arrayEstado[i]);
			}
		}
	}

	public static void segundaOpcion(Scanner sc, String[] arrayTipos, String[] arrayNombre, String[] arrayEstado,
			double[] arrayCalorias) {
		double suma = 0;
		double media = 0;
		int contador = 0;
		int indice = 0;

		System.out.println("2. Dado un tipo de alimento, mostrar la media de calorías de los alimentos de ese tipo.");
		System.out.println("Introduce un tipo de alimento: ");
		String input = sc.nextLine();

		for (int i = 0; i < arrayNombre.length; i++) {
			if (arrayTipos[i].equalsIgnoreCase(input)) {
				suma += arrayCalorias[i];
				indice = i;
				contador++;
			}
		}

		media = suma / contador;

		System.out.println("La media de calorías de los alimentos del tipo: " + arrayTipos[indice] + " es " + media);

	}

	public static void terceraOpcion(Scanner sc, String[] arrayTipos, String[] arrayNombre) {
		int contador = 0;
		int indice = 0;

		System.out.println("3. Dado un tipo de alimento, mostrar la cantidad de ese tipo de alimentos.");
		System.out.println("Introduce un tipo de alimento: ");
		String input = sc.nextLine();

		for (int i = 0; i < arrayNombre.length; i++) {
			if (arrayTipos[i].equalsIgnoreCase(input)) {
				contador++;
				indice = i;
			}
		}

		System.out.println("La cantidad de alimentos de tipo: " + arrayTipos[indice] + " es " + contador);

	}

	public static void cuartaOpcion(Scanner sc, String[] arrayNombre, String[] arrayEstado, double[] arrayCalorias,
			double[] arrayGrasas, double[] arrayProteinas, double[] arrayCarbohidratos) {
		String inputAlimento = "";
		String inputEstado = "";
		System.out.println("4. Dado el alimento y el estado, mostrar sus valores nutricionales.");
		System.out.println("Introduce el nombre del alimento: ");
		inputAlimento = sc.nextLine().toLowerCase();
		System.out.println("Introduce el estado del alimento: ");
		inputEstado = sc.nextLine().toLowerCase();

		for (int i = 0; i < arrayNombre.length; i++) {
			if (arrayNombre[i].equalsIgnoreCase(inputAlimento) && arrayEstado[i].equalsIgnoreCase(inputEstado)) {
				System.out.println(" Calorias: " + arrayCalorias[i] + " | Grasas: " + arrayGrasas[i] + " | Proteinas: "
						+ arrayProteinas[i] + " | Proteinas:  " + arrayCarbohidratos[i]);
			}
		}

	}
	
	public static void quintaOpcion(Scanner sc, String[] arrayNombre, String[] arrayEstado, double[] arrayCalorias,
			double[] arrayGrasas, double[] arrayProteinas, double[] arrayCarbohidratos) {
		String inputAlimento = "";
		System.out.println("5. Dado el alimento, mostrar los valores nutricionales según el estado.");
		System.out.println("Introduce el nombre del alimento: ");
		inputAlimento = sc.nextLine().toLowerCase();

		for (int i = 0; i < arrayNombre.length; i++) {
			if (arrayNombre[i].equalsIgnoreCase(inputAlimento)) {
				System.out.println("Estado:  " + arrayEstado[i] + " | Calorias: " + arrayCalorias[i] + " | Grasas: " + arrayGrasas[i] + " | Proteinas: "
						+ arrayProteinas[i] + " | Proteinas:  " + arrayCarbohidratos[i]);
			}
		}
	}

}
