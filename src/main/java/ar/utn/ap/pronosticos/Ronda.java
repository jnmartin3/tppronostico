package ar.utn.ap.pronosticos;

public class Ronda {

	private int nro;
	
	private Partido[] partidos;
	
	public Ronda(Partido partido) {
		this.nro = partido.getRonda();
	}
	
	public void agregarPartido() {
		
		
		
	}
	
	public int puntos() {
		
		return 0;
	}
	
	public int getRonda() {
		return nro;
	}
	
}
