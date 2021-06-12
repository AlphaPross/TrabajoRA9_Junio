package Guardar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Leer {
	
	public String[][] LeerData() throws IOException {
		// Leer archivo
		String[][] listData = null;
		String[] listDataPre; 
		int i = 0;
		int y = 0;
		String cadena;
		FileReader f = new FileReader("data/data.dat");
		BufferedReader b = new BufferedReader(f);

		while((cadena = b.readLine())!=null) {
        	listData[i] = cadena.split(";");
        	i++;
        }
		
		System.out.println(listData[0]);

		b.close();
		return listData;
	}

	public static String[][] LeerCar() throws IOException {
		// Leer archivo
		String[][] listCar = new String[999][999];
		int i = 0;
		int y = 0;
		String cadena;
		FileReader f = new FileReader("data/car.dat");
		BufferedReader b = new BufferedReader(f);

		String[] listCarPre;

		for (int j = 0; (cadena = b.readLine()) != null; j++) {
			for (int j2 = 0; j2 < 5; j2++) {
				listCarPre = cadena.split(";");
				listCar[j][j2] = listCarPre[j2];
			}
		}

		b.close();
		return listCar;
	}

}
