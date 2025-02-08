package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pe.edu.pucp.softrh.compras.model.Totales;
import pe.edu.pucp.softrh.rmi.interfaces.TotalesBO;

public class TotalesBOImpl extends UnicastRemoteObject implements TotalesBO{

    private pe.edu.pucp.softrh.compras.bo.TotalesBO totalesBO;

    public TotalesBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.totalesBO = new pe.edu.pucp.softrh.compras.bo.TotalesBO();
    }

    @Override
    public Totales obtenerValoresTotales() throws RemoteException {
        return this.totalesBO.obtenerValoresTotales();
    }

    @Override
    public Totales obtenerValoresTotalesPorMes(int anho, int mes) throws RemoteException {
        return this.totalesBO.obtenerValoresTotalesPorMes(anho, mes);
    }
}
