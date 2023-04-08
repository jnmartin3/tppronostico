package ar.utn.ap.pronosticos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//colecciones de partidos y rondas para almacenar todos los existentes en los archivos
		Collection<Partido> partidos = new ArrayList<Partido>();
//		Collection<Ronda> rondas = new ArrayList<Ronda>();
		
		
		//traer path de args, abrir archivo y guardar en lista lineasResultados
		Path pathResultados = Paths.get(args[0]);
		Path pathPronosticos = Paths.get(args[1]);

		List<String> lineasResultados = null;
		List<String> lineasPronosticos = null;
		
		//abro los archivos y los guardo en las listas lineasResultado y lineasPronosticos
		//el metodo Files.readAllLines cierra automaticamente
		try {
			lineasResultados = Files.readAllLines(pathResultados);
		}catch(Exception e){
			System.out.println("Error al abrir archivo resultados: " + e.getMessage());
			System.exit(1);			//Solo en MAIN se coloca System.exit
		}
		
		try {
			lineasPronosticos = Files.readAllLines(pathPronosticos);
		}catch(Exception e){
			System.out.println("Error al abrir archivo Pronosticos: " + e.getMessage());
			System.exit(1);
		}
	
		
		
		//------------------- Archivo de RESULTADOS ---------------------------
		
		//obviando encabezado de archivo...
		boolean obviarPrimera = true;
		//para validar cantidad de campos del archivo
		int cantidadCampos = 5;
		
		for(String lineaResultados : lineasResultados) {
			if(obviarPrimera) {
				obviarPrimera = false;
			}else {
				
				//System.out.println(lineaResultados);				// mostrar contenido del mismo...
				String[] separado = lineaResultados.split(",");		//cortar por su separadores coma y guardar en vector
				
				//validacion de cantidad de datos del archivo
				if (separado.length != cantidadCampos) {
					System.out.println("La cantidad de campos del archivo resultados no es correcta. Corrija el archivo y reinicie.");
					System.exit(1);
				}
				
				//Validacion del numero de goles, que sea entero				
				try {
					int numero1 = Integer.parseInt(separado[2]);
					int numero2 = Integer.parseInt(separado[3]);
					if(numero1 < 0 || numero2 <0) {
						System.out.println("La cantidad de goles del archivo es incorrecta. Corrija el archivo y reinicie.");
						System.exit(1);
					}
				}catch(NumberFormatException e) {
					System.out.println("La cantidad de goles del archivo es incorrecta. Corrija el archivo y reinicie.");
					System.exit(1);
				}
				
				//Crear los objetos Equipo con sus atributos
				Equipo equipo1 = new Equipo(separado[1],Integer.parseInt(separado[2]),"Seleccion nacional");
				Equipo equipo2 = new Equipo(separado[4],Integer.parseInt(separado[3]), "Seleccion nacional");
				
				//instanciamos un Partido con nro de ronda y los equipos contendientes
				Partido partido = new Partido(Integer.parseInt(separado[0]), equipo1, equipo2);
			
				partidos.add(partido);
				
			}
		}
		
		
		//------------------- Archivo de PRONOSTICOS ---------------------------
		
		obviarPrimera = true;
		
		int puntos = 0;
		
		String player ="";
		boolean unicaVez = true;
		Jugador jugador = null;
		List<Jugador> jugadores = new ArrayList<Jugador>();
		Pronostico prono;
		
		for(String lineaPronosticos : lineasPronosticos) {
			if(obviarPrimera) {
				obviarPrimera = false;
			}else {
				//System.out.println(lineaPronosticos);
				String[] separado = lineaPronosticos.split(",");
				
				//Creo tantas instancias de Jugador como los que indica en pronostico
				if(unicaVez) {
					player = separado[0];
					jugador = new Jugador(separado[0]);
					unicaVez=false;
					jugadores.add(jugador);
				}else {
					if(!separado[0].equals(player)) {
						jugador = new Jugador(separado[0]);
						player = separado[0];
						jugadores.add(jugador);
					}					
				}
								
				Equipo equipo1 = new Equipo(separado[2]);
				Equipo equipo2 = new Equipo(separado[6]);
				Partido partido = null;
				
				//bucle para identificar cada partido que coincida entre los resultados y los pronosticos
				//se podria usar un indice para este mismo fin
				for(Partido partidoCol : partidos) {
					//mismos contrincantes...
					if((partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) &&
							equipo2.getNombre().equals(equipo2.getNombre())) || 
							(partidoCol.getEquipo1().getNombre().equals(equipo2.getNombre()) &&
									equipo2.getNombre().equals(equipo1.getNombre())) &&
							//y misma ronda
							(partidoCol.getRonda() == Integer.parseInt(separado[1]))) {

						//si todo coincide, es el mismo partido, por ende duplico el objeto
						partido = partidoCol;
						
					}
					
				}
				
				Equipo equipo = null;
				
				ResultadoEnum resultado = null;
				
				//si gana el equipo de la izquierda...
				if("X".equals(separado[3]) && "".equals(separado[4]) && "".equals(separado[5])) {
					equipo = equipo1;
					resultado = ResultadoEnum.GANADOR;
				}
				//si hay un empate...
				if("".equals(separado[3]) && "X".equals(separado[4]) && "".equals(separado[5])) {
					equipo = equipo1;
					resultado = ResultadoEnum.EMPATE;
				}
				//si gana el equipo de la derecha
				if("".equals(separado[3]) && "".equals(separado[4]) && "X".equals(separado[5])) {
					equipo = equipo1;
					resultado = ResultadoEnum.PERDEDOR;
				}
				//creo el pronostico donde el constructor asigna valores para calculo
				prono = new Pronostico(jugador, partido, equipo, resultado);
				//calcula puntos
				prono.puntos();
			}
		}
		
		
		//recorriendo el array de jugadores voy imprimiendo sus puntos
		for(Jugador j : jugadores) {
			System.out.println(j.getNombreJugador()+ ": " + j.getPuntosJugador());
		}
		
		
	}

	ResultadoEnum resultado;
}
