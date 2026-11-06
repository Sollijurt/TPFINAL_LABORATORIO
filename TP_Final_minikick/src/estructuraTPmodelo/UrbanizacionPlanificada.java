package estructuraTPmodelo;

public class UrbanizacionPlanificada extends Barrio {
	private String numeroLey;
	private boolean tieneConexionAgua;
	
	@Override
	public float calculoDensidad() {
		return 0;
	}
	@Override
	public boolean criticidadHabitacional() {
		return false;
	}
}
