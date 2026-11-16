package estructuraTPmodelo.Barrios;

public class UrbanizacionPlanificada extends Barrio {
	private String numeroLey;
	private boolean tieneConexionAgua;
	
	private final int DIVISOR_HECTAREAS = 10000;
	
	public UrbanizacionPlanificada(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public float calculoDensidad() {
		float cantidadHectareas = superficie / DIVISOR_HECTAREAS;
		float aux = personas.size() / cantidadHectareas;
		return aux;
	}
	@Override
	public boolean criticidadHabitacional() {
		boolean aux = false;
		if(tieneConexionAgua == false) {
			aux = true;
		}
		return aux;
	}
	public String getNombre() {
		return nombre;
	}
	public float getSuperficie() {
		return superficie;
	}
	public String getNumeroLey() {
		return numeroLey;
	}
	public boolean getTieneConexionAgua() {
		return tieneConexionAgua;
	}
}
