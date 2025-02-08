using RHStoreBaseBO.ServiciosWeb;
using RHStoreBaseBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStoreComprasBO
{
    public class TotalesBO : BaseBO
    {
        public totales obtenerValoresTotales()
        {
            return TotalesWS.obtenerValoresTotales();
        }
        public totales obtenerValoresTotalesPorMes(int anho,int mes)
        {
            return TotalesWS.obtenerValoresTotalesPorMes(anho,mes);
        }

    }
}
