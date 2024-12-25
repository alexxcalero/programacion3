package pe.edu.pucp.eventmastersoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.eventmastersoft.dao.EventoDAO;
import pe.edu.pucp.eventmastersoft.model.Evento;
import pe.edu.pucp.eventmastersoft.mysql.EventoMySQL;

@WebService(serviceName = "EventoWS", targetNamespace = "http://services.eventmastersoft.pucp.edu.pe")
public class EventoWS {

    private EventoDAO daoEvento;

	@WebMethod(operationName = "insertarEvento")
	public int insertarEvento(@WebParam(name = "evento") Evento evento) {
		int resultado = 0;
		try{
			daoEvento = new EventoMySQL();
			resultado = daoEvento.insertar(evento);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return resultado;
	}

    @WebMethod(operationName = "listarEventosPorNombre")
    public ArrayList<Evento> listarEventosPorNombre(@WebParam(name = "nombre") String nombre) {
        ArrayList<Evento> eventos = null;
        try{
            daoEvento = new EventoMySQL();
            eventos = daoEvento.listarPorNombre(nombre);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return eventos;
    }

	@WebMethod(operationName = "obtenerEventoPorId")
	public Evento obtenerEventoPorId(@WebParam(name = "idEvento") int idEvento) {
		Evento evento = null;
		try{
			daoEvento = new EventoMySQL();
			evento = daoEvento.obtenerPorId(idEvento);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return evento;
	}
}
