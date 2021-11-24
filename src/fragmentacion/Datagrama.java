package fragmentacion;

public class Datagrama {

	private int longitudTotal;
	private String protocolo;
	private String ipOrigen;
	private String ipDestino;
	private int numeroIdentificacion;
	private int tiempoDeVida;
	private int mtu;
	private static final int version = 4;
	private static final int longitudEncabezado = 5;
	private static final int serviciosDiferenciados = 0;

	public Datagrama() {

	}

	public Datagrama(int longitudTotal, String protocolo, String ipOrigen, String ipDestino,
			int numeroIdentificacion, int tiempoDeVida, int mtu) {
		super();
		this.longitudTotal = longitudTotal;
		this.protocolo = protocolo;
		this.ipOrigen = ipOrigen;
		this.ipDestino = ipDestino;
		this.numeroIdentificacion = numeroIdentificacion;
		this.tiempoDeVida = tiempoDeVida;
		this.mtu = mtu;
	}

	public int getLongitudTotal() {
		return longitudTotal;
	}

	public void setLongitudTotal(int longitudTotal) {
		this.longitudTotal = longitudTotal;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getIpOrigen() {
		return ipOrigen;
	}

	public void setIpOrigen(String ipOrigen) {
		this.ipOrigen = ipOrigen;
	}

	public String getIpDestino() {
		return ipDestino;
	}

	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
	}

	public int getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(int numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public int getTiempoDeVida() {
		return tiempoDeVida;
	}

	public void setTiempoDeVida(int tiempoDeVida) {
		this.tiempoDeVida = tiempoDeVida;
	}

	public int getMtu() {
		return mtu;
	}

	public void setMtu(int mtu) {
		this.mtu = mtu;
	}

	public static int getVersion() {
		return version;
	}

	public static int getLongitudencabezado() {
		return longitudEncabezado;
	}

	public static int getServiciosdiferenciados() {
		return serviciosDiferenciados;
	}

	@Override
	public String toString() {
		return "Datagrama [longitudTotal=" + longitudTotal + ", protocolo=" + protocolo + ", ipOrigen=" + ipOrigen
				+ ", ipDestino=" + ipDestino + ", numeroIdentificacion=" + numeroIdentificacion + ", tiempoDeVida="
				+ tiempoDeVida + ", version=" + version + ", longitud de encabezado=" + longitudEncabezado
				+ ", servicios diferenciados=" + serviciosDiferenciados + "]";
	}

}
