package fragmentacion;

public class Fragmento {
	
	private int longitudTotal;
	private String protocolo;
	private String ipOrigen;
	private String ipDestino;
	private int numeroIdentificacion;
	private int tiempoDeVida;
	private static final int version = 4;
	private static final int longitudEncabezado = 5;
	private static final int serviciosDiferenciados = 0;
	private static final int flagCero = 0;
	private int dF;
	private int mF;
	private int desplazamiento;
	private int sumaComprobacion;
	
	public Fragmento() {
	
	}
	
	public Fragmento(int longitudTotal, String protocolo, String ipOrigen, String ipDestino, int numeroIdentificacion,
			int tiempoDeVida, int dF, int mF, int desplazamiento, int sumaComprobacion) {
		this.longitudTotal = longitudTotal;
		this.protocolo = protocolo;
		this.ipOrigen = ipOrigen;
		this.ipDestino = ipDestino;
		this.numeroIdentificacion = numeroIdentificacion;
		this.tiempoDeVida = tiempoDeVida;
		this.dF = dF;
		this.mF = mF;
		this.desplazamiento = desplazamiento;
		this.sumaComprobacion = sumaComprobacion;
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

	public int getdF() {
		return dF;
	}

	public void setdF(int dF) {
		this.dF = dF;
	}

	public int getmF() {
		return mF;
	}

	public void setmF(int mF) {
		this.mF = mF;
	}

	public int getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(int desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

	public int getSumaComprobacion() {
		return sumaComprobacion;
	}

	public void setSumaComprobacion(int sumaComprobacion) {
		this.sumaComprobacion = sumaComprobacion;
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

	public static int getFlagcero() {
		return flagCero;
	}

	@Override
	public String toString() {
		return "Fragmento [longitudTotal=" + longitudTotal + ", protocolo=" + protocolo + ", ipOrigen=" + ipOrigen
				+ ", ipDestino=" + ipDestino + ", numeroIdentificacion=" + numeroIdentificacion + ", tiempoDeVida="
				+ tiempoDeVida +  ", version=" + version + ", longitud de encabezado=" + longitudEncabezado 
				+ ", servicios diferenciados=" + serviciosDiferenciados + ", flagCero=" + flagCero 
				+ ", dF=" + dF + ", mF=" + mF 
				+ ", desplazamiento=" + desplazamiento
				+ ", sumaComprobacion=" + sumaComprobacion + "]";
	}
	
	
}
