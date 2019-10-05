package principal;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Analizador;
import persistencia.Persistencia;

public class Principal extends Application 
{
	private Analizador miAnalizador;

	@Override
	public void start(Stage primaryStage) 
	{
		if(miAnalizador==null)
		{
			miAnalizador = new Analizador();
		}
		cargarPanelPrincipal(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
	public void cargarPanelPrincipal(Stage primaryStage)
	{
		try 
		{
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(Principal.class.getResource("../vista/PrincipalView.fxml"));
			BorderPane panel = (BorderPane)cargador.load();
			Scene scene  = new Scene(panel);
			PrincipalViewController controlador = cargador.getController();
			controlador.setMiPrincipal(this);
			controlador.setMiStage(primaryStage);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:src/images/analizador-de-datos.png"));
			primaryStage.show();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void guardarDatos()
	{
		try {
			Persistencia.serializarObjeto(miAnalizador, "src/resources/Analizador.XML");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void cargarDatos()
	{
		Analizador miAnalizador;
		File miFile = new File("src/resources/Analizador.XML");
		if(miFile.exists())
		{
			try 
			{
				miAnalizador = (Analizador)Persistencia.deserializarObjeto("src/resources/Analizador.XML");
				this.miAnalizador=miAnalizador;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	public Analizador getMiAnalizador() {
		return miAnalizador;
	}

	public void setMiAnalizador(Analizador miAnalizador) {
		this.miAnalizador = miAnalizador;
	}
	
}
