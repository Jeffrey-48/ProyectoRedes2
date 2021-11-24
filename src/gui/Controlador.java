package gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import fragmentacion.Logica;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controlador {
	
	public Logica logica;

	public Controlador(Logica logica) throws FileNotFoundException, ClassNotFoundException, IOException {
		super();
		this.logica = logica;
	}

	/**
	 * Controlador De la Ventana  en JavaFx
	 * 
	 * @throws Exception
	 */
	public void VentanaInicial() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ventanas/ventanaPrincipal.fxml"));

		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("DATAGRAMA IP");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		VentanaPrincipal vp = fxmlLoader.getController();
		vp.setControlador(this);
		vp.setStage(primaryStage);

	}
}
