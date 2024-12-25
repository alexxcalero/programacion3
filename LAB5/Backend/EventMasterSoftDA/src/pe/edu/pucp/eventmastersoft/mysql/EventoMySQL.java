package pe.edu.pucp.eventmastersoft.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import pe.edu.pucp.eventmastersoft.config.DBManager;
import pe.edu.pucp.eventmastersoft.dao.EventoDAO;
import pe.edu.pucp.eventmastersoft.model.Evento;
import pe.edu.pucp.eventmastersoft.model.Productora;
import pe.edu.pucp.eventmastersoft.model.TipoEvento;
import java.sql.Types;

public class EventoMySQL implements EventoDAO{

    private ResultSet rs;

    @Override
    public int insertar(Evento evento) {
        HashMap<String,Object> parametrosEntrada = new HashMap<>();
		parametrosEntrada.put("_fid_productora", evento.getProductora().getIdProductora());
		parametrosEntrada.put("_fid_clasificacion", evento.getClasificacion());
		parametrosEntrada.put("_nombre", evento.getNombre());
		parametrosEntrada.put("_costo_realizacion", evento.getCostoRealizacion());
		parametrosEntrada.put("_tipo_evento", evento.getTipoEvento());
		parametrosEntrada.put("_fecha_realizacion", evento.getFechaRealizacion());
		parametrosEntrada.put("_descripcion", evento.getDescripcion());
		parametrosEntrada.put("_permite_reingreso", evento.isPermiteReingreso());
		parametrosEntrada.put("_permite_grabacion", evento.isPermiteGrabacion());
		parametrosEntrada.put("_banner_promocional", evento.getBannerPromocional());

		HashMap<String,Object> parametrosSalida = new HashMap<>();
		parametrosSalida.put("_id_evento", Types.INTEGER);

		DBManager.getInstance().ejecutarProcedimiento("INSERTAR_EVENTO", parametrosEntrada, parametrosSalida);
        return (int) parametrosSalida.get("_id_evento");
    }

    @Override
    public ArrayList<Evento> listarPorNombre(String nombre) {
        ArrayList<Evento> eventos = new ArrayList<>();
		HashMap<String,Object> parametrosEntrada = new HashMap<>();
		parametrosEntrada.put("_nombre", nombre);
		rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_EVENTOS_X_NOMBRE", parametrosEntrada);
		try{
			while(rs.next()){
			   Evento evento = new Evento();
			   evento.setIdEvento(rs.getInt("id_evento"));
			   evento.setNombre(rs.getString("nombre_evento"));
			   evento.setFechaRealizacion(rs.getDate("fecha_realizacion"));
			   Productora productora = new Productora();
			   productora.setIdProductora(rs.getInt("id_productora"));
			   productora.setNombre(rs.getString("nombre_productora"));
			   evento.setProductora(productora);
			   eventos.add(evento);
			}
		}catch(SQLException ex){
			System.out.println("Error leyendo datos: " + ex.getMessage());
		}
		return eventos;
    }

    @Override
    public Evento obtenerPorId(int idEvento) {
        Evento evento = new Evento();
		HashMap<String,Object> parametrosEntrada = new HashMap<>();
		parametrosEntrada.put("_id_evento", idEvento);
		rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_EVENTO_X_ID", parametrosEntrada);
		try{
			if(rs.next()){
			   evento.setIdEvento(rs.getInt("id_evento"));
			   evento.setClasificacion(rs.getString("id_clasificacion").charAt(0));
			   evento.setCostoRealizacion(rs.getDouble("costo_realizacion"));
			   evento.setTipoEvento(TipoEvento.valueOf(rs.getString("tipo_evento")));
			   evento.setNombre(rs.getString("nombre_evento"));
			   evento.setDescripcion(rs.getString("descripcion"));
			   evento.setBannerPromocional(rs.getBytes("banner_promocional"));
			   evento.setPermiteGrabacion(rs.getBoolean("permite_grabacion"));
			   evento.setPermiteReingreso(rs.getBoolean("permite_reingreso"));
			   evento.setFechaRealizacion(rs.getDate("fecha_realizacion"));
			   Productora productora = new Productora();
			   productora.setIdProductora(rs.getInt("id_productora"));
			   productora.setNombre(rs.getString("nombre_productora"));
			   evento.setProductora(productora);
			   evento.setActivo(true);
			}
		}catch(SQLException ex){
			System.out.println("Error leyendo datos: " + ex.getMessage());
		}
		return evento;
    }
}
