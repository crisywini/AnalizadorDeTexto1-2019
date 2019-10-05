package modelo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import persistencia.Archivo;

public class Analizador implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Hashtable<String, Integer>> tablas;
	private String rutaCarpeta;
	public Analizador() 
	{
		this.setTablas(new ArrayList<Hashtable<String,Integer>>());
		this.rutaCarpeta="";
	}

	public ArrayList<Hashtable<String, Integer>> getTablas() {
		return tablas;
	}

	public String getRutaCarpeta() {
		return rutaCarpeta;
	}

	public void setRutaCarpeta(String rutaCarpeta) {
		this.rutaCarpeta = rutaCarpeta;
	}

	public void setTablas(ArrayList<Hashtable<String, Integer>> tablas) {
		this.tablas = tablas;
	}
	public void llenarTabla(Hashtable<String, Integer> tabla, String nombre, Integer valor)
	{
		Integer dato = tabla.get(nombre);
		if(dato ==null)
		{
			tabla.put(nombre, valor);
		}
		else
		{
			tabla.put(nombre, dato.intValue()+valor);
		}
	}
	public void llenarTablas() throws IOException
	{
		ArrayList<File> archivos = Archivo.obtenerArchivos(getRutaCarpeta());
		int contador = 0;
		for(int i=0; i< archivos.size();i++)
		{
			contador = Archivo.contarPalabrasArchivo(archivos.get(i));
			getTablas().add(new Hashtable<String, Integer>());
			llenarTabla(getTablas().get(i), archivos.get(i).getName(), contador);
		}
	}
	public String obtenerNombreArchivos()
	{
		String nombreArchivos = "";
		Hashtable<String, Integer> miTabla;
		Enumeration<String> llaves ;
		for (int i = 0; i < getTablas().size(); i++) 
		{
			miTabla = getTablas().get(i);
			llaves = miTabla.keys();
			while(llaves.hasMoreElements())
			{
				nombreArchivos += llaves.nextElement()+"\n";
			}
		}
		return nombreArchivos;
	}
	public String obtenerCantidadPalabras()
	{
		String cantidadDePalabras = "";
		Hashtable<String, Integer> miTabla;
		Enumeration<Integer> valores;
		for (int i = 0; i < getTablas().size(); i++) 
		{
			miTabla = getTablas().get(i);
			valores = miTabla.elements();
			while(valores.hasMoreElements())
			{
				cantidadDePalabras += valores.nextElement().intValue()+" \n";
			}
		}
		return cantidadDePalabras;
	}

}