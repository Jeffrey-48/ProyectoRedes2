package fragmentacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.stylesheets.LinkStyle;

public class Logica {

	public static List<String[]> flagDes = new ArrayList<>();

	public static void main(String[] args) {
		Datagrama data = new Datagrama(548, "ICPM", "192.168.2.227", "192.168.2.222", 52276, 64, 1500);
		int cant = (int) Math.ceil(((double) data.getLongitudTotal()) / ((double) 1500));
		List<Fragmento> frags = fragmentar(data, cant, 1500);
		List<int[]> listaDecimal = ordenarEncabezado(frags);
		List<String[]> listaHexa = convertirHexadecimal(listaDecimal);
		List<String[]> listaHexaOrder = ordenHexa(listaHexa);
		int cont = 0;
		for (String[] strings : listaHexaOrder) {
			for (int i = 0; i < strings.length; i++) {
				if (i == 6) {
					String[] fl = flagDes.get(cont);
					strings[6] = fl[0];
					strings[7] = fl[1];
				}
			}
			cont++;
		}
		List<String> sumas = sumaComprobacion(listaHexaOrder);
		for (int i = 0; i < listaHexaOrder.size(); i++) {
			for (int j = 0; j < listaHexaOrder.get(i).length ; j++) {
				for (int j2 = 0; j2 < sumas.size(); j2++) {
					listaHexaOrder.get(i)[10] = sumas.get(j2).substring(0,2);
					listaHexaOrder.get(i)[11] = sumas.get(j2).substring(2,4);
				}
			}
		}
		List<String[]> listaBinary = convertirDecimal(listaHexaOrder);
		for (String[] strings : listaBinary) {
			for (int i = 0; i < strings.length; i++) {
				System.out.println(strings[i]);
			}
		}
	}
	
	public static List<String[]> convertirDecimal( List<String[]> listaHexa) {
		List<String[]> listaDecimales = new ArrayList<>();
		String cadenaBinary[] = new String [20];
		for (String[] strings : listaHexa) {
			for (int i = 0; i < strings.length; i++) {
				String cad1 = binary(strings[i].substring(0,1));
				String cad2 = binary(strings[i].substring(1,2));
				cad1 = ordenar(cad1);
				cad2 = ordenar(cad2);
				cadenaBinary[i] = cad1 + " " + cad2;
			}
			listaDecimales.add(cadenaBinary);
		}
		return listaDecimales;
	}
	
	public static String ordenar (String pal) {
		if (pal.length() == 1) {
			pal = "000" + pal; 
		}
		if (pal.length() == 2) {
			pal = "00" + pal; 
		}
		if (pal.length() == 3) {
			pal = "0" + pal; 
		}
		return pal;
	}
	
	 public static String binary(String hexa) {
	        int numHex = Integer.parseInt(hexa, 16);
	        String binary = Integer.toBinaryString(numHex);
	        return binary;
	    }

	public static List<String> sumaComprobacion(List<String[]> listaHexa) {
		List<String> lista = new ArrayList<>();
		for (String[] fr : listaHexa) {
			String[] listaHexSum = new String[10];
			int cont = 0;
			for (int i = 0; i < fr.length; i++) {
				if (i + 1 < fr.length && i % 2 == 0) {
					listaHexSum[cont] = fr[i] + "" + fr[i + 1];
					cont++;
				}
			}
			String hexa1 = "";
			for (int i = 0; i < listaHexSum.length; i++) {
				if (i != 5) {
					if (i == 0) {
						hexa1 = listaHexSum[0];
					}
					if (i > 0) {
						hexa1 = sumarHexa(hexa1, listaHexSum[i]);
					}
				}
			}
			lista.add(hexa1);
		}
		return lista;

	}

	private static String sumarHexa(String hexa1, String hexa2) {
		int uno[] = new int[4];
		int cont1 = 0;
		for (int i = 0; i < hexa1.length(); i++) {
			String letra = hexa1.charAt(i) + "";
			uno[cont1] = numeroLetra(letra);
			cont1++;
		}
		int dos[] = new int[4];
		int cont2 = 0;
		for (int i = 0; i < hexa2.length(); i++) {
			String letra = hexa2.charAt(i) + "";
			dos[cont2] = numeroLetra(letra);
			cont2++;
		}
		int suma[] = new int[4];
		int cont3 = 0;
		for (int i = 0; i < uno.length; i++) {
			if (uno[i] + dos[i] > 15) {
				suma[cont3] = uno[i] + dos[i] - 16;
				cont3++;
			} else {
				suma[cont3] = uno[i] + dos[i];
				cont3++;
			}
		}
		String cadena = "";
		for (int i = 0; i < suma.length; i++) {
			if (suma[i] > 9) {
				if (suma[i] == 10) {
					cadena += "a";
				}
				if (suma[i] == 11) {
					cadena += "b";
				}
				if (suma[i] == 12) {
					cadena += "c";
				}
				if (suma[i] == 13) {
					cadena += "d";
				}
				if (suma[i] == 14) {
					cadena += "e";
				}
				if (suma[i] == 15) {
					cadena += "f";
				}
			} else {
				cadena += suma[i];
			}
		}
		return cadena;
	}

