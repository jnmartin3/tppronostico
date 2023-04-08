package ar.utn.ap.pronosticos;

public class Jugador {

	private String nombreJugador;
	private int puntosJugador;
	
	public Jugador(String nombre) {
		nombreJugador = nombre;	
	}
	
	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public int getPuntosJugador() {
		return puntosJugador;
	}

	public void setPuntosJugador(int puntosJugador) {
		this.puntosJugador += puntosJugador;
	}
	

}
