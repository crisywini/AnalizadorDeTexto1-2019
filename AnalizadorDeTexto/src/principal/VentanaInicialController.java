package principal;

import java.io.File;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import modelo.Analizador;
public class VentanaInicialController {
	@FXML
	private TextField rutaCarpetaField;
	@FXML
	private Text textoNombreArchivo;

	@FXML
	private Text textoCantidadDePalabras;
	private PrincipalViewController ventanaPrincipal;

	public PrincipalViewController getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(PrincipalViewController ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@FXML
	void handelAnalizarButton() 
	{
		if(isInputValid())
		{
			
			try {
				Analizador miAnalizador =  ventanaPrincipal.getMiPrincipal().getMiAnalizador();
				miAnalizador.setRutaCarpeta(rutaCarpetaField.getText());
				miAnalizador.llenarTablas();
				textoCantidadDePalabras.setText(miAnalizador.obtenerCantidadPalabras());
				textoNombreArchivo.setText(miAnalizador.obtenerNombreArchivos());
			} catch (IOException e) {
				ventanaPrincipal.mostrarMensaje("ERROR", e.getMessage(), "", AlertType.ERROR);
			}
			
		}
	}

	@FXML
	void initialize() 
	{
	}
	public boolean isInputValid()
	{
		String mensajeDeError = "";
		boolean centinela = false;
		File archivo = new File(rutaCarpetaField.getText());
		if(rutaCarpetaField.getText()==null||rutaCarpetaField.getText().length()==0)
		{
			mensajeDeError += "Ruta no valida\n";
		}
		if(archivo.isFile()||!archivo.exists())
		{
			mensajeDeError += "La ruta ingresada no es valida";
		}
		if(mensajeDeError.length()==0)
		{
			centinela = true;
		}
		else
		{
			ventanaPrincipal.mostrarMensaje("Advertencia", mensajeDeError, "", AlertType.WARNING);
		}
		return centinela;
	}
}
