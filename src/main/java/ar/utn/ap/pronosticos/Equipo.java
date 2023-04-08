package ar.utn.ap.pronosticos;

public class Equipo {

	public Equipo(String nombre) {
		this.nombre = nombre;
	}
	
	public Equipo(String nombre, int parseInt, String descripcion) {
		
		this.nombre = nombre;
		this.golesEquipo = parseInt;
		this.setDescripcion(descripcion);
		
	}

	public int getGolesEquipo() {
		return golesEquipo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private String nombre;
	
	private int golesEquipo;
	
	private String descripcion;
	
}
