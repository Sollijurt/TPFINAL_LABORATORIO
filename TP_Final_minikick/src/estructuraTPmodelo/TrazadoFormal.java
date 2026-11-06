package estructuraTPmodelo;

public class TrazadoFormal extends Barrio{
	private int cantidadEdificios;
	private float porcentajeAsfalto;
	
	@Override
	public float calculoDensidad() {
		return 0;
	}
	@Override
	public boolean criticidadHabitacional() {
		Boolean aux = false;
		if(porcentajeAsfalto > 95) {
			aux =true;
		}
		return aux;
	}
}
