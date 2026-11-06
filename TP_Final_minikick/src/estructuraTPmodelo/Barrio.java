package estructuraTPmodelo;

import java.util.ArrayList;

public abstract class  Barrio {
	private String nombre;
	private float superficie;
	private ArrayList<Persona> personas = new ArrayList<>();
	
	public abstract float calculoDensidad() ;
	
	public abstract boolean criticidadHabitacional();
}
