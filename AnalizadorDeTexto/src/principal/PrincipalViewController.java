package principal;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalViewController {
	@FXML
	private BorderPane panelPrincipal;
	private AnchorPane panelInicial;
	private VentanaInicialController controladorInicial;
	
	private Principal miPrincipal;
	private Stage miStage;

	public Principal getMiPrincipal() {
		return miPrincipal;
	}

	public void setMiPrincipal(Principal miPrincipal) {
		this.miPrincipal = miPrincipal;
	}

	public Stage getMiStage() {
		return miStage;
	}

	public void setMiStage(Stage miStage) {
		this.miStage = miStage;
	}
	public void cargarPanelInicial()
	{
		if(panelInicial == null)
		{
			try 
			{
				FXMLLoader cargador = new FXMLLoader();
				cargador.setLocation(Principal.class.getResource("../vista/VentanaInicial.fxml"));
				panelInicial = (AnchorPane)cargador.load();
				controladorInicial = cargador.getController();
				controladorInicial.setVentanaPrincipal(this);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		panelPrincipal.setCenter(panelInicial);
	}
	@FXML
    void initialize() 
	{
		cargarPanelInicial();
    }
	@FXML
    void handleGuardarDatosMenu() 
	{
		if(elegirGuardar())
		{
			getMiPrincipal().guardarDatos();
		}
    }

    @FXML
    void handleSalirMenu() 
    {
    	miStage.hide();

    }
    public void mostrarMensaje(String titulo, String mensaje, String cabecera, AlertType tipo)
    {
    	Alert miAlert =  new Alert(tipo);
    	miAlert.setTitle(titulo);
    	miAlert.setHeaderText(cabecera);
    	miAlert.setContentText(mensaje);
    	miAlert.initOwner(miStage);
    	miAlert.showAndWait();
    }
   
    public boolean elegirGuardar()
    {
    	boolean centinela;
    	Alert miAlert = new Alert(AlertType.CONFIRMATION);
    	miAlert.setTitle("Guardar?");
    	miAlert.setContentText("Desea guardar los datos?");
    	miAlert.initOwner(miStage);
    	ButtonType buttonTypeOne = new ButtonType("Si");
    	ButtonType buttonTypeTwo = new ButtonType("No");
    	miAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
    	Optional<ButtonType> resultado = miAlert.showAndWait();
    	centinela = resultado.get()==buttonTypeOne;
    	return centinela;
    }
    

}
