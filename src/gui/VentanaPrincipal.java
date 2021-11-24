package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import fragmentacion.Datagrama;
import fragmentacion.Logica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class VentanaPrincipal implements Initializable {
	@FXML
	Stage stage;

	@FXML
	Controlador controlador;

	@FXML
	private Button btnFormatoLegible;

	@FXML
	private Button btnLimpiar;

	@FXML
	private TextField txtIpOrigen;

	@FXML
	private TextField txtDiferenciado;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	private TextArea txtAreaResultados;

	@FXML
	private TextField txtLongitudEncabezado;

	@FXML
	private TextField txtLongitudT;

	@FXML
	private RadioButton btnRadioICMP;

	@FXML
	private Button btnFormatoBinario;

	@FXML
	private TextField txtVersion;

	@FXML
	private RadioButton btnRadioTCP;

	@FXML
	private RadioButton btnRadioUDP;

	@FXML
	private TextField txtIpDestino;

	@FXML
	private Button btnFormatoHexadecimal;

	@FXML
	private TextField txtTimeLive;

	@FXML
	private TextField txtMtu;

	@FXML
	private ToggleGroup groupRadioButton;

	@FXML
	void actionBinario(ActionEvent event) {
		txtAreaResultados.setText("");
	}

	@FXML
	void actionLimpiar(ActionEvent event) {

		txtIpOrigen.setText("");
		txtIpDestino.setText("");
		txtMtu.setText("");
		txtLongitudT.setText("");
		txtTimeLive.setText("");
		txtIdentificacion.setText("");
		txtAreaResultados.setText("");

	}

	@FXML
	void actionHexa(ActionEvent event) {
		txtAreaResultados.setText("");
		Logica logica = new Logica();

		if (logica.esNumero(txtLongitudT.getText()) && logica.esNumero(txtMtu.getText())) {
			String direccionOrigen = txtIpOrigen.getText();
			String direccionDestino = txtIpDestino.getText();
			int longTotal = Integer.parseInt(txtLongitudT.getText());
			int mtu = Integer.parseInt(txtMtu.getText());

			String protocolo = "";
			if (btnRadioICMP.isSelected()) {
				protocolo = "ICMP";
			} else if (btnRadioTCP.isSelected()) {
				protocolo = "TCP";
			} else {
				protocolo = "UDP";
			}

			int identificacion = logica.generarIdentificacion();
			int tiempoVida = logica.generarTiempoDeVida();

			txtTimeLive.setText(tiempoVida + "");
			txtIdentificacion.setText(identificacion + "");

			Datagrama datagrama = new Datagrama(longTotal, protocolo, direccionOrigen, direccionDestino, identificacion,
					tiempoVida, mtu);
		} else {
			JOptionPane.showMessageDialog(null, "Datos Incorrectos, verifique.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	@FXML
	void actionLegible(ActionEvent event) {
		txtAreaResultados.setText("");
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