	public static int numeroLetra(String letra) {
		int retor = 0;
		if (letra.equals("a")) {
			retor = 10;
		}
		if (letra.equals("b")) {
			retor = 11;
		}
		if (letra.equals("c")) {
			retor = 12;
		}
		if (letra.equals("d")) {
			retor = 13;
		}
		if (letra.equals("e")) {
			retor = 14;
		}
		if (letra.equals("f")) {
			retor = 15;
		}
		if (letra.equals("0") || letra.equals("1") || letra.equals("2") || letra.equals("3") || letra.equals("4")
				|| letra.equals("5") || letra.equals("6") || letra.equals("7") || letra.equals("8")
				|| letra.equals("9")) {
			retor = Integer.parseInt(letra);
		}
		return retor;
	}

	public static List<String[]> ordenHexa(List<String[]> listaHexa) {
		List<String[]> listaHexadecimal = new ArrayList<String[]>();
		for (String[] strings : listaHexa) {
			String[] hexa = new String[20];
			hexa[0] = strings[0] + strings[1];
			hexa[1] = strings[2];
			hexa[2] = strings[3].substring(0, 2);
			hexa[3] = strings[3].substring(2, 4);
			hexa[4] = strings[4].substring(0, 2);
			hexa[5] = strings[4].substring(2, 4);
			hexa[6] = strings[5] + strings[6];
			hexa[7] = strings[8].substring(1, 3);
			hexa[8] = strings[9];
			hexa[9] = strings[10];
			hexa[10] = strings[11] + "0";
			hexa[11] = strings[11] + "0";
			hexa[12] = strings[12];
			hexa[13] = strings[13];
			hexa[14] = strings[14];
			hexa[15] = strings[15];
			hexa[16] = strings[16];
			hexa[17] = strings[17];
			hexa[18] = strings[18];
			hexa[19] = strings[19];
			listaHexadecimal.add(hexa);
		}
		return listaHexadecimal;
	}

	public static List<int[]> ordenarEncabezado(List<Fragmento> frags) {
		List<int[]> listaDecimal = new ArrayList<>();
		for (Fragmento fragmento : frags) {
			int encabezado[] = new int[20];
			encabezado[0] = Fragmento.getVersion();
			encabezado[1] = Fragmento.getLongitudencabezado();
			encabezado[2] = Fragmento.getServiciosdiferenciados();
			encabezado[3] = fragmento.getLongitudTotal();
			encabezado[4] = fragmento.getNumeroIdentificacion();
			encabezado[5] = Fragmento.getFlagcero();
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
			String[] casillasDyF = convertirhexaDesplazFlag(0, fragmento.getdF(), fragmento.getmF(),
					fragmento.getDesplazamiento());
			flagDes.add(casillasDyF);
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

	public static List<Fragmento> fragmentar(Datagrama dt, int cant, int mtu) {
		List<Fragmento> datas = new ArrayList<>();
		int longitud = 0;
		int desplazamiento = 0;
		int identificacion = (int) dt.getNumeroIdentificacion();
		int longitudFragmento = dt.getLongitudTotal();
		for (int i = 0; i < cant; i++) {
			if (i != cant - 1) {
				longitud = mtu;
				longitudFragmento -= longitud;
				Fragmento ndt = new Fragmento(longitud, dt.getProtocolo(), dt.getIpOrigen(), dt.getIpDestino(),
						identificacion, dt.getTiempoDeVida(), 0, 1, desplazamiento, 0);
				datas.add(ndt);
				desplazamiento += longitud;
			} else {
				if (cant == 1) {
					longitud = longitudFragmento;
					longitudFragmento -= longitud;
					Fragmento ndt = new Fragmento(longitud, dt.getProtocolo(), dt.getIpOrigen(), dt.getIpDestino(),
							identificacion, dt.getTiempoDeVida(), 0, 0, desplazamiento + longitud - 20, 0);
					desplazamiento += longitud;
					datas.add(ndt);
				} else {
					longitud = longitudFragmento;
					longitudFragmento -= longitud;
					desplazamiento += longitud;
					Fragmento ndt = new Fragmento(longitud, dt.getProtocolo(), dt.getIpOrigen(), dt.getIpDestino(),
							identificacion, dt.getTiempoDeVida(), 0, 0, desplazamiento, 0);
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

	public static String[] convertirhexaDesplazFlag(int flag0, int df, int mf, int desplazamiento) {
		String arrSalida[] = new String[2];
		String desp = convertirDecimalABinarioManual(desplazamiento) + "0";
		int desplaza = Integer.parseInt(desp, 2);
		String desplaHexa = Integer.toHexString(desplaza);
		String digitoExtra = desplaHexa.charAt(desplaHexa.length() - 1) + "";
		int flag = Integer.parseInt(flag0 + "" + df + "" + mf, 2);
		String flagL = Integer.toHexString(flag) + digitoExtra;
		if (desplaHexa.length() < 2) {
			desplaHexa += "0";
		}
		arrSalida[0] = flagL;
		arrSalida[1] = desplaHexa.substring(0, 2);
		return arrSalida;
	}

	public boolean esNumero(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int generarIdentificacion() {
		return (int) Math.floor(Math.random() * (65535 - 0 + 1) + 0);
	}

	public int generarTiempoDeVida() {
		return (int) Math.floor(Math.random() * (255 - 0 + 1) + 0);
	}
}
