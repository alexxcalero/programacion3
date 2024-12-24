using EventMasterSoftDA.DAO;
using EventMasterSoftDBManager;
using EventMasterSoftModel;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftDA.MySQL
{
    public class ProductoraMySQL : ProductoraDAO
    {
        private MySqlDataReader lector;
        public BindingList<Productora> listarTodas()
        {
            BindingList<Productora> productoras = new BindingList<Productora>();
            lector = DBManager.Instance.EjecutarProcedimientoLectura("LISTAR_PRODUCTORAS_TODAS", null);
            while (lector.Read())
            {
                Productora productora = new Productora();

                productora.IdProductora = lector.GetInt32("id_productora");
                productora.Nombre = lector.GetString("nombre");
                productora.Activa = lector.GetBoolean("activa");

                productoras.Add(productora);
            }
            DBManager.Instance.CerrarConexion();
            return productoras;
        }
    }
}
