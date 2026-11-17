package estructuraDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import estructuraTPmodelo.Barrios.ComplejoHabitacional;
import estructuraTPmodelo.Barrios.TrazadoFormal;
import estructuraTPmodelo.Barrios.UrbanizacionPlanificada;

public class DAOBarrio{
	private Conexion conect;
	
	public DAOBarrio(Conexion c) {
		conect = c;
	}
	

}
