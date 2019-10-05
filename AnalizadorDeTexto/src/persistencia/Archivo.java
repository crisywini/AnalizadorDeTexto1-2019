package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Archivo 
{
	public static boolean isArchivo(String ruta)
	{
		File archivo = new File(ruta);
		return archivo.isFile();
	}
	public static ArrayList<File> obtenerArchivos(String rutaCarpeta)
	{
		ArrayList<File> archivos = new ArrayList<File>();
		File carpeta = new File(rutaCarpeta);
		String[] contenido = carpeta.list();
		File auxiliar;
		for (int i = 0; i < contenido.length; i++)
		{
			auxiliar = new File(rutaCarpeta+"/"+contenido[i]);
			if(auxiliar.isFile())
			{
				archivos.add(auxiliar);
			}
		}
		return archivos;
	}
	public static String[] particionar(String informacion)
	{
		String[] salida = null;
		StringTokenizer particionador = new StringTokenizer(informacion, " ,;:.?¡¿()!#$%&/=0123456789", false);
		salida = new String[particionador.countTokens()];
		int cont = 0;
		while (particionador.hasMoreElements()) 
		{
			salida[cont]=particionador.nextToken();
			cont++;
		}
		return salida;
	}
	public static ArrayList<String> obtenerInformacionArchivo(String ruta) throws IOException
	{
		ArrayList<String> informacion = new ArrayList<String>();
		FileReader archivoLector = new FileReader(ruta);
		BufferedReader direccionadorArchivo = new BufferedReader(archivoLector);
		String linea = "";
		while ((linea = direccionadorArchivo.readLine())!=null) 
		{
			informacion.add(linea+"\n");
		}
		direccionadorArchivo.close();
		archivoLector.close();
		return informacion;
	}
	public static String convertirArrayListAString(ArrayList<String> informacion)
	{
		String informacionSalida = "";
		for (int i = 0; i < informacion.size(); i++) 
		{
			informacionSalida+=informacion.get(i);
		}
		return informacionSalida;
	}
	public static int contarPalabrasArchivo(File archivo) throws IOException
	{
		ArrayList<String> informacionArchivo = obtenerInformacionArchivo(archivo.getAbsolutePath());
		String informacion = convertirArrayListAString(informacionArchivo);
		String[] particionamiento = particionar(informacion);
		int contador = particionamiento.length;
		return contador;
	}
	
}