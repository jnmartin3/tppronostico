package ar.utn.ap.pronosticos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//colecciones de partidos para almacenar todos los existentes en los archivos
		Collection<Partido> partidos = new ArrayList<Partido>();
		
		//traer path de args, abrir archivo y guardar en lista lineasResultados
		Path pathResultados = Paths.get(args[0]);
		Path pathPronosticos = Paths.get(args[1]);

		List<String> lineasResultados = null;
		List<String> lineasPronosticos = null;
		
		//abro los archivos y los guardo en las listas lineasResultado y lineasPronosticos
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
		
		for(String lineaResultados : lineasResultados) {
			if(obviarPrimera) {
				obviarPrimera = false;
			}else {
				
				//System.out.println(lineaResultados);				// mostrar contenido del mismo...
				String[] separado = lineaResultados.split(",");		//cortar por su separadores coma y guardar en vector
				
				//Crear los objetos Equipo con sus atributos
				Equipo equipo1 = new Equipo(separado[0],Integer.parseInt(separado[1]),"Seleccion nacional");
				Equipo equipo2 = new Equipo(separado[3],Integer.parseInt(separado[2]), "Seleccion nacional");
				
				//instanciamos un Partido con los equipos contendientes
				Partido partido = new Partido(equipo1, equipo2);
				partidos.add(partido);
				
				//mostramos el resultado del calculo
				//System.out.println(partido.resultado());
				
			}
		}
		
		//------------------- Archivo de PRONOSTICOS ---------------------------
		
		obviarPrimera = true;
		
		int puntos = 0;
		
		for(String lineaPronosticos : lineasPronosticos) {
			if(obviarPrimera) {
				obviarPrimera = false;
			}else {
				//System.out.println(lineaPronosticos);
				String[] separado = lineaPronosticos.split(",");
				Equipo equipo1 = new Equipo(separado[0]);
				Equipo equipo2 = new Equipo(separado[4]);
				Partido partido = null;
				
				//bucle para identificar cada partido que coincida entre los resultados y los pronosticos
				//se podria usar un indice para este mismo fin
				for(Partido partidoCol : partidos) {
					if((partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) &&
							equipo2.getNombre().equals(equipo2.getNombre())) || 
							(partidoCol.getEquipo1().getNombre().equals(equipo2.getNombre()) &&
									equipo2.getNombre().equals(equipo1.getNombre()))) {
						
						//si los nombres coinciden, es el mismo partido, por ende duplico el objeto
						partido = partidoCol;
						
					}
					
				}
				
				Equipo equipo = null;
				
				ResultadoEnum resultado = null;
				
				//si gana el equipo de la derecha...
				if("X".equals(separado[1]) && "".equals(separado[2]) && "".equals(separado[3])) {
					equipo = equipo1;
					resultado = ResultadoEnum.GANADOR;
				}
				//si hay un empate...
				if("".equals(separado[1]) && "X".equals(separado[2]) && "".equals(separado[3])) {
					equipo = equipo1;
					resultado = ResultadoEnum.EMPATE;
				}
				//si gana el equipo de la izquierda
				if("".equals(separado[1]) && "".equals(separado[2]) && "X".equals(separado[3])) {
					equipo = equipo1;
					resultado = ResultadoEnum.PERDEDOR;
				}
				//creo el pronostico donde el constructor asigna valores para calculo
				Pronostico prono = new Pronostico(partido, equipo, resultado);
				//calcula puntos
				puntos += prono.puntos();
			}
		}
		
		System.out.println("Puntaje = " + puntos);
	
	}

	ResultadoEnum resultado;
}
