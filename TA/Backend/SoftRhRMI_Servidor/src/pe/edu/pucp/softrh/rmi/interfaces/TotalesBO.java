package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pe.edu.pucp.softrh.compras.model.Totales;

public interface TotalesBO extends Remote{
    Totales obtenerValoresTotales() throws RemoteException;

    Totales obtenerValoresTotalesPorMes(int anho, int mes) throws RemoteException;
}
