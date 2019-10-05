package principal;

import java.io.IOException;


import modelo.Analizador;


public class Pruebas {

	public static void main(String[] args) 
	{
		Analizador miAnalizador = new Analizador();
		miAnalizador.setRutaCarpeta("C:\\Users\\Crisi\\Documents");
		try {
			miAnalizador.llenarTablas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(miAnalizador.obtenerCantidadPalabras());
		System.out.println(miAnalizador.obtenerNombreArchivos());
	}

}
