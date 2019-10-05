package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Persistencia 
{
	public static void serializarObjeto(Object objeto, String ruta) throws IOException
	{
		FileOutputStream archivo = new FileOutputStream(ruta);
		XMLEncoder codificador = new XMLEncoder(archivo);
		codificador.writeObject(objeto);
		codificador.close();
		archivo.close();
	}
	public static Object deserializarObjeto(String ruta) throws IOException
	{
		Object objeto;
		FileInputStream archivo = new FileInputStream(ruta);
		XMLDecoder decodificador = new XMLDecoder(archivo);
		objeto = decodificador.readObject();
		decodificador.close();
		archivo.close();
		return objeto;
	}

}
