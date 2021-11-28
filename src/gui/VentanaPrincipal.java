package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import fragmentacion.Datagrama;
import fragmentacion.Fragmento;
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

	Logica logica = new Logica();

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
			int cant = (int) Math.ceil(((double) longTotal) / ((double) mtu));
			List<Fragmento> frags = logica.fragmentar(datagrama, cant, mtu);
			List<int[]> listaDecimal = logica.ordenarEncabezado(frags);
			List<String[]> listaHexa = logica.convertirHexadecimal(listaDecimal);
			List<String[]> listaHexaOrder = logica.ordenHexa(listaHexa);
			int cont = 0;
			String mensaje = "";
			for (String[] strings : listaHexaOrder) {
				for (int i = 0; i < strings.length; i++) {
					if (i == 6) {
						String[] fl = logica.flagDes.get(cont);
						strings[6] = fl[0];
						strings[7] = fl[1];
					}
				}
				cont++;
			}
			List<String> sumas = logica.sumaComprobacion(listaHexaOrder);
			for (int i = 0; i < listaHexaOrder.size(); i++) {
				for (int j = 0; j < listaHexaOrder.get(i).length; j++) {
					for (int j2 = 0; j2 < sumas.size(); j2++) {
						listaHexaOrder.get(i)[10] = sumas.get(j2).substring(0, 2);
						listaHexaOrder.get(i)[11] = sumas.get(j2).substring(2, 4);
					}
				}
			}
			List<String[]> listaBinary = logica.convertirDecimal(listaHexaOrder);
			for (String[] strings : listaBinary) {
				for (int i = 0; i < strings.length; i++) {
					mensaje += strings[i] + " ";
				}
				mensaje += "\n";
				cont++;
			}
			txtAreaResultados.setText(mensaje);
		}
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
			int cant = (int) Math.ceil(((double) longTotal) / ((double) mtu));
			List<Fragmento> frags = logica.fragmentar(datagrama, cant, mtu);
			List<int[]> listaDecimal = logica.ordenarEncabezado(frags);
			List<String[]> listaHexa = logica.convertirHexadecimal(listaDecimal);
			List<String[]> listaHexaOrder = logica.ordenHexa(listaHexa);
			int cont = 0;
			String mensaje = "";
			for (String[] strings : listaHexaOrder) {
				for (int i = 0; i < strings.length; i++) {
					if (i == 6) {
						String[] fl = logica.flagDes.get(cont);
						strings[6] = fl[0];
						strings[7] = fl[1];
					}
				}
				cont++;
			}
			List<String> sumas = logica.sumaComprobacion(listaHexaOrder);
			for (int i = 0; i < listaHexaOrder.size(); i++) {
				for (int j = 0; j < listaHexaOrder.get(i).length; j++) {
					for (int j2 = 0; j2 < sumas.size(); j2++) {
						listaHexaOrder.get(i)[10] = sumas.get(j2).substring(0, 2);
						listaHexaOrder.get(i)[11] = sumas.get(j2).substring(2, 4);
					}
				}
			}
			for (String[] strings : listaHexaOrder) {
				for (int i = 0; i < strings.length; i++) {
					mensaje += strings[i] + " ";
				}
				mensaje += "\n";
				cont++;
			}
			txtAreaResultados.setText(mensaje);
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
