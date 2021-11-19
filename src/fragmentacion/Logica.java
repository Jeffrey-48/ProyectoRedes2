package fragmentacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Logica {

	public static void main(String[] args) {
		Datagrama data = new Datagrama(548, "ICPM", "192.168.2.227", "192.168.2.222", 52276, 100);
		int cant = (int) Math.ceil(((double) data.getLongitudTotal()) / ((double) 1500));
		List<Fragmento> frags = fragmentar(data, cant, 1500);
		List<int[]> listaDecimal = ordenarEncabezado(frags);
		List<String[]> listaHexa = convertirHexadecimal(listaDecimal);
		for (String[] strings : listaHexa) {
			for (int i = 0; i < strings.length; i++) {
				System.out.println(strings[i]);
			}
		}
		List<String[]> listaHexaOrder = ordenHexa(listaHexa);
		for (String[] strings : listaHexaOrder) {
			for (int i = 0; i < strings.length; i++) {
				System.out.print(strings[i]);
			}
			System.out.println();
		}

	}

	private static List<String[]> ordenHexa(List<String[]> listaHexa) {
		List<String[]> listaHexadecimal = new ArrayList<String[]>();
		for (String[] strings : listaHexa) {
			String[] hexa = new String[21];
			hexa[0] = strings[0] + strings[1];
			hexa[1] = strings[2];
			hexa[2] = strings[3].substring(0, 2);
			hexa[3] = strings[3].substring(2, 4);
			hexa[4] = strings[4].substring(0, 2);
			hexa[5] = strings[4].substring(2, 4);
			hexa[6] = strings[5] + strings[6];
			hexa[7] = strings[7] + strings[8].substring(0,1);
			hexa[8] = strings[8].substring(1, 3);
			hexa[9] = strings[9];
			System.out.println(hexa[6] + " -- " +hexa[7] + " -- " + hexa[8] + " -- " + hexa[9] + " -- " );
			hexa[10] = strings[10];
			hexa[11] = strings[11] + "0";
			hexa[12] = strings[11] + "0";
			hexa[13] = strings[12];
			hexa[14] = strings[13];
			hexa[15] = strings[14];
			hexa[16] = strings[15];
			hexa[17] = strings[16];
			hexa[18] = strings[17];
			hexa[19] = strings[18];
			hexa[20] = strings[19];
			listaHexadecimal.add(hexa);
		}
		return listaHexadecimal;
	}

	private static List<int[]> ordenarEncabezado(List<Fragmento> frags) {
		List<int[]> listaDecimal = new ArrayList<>();
		for (Fragmento fragmento : frags) {
			int encabezado[] = new int[20];
			encabezado[0] = fragmento.getVersion();
			encabezado[1] = fragmento.getLongitudencabezado();
			encabezado[2] = fragmento.getServiciosdiferenciados();
			encabezado[3] = fragmento.getLongitudTotal();
			encabezado[4] = fragmento.getNumeroIdentificacion();
			encabezado[5] = fragmento.getFlagcero();
			encabezado[6] = fragmento.getdF();
			encabezado[7] = fragmento.getmF();
			encabezado[8] = fragmento.getDesplazamiento();
			encabezado[9] = fragmento.getTiempoDeVida();
			encabezado[10] = calcularProtocolo(fragmento.getProtocolo());
			encabezado[11] = fragmento.getSumaComprobacion();
			String obtetos[] = dividirDireccion(fragmento.getIpOrigen());
			encabezado[12] = Integer.parseInt(obtetos[0]);
			encabezado[13] = Integer.parseInt(obtetos[1]);
			encabezado[14] = Integer.parseInt(obtetos[2]);
			encabezado[15] = Integer.parseInt(obtetos[3]);
			String obtetosDest[] = dividirDireccion(fragmento.getIpDestino());
			encabezado[16] = Integer.parseInt(obtetosDest[0]);
			encabezado[17] = Integer.parseInt(obtetosDest[1]);
			encabezado[18] = Integer.parseInt(obtetosDest[2]);
			encabezado[19] = Integer.parseInt(obtetosDest[3]);
			listaDecimal.add(encabezado);
		}
		return listaDecimal;

	}

	public static List<String[]> convertirHexadecimal(List<int[]> fragmentos) {

		List<String[]> listaHexadecimal = new ArrayList<String[]>();

		for (int[] fragmento : fragmentos) {
			String[] fragmentoHexadecimal = new String[20];
			for (int i = 0; i < fragmento.length; i++) {
				fragmentoHexadecimal[i] = Integer.toHexString(fragmento[i]);
				if (fragmentoHexadecimal[i].length() < 2 && i == 2) {
					fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
				}
				if (i == 3) {
					if (fragmentoHexadecimal[i].length() < 4) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
					if (fragmentoHexadecimal[i].length() < 3) {
						fragmentoHexadecimal[i] = "00" + fragmentoHexadecimal[i];
					}
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "000" + fragmentoHexadecimal[i];
					}
				}
				if (i == 4) {
					if (fragmentoHexadecimal[i].length() < 4) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
					if (fragmentoHexadecimal[i].length() < 3) {
						fragmentoHexadecimal[i] = "00" + fragmentoHexadecimal[i];
					}
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "000" + fragmentoHexadecimal[i];
					}
				}
				if (i == 8) {
					if (fragmentoHexadecimal[i].length() < 3) {
						fragmentoHexadecimal[i] = "00" + fragmentoHexadecimal[i];
					}
				}
				if (i == 9) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 10) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 12) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 13) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 14) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 15) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 16) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 17) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 18) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
				if (i == 19) {
					if (fragmentoHexadecimal[i].length() < 2) {
						fragmentoHexadecimal[i] = "0" + fragmentoHexadecimal[i];
					}
				}
			}
			listaHexadecimal.add(fragmentoHexadecimal);
		}

		return listaHexadecimal;
	}

	public static String[] dividirDireccion(String ip) {
		return ip.split("\\.");
	}

	private static int calcularProtocolo(String protocol) {
		int retorno = 0;
		if (protocol.equals("TCP")) {
			retorno = 6;
		}
		if (protocol.equals("UDP")) {
			retorno = 23;
		}
		if (protocol.equals("ICPM")) {
			retorno = 1;
		}
		return retorno;
	}

	private static List<Fragmento> calcularSumas(List<Fragmento> frags) {

		return null;
	}

	public static List<Fragmento> fragmentar(Datagrama dt, int cant, int mtu) {
		List<Fragmento> datas = new ArrayList<>();
		int longitud = 0;
		int desplazamiento = 0;
		int identificacion = dt.getNumeroIdentificacion();
		int longitudFragmento = dt.getLongitudTotal();
		for (int i = 0; i < cant; i++) {
			if (i != cant - 1) {
				longitud = mtu;
				longitudFragmento -= longitud;
				Fragmento ndt = new Fragmento(longitud, dt.getProtocolo(), dt.getIpOrigen(), dt.getIpDestino(),
						identificacion, dt.getTiempoDeVida() - 1, 0, 1, desplazamiento, 0);
				datas.add(ndt);
				desplazamiento += longitud;
			} else {
				if (cant == 1) {
					longitud = longitudFragmento;
					longitudFragmento -= longitud;
					Fragmento ndt = new Fragmento(longitud, dt.getProtocolo(), dt.getIpOrigen(), dt.getIpDestino(),
							identificacion, dt.getTiempoDeVida() - 1, 0, 0, desplazamiento, 0);
					datas.add(ndt);
				} else {
					longitud = longitudFragmento;
					longitudFragmento -= longitud;
					desplazamiento += longitud;
					Fragmento ndt = new Fragmento(longitud, dt.getProtocolo(), dt.getIpOrigen(), dt.getIpDestino(),
							identificacion, dt.getTiempoDeVida() - 1, 0, 0, desplazamiento, 0);
					datas.add(ndt);
				}
			}
		}
		for (Fragmento datagrama : datas) {
			System.out.println(datagrama.toString());
		}
		return datas;
	}

	public static String convertirDecimalABinarioManual(long decimal) {
		if (decimal <= 0) {
			return "0";
		}
		StringBuilder binario = new StringBuilder();
		while (decimal > 0) {
			short residuo = (short) (decimal % 2);
			decimal = decimal / 2;
			binario.insert(0, String.valueOf(residuo));
		}
		return binario.toString();
	}

}