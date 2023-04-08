package ar.utn.ap.pronosticos;

public class Pronostico {

	private Jugador jugador;
	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;
	
	
	
	public Pronostico(Jugador jugador, Partido partido, Equipo equipo, ResultadoEnum resultado) {
		this.jugador = jugador;
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}


	public Partido getPartido() {
		return partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public ResultadoEnum getResultado() {
		return resultado;
	}

	public void puntos() {
		//TODO completar
		ResultadoEnum resultadoReal = partido.resultado();
		
		if(resultadoReal.equals(resultado)) {
			jugador.setPuntosJugador(1);
		}else {
			jugador.setPuntosJugador(0);
		}
	}
}
