package ar.utn.ap.pronosticos;

public class Partido {

	private Equipo equipo1;
	
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;
	
	public Partido(Equipo equipo1, Equipo equipo2) {
		
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		
	}
	
	public ResultadoEnum resultado() {
		
		if(equipo1.getGolesEquipo()==equipo2.getGolesEquipo()) return ResultadoEnum.EMPATE;
		
		if(equipo1.getGolesEquipo() > equipo2.getGolesEquipo()) {
			return ResultadoEnum.GANADOR;
		}else {
			return ResultadoEnum.PERDEDOR;
		}
	}
	
	public Equipo getEquipo1() {
		return equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	
}
