package estructuraTPmodelo;

import java.util.ArrayList;

public class Comuna {
	private String nombre;
	private int numeroComuna;
	
	ArrayList<Barrio> barrios = new ArrayList<>();
	
	public boolean esDeAtencionPrioritaria() {
		return true;
	}
}
