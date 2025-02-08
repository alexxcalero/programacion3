/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softrh.compras.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.Date;
import pe.edu.pucp.softrh.compras.model.Totales;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;
import pe.edu.pucp.softrh.rmi.interfaces.PrendaSeleccionadaBO;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.TotalesBO;

@WebService(serviceName = "TotalesWS", targetNamespace = "softrh.services")
public class TotalesWS {

	private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private TotalesBO totalesBO;

    public TotalesWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("totalesBO");
        try {
            this.totalesBO = (TotalesBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(TotalesWS.class.getName()).log(Level.SEVERE, null, ex);
            this.totalesBO = null; // En caso de error, inicializamos a null
        }
    }

    @WebMethod(operationName = "obtenerValoresTotales")
    public Totales obtenerValoresTotales() {
        Totales retorno = null;
        try {
            retorno = totalesBO.obtenerValoresTotales();
        } catch (RemoteException ex) {
            Logger.getLogger(TotalesWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerValoresTotalesPorMes")
    public Totales obtenerValoresTotalesPorMes(int anho, int mes) {
        Totales retorno = null;
        try {
            retorno = totalesBO.obtenerValoresTotalesPorMes(anho, mes);
        } catch (RemoteException ex) {
            Logger.getLogger(TotalesWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

