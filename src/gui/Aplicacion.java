package gui;

import fragmentacion.Logica;
import javafx.application.Application;
import javafx.stage.Stage;

public class Aplicacion extends Application {
	
	/**
	 * Main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		launch(args);

	}
	
	/**
	 * Metodo ejecutor de Java FX
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Logica principal = new Logica();
		Controlador controlador = new Controlador(principal);
		controlador.VentanaInicial();

	}
}
