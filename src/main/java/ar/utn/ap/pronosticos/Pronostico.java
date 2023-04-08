package ar.utn.ap.pronosticos;

public class Pronostico {

	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;
	
	
	
	public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {
		super();
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

	public int puntos() {
		//TODO completar
		ResultadoEnum resultadoReal = partido.resultado();
		
		if(resultadoReal.equals(resultado)) {
			return 1;
		}else {
			return 0;
		}
	}
}
