package pe.edu.pucp.eventmastersoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.eventmastersoft.model.Evento;

public interface EventoDAO {
    int insertar(Evento evento);
    ArrayList<Evento> listarPorNombre(String nombre);
    Evento obtenerPorId(int idEvento);
}