using EventMasterSoftModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftDA.DAO
{
    public interface ProductoraDAO
    {
        int insertar(Productora productora);
        int modificar(Productora productora);
        int eliminar(int idProductora);
        Productora obtenerPorId(int idProductora);
        BindingList<Productora> listarTodas();
    }
}
