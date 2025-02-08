package pe.edu.pucp.softrh.compras.dao;

import pe.edu.pucp.softrh.compras.model.Totales;

public interface TotalesDAO {
    Totales obtenerValoresTotales();

    Totales obtenerValoresTotalesPorMes(int anho, int mes);
}
