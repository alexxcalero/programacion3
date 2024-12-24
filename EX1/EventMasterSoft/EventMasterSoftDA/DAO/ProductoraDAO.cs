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
        BindingList<Productora> listarTodas();
    }
}
