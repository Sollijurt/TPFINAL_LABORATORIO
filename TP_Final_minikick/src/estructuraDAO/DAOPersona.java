package estructuraDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import estructuraTPmodelo.Comuna;
import estructuraTPmodelo.Persona;
import estructuraTPmodelo.TipoSituacionLaboral;

public class DAOPersona{
	private Conexion conect;
	
	public DAOPersona(Conexion conectar) {
		this.conect = conectar;
	}
	
	public boolean agregar(Persona p, String barrio) {
		boolean sePudoCargar = true;
		Connection conexion = null;
		//SANTY MODIFICA LOS NOMBRES DE COMO TENES LA FK DE BARRIO Y TIPOSITUACIONLABORAL EN PERSONA
		String sql = "INSERT INTO persona (nombrePersona , fechaNacimientoPersona, dniPersona , idBarrio, idTipoSituacionLaboral)VALUES(?,?,?,(SELECT idBarrio FROM barrio WHERE nombreBarrio =?  ),(SELECT idtipoSituacionLaboral FROM tipoSituacionLaboral WHERE nombreTipoSituacionLaboral  = LOWER(?)));";
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, p.getNombre());
			stmt.setDate(2, Date.valueOf(p.getFechaNacimiento()));
			stmt.setString(3, p.getDNI());
			stmt.setString(4, barrio);
			stmt.setString(5, p.getTipo().toString());
			
			stmt.executeUpdate();
			
