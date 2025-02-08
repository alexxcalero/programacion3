/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softrh.compras.bo;

import java.util.Date;
import pe.edu.pucp.softrh.compras.dao.TotalesDAO;
import pe.edu.pucp.softrh.compras.daoimp.TotalesDAOImp;
import pe.edu.pucp.softrh.compras.model.Totales;

public class TotalesBO {
    private TotalesDAO totalesDAO;
    
    public TotalesBO(){
        this.totalesDAO = new TotalesDAOImp();
    }
    
    public Totales obtenerValoresTotales(){
        return this.totalesDAO.obtenerValoresTotales();
    }
    
    public Totales obtenerValoresTotalesPorMes(int anho, int mes){
        return this.totalesDAO.obtenerValoresTotalesPorMes(anho, mes);
    }
}