			stmt.close();
		}catch(SQLException e){
			sePudoCargar = false;
			e.printStackTrace();
		
		}
		return sePudoCargar;
	}
	
	public ArrayList<Persona> consultar()
	{
		String sql = "SELECT p.nombrePersona , p.fechaNacimientoPersona , p.dniPersona, b.nombreBarrio , t.idTipoSituacionLaboral FROM persona p INNER JOIN barrio b ON p.idBarrio = b.idBarrio INNER JOIN tipoSituacionLaboral t ON p.idtipoSituacionLaboral = t.idtipoSituacionLaboral";
		
		Connection conexion = null;
		ArrayList<Persona> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
			Statement stmt = conexion.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);	
			while(rs.next())
			{
				solucion.add(crearPersona(rs));
			}
			rs.close();
			stmt.close();
		    conect.cerrarConexion();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
	}
	
	public ArrayList<Persona> consultarPorNombreBarrio(String nombreBarrio)
	{
		String sql = "SELECT p.nombrePersona , p.fechaNacimientoPersona , p.dniPersona, b.nombreBarrio , t.idTipoSituacionLaboral FROM persona p INNER JOIN barrio b ON p.idBarrio = b.idBarrio INNER JOIN tipoSituacionLaboral t ON p.idtipoSituacionLaboral = t.idtipoSituacionLaboral where p.idBarrio = (Select idBarrio from barrio where nombreBarrio = ?)";
		
		Connection conexion = null;
		ArrayList<Persona> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, nombreBarrio);
		    ResultSet rs = stmt.executeQuery();	
			while(rs.next())
			{
				solucion.add(crearPersona(rs));
			}
			rs.close();
			stmt.close();
		    conect.cerrarConexion();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
	}
	/*public ArrayList<Persona> consultarPorNombre(String dniBusqueda)
	{
		String sql = "SELECT p.nombrePersona , p.fechaNacimientoPersona , p.dniPersona, b.nombreBarrio , t.nombreTipoSituacionLaboral FROM persona p INNER JOIN barrio b ON p.barrio_idBarrio = b.idBarrio INNER JOIN tipoSituacionLaboral t ON p.tipoSituacionLaboral_idtipoSituacionLaboral = t.idtipoSituacionLaboral WHERE dni = ?";
		
		Connection conexion = null;
		ArrayList<Persona> solucion = new ArrayList<>();
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dniBusqueda);
		    ResultSet rs = stmt.executeQuery(sql);	
			while(rs.next())
			{
				String nombre = rs.getString("nombrePersona");
				LocalDate fechaNacimiento = rs.getDate("fechaNacimientoPersona").toLocalDate();
				String dni = rs.getString("dniPersona");
				TipoSituacionLaboral tipo = TipoSituacionLaboral.obtenerTipoSituacionLaboral(rs.getInt("idTipoSituacionLaboral"));
				Persona p = new Persona(nombre,fechaNacimiento,dni,tipo);
				solucion.add(p);
			}
			rs.close();
			stmt.close();
		    conexion.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return solucion;
	}
	*/
	
	public boolean actualizar(Persona p, String nombreBarrio, String dni)
	{
		boolean sePudoCargar = true;
		String sql = "UPDATE persona SET nombrePersona = ?, fechaNacimientoPersona = ? , dniPersona = ? , idBarrio = (SELECT idBarrio FROM barrio WHERE nombreBarrio = ?), idtipoSituacionLaboral = (SELECT idtipoSituacionLaboral FROM tipoSituacionLaboral where nombreTipoSituacionLaboral = ?) WHERE dniPersona =  ?";
		Connection conexion = null;
		Date fechaNacimiento = Date.valueOf(p.getFechaNacimiento());
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1,p.getNombre());
			stmt.setDate(2, fechaNacimiento);
			stmt.setString(3,p.getDNI());
			stmt.setString (4 , nombreBarrio);
			stmt.setString(5, p.getTipo().toString());
			stmt.setString(6, dni);
			
			stmt.executeUpdate();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			sePudoCargar = false;
			e.printStackTrace();
		} 

		
		return sePudoCargar;
	}
	
	public void eliminar(String dni)
	{
		String sql = "DELETE FROM persona WHERE dniPersona = ?";
		Connection conexion = null;
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);
			stmt.executeUpdate();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public ArrayList<Persona> consultaPorAproximacion(String nombreAproximado)
	{
		String sql = "SELECT p.nombrePersona , p.fechaNacimientoPersona , p.dniPersona, b.nombreBarrio , t.idTipoSituacionLaboral FROM persona p INNER JOIN barrio b ON p.idBarrio = b.idBarrio INNER JOIN tipoSituacionLaboral t ON p.idtipoSituacionLaboral = t.idtipoSituacionLaboral where nombrePersona like ?";
		Connection conexion = null;
		ArrayList<Persona> solucion = new ArrayList<>();
	
		try {
			conexion = conect.conectar();
			PreparedStatement stmt = conexion.prepareStatement(sql);
		    stmt.setString(1, nombreAproximado + "%");
		    ResultSet rs = stmt.executeQuery();	
		    
			while(rs.next())
			{
				solucion.add(crearPersona(rs));
			}
			
		    rs.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return solucion;
	}
	
	private Persona crearPersona(ResultSet rs) throws SQLException {
		String nombre = rs.getString("nombrePersona");
		LocalDate fechaNacimiento = rs.getDate("fechaNacimientoPersona").toLocalDate();
		String dni = rs.getString("dniPersona");
		TipoSituacionLaboral tipo = TipoSituacionLaboral.obtenerTipoSituacionLaboral(rs.getInt("idTipoSituacionLaboral")-1);
		Persona p = new Persona(nombre,fechaNacimiento,dni,tipo);
		
		return p;
	}
	
	public void actualizarElBarrioAlBorrar(String nombreBarrio, String nuevoNombre) {
		String sql = "UPDATE Persona set idBarrio = (select idBarrio from barrio where nombreBarrio = ?) where idBarrio = (SELECT idBarrio FROM Barrio WHERE nombreBarrio = ?);";
	    Connection conexion = null;
	    try {
	        conexion = conect.conectar();
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        stmt.setString(1, nuevoNombre);
	        stmt.setString(2, nombreBarrio);
	        stmt.executeUpdate();
	        
	        conect.cerrarConexion();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}