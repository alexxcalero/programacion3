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
    public class EventoMySQL : EventoDAO
    {
        private MySqlDataReader lector;

        public int insertar(Evento evento)
        {
            int resultado = 0;
            MySqlParameter[] parametros = new MySqlParameter[11];
            parametros[0] = new MySqlParameter("_id_evento", MySqlDbType.Int32);
            parametros[0].Direction = System.Data.ParameterDirection.Output;
            parametros[1] = new MySqlParameter("_fid_productora", evento.Productora.IdProductora);
            parametros[2] = new MySqlParameter("_fid_clasificacion", evento.Clasificacion);
            parametros[3] = new MySqlParameter("_nombre", evento.Nombre);
            parametros[4] = new MySqlParameter("_costo_realizacion", evento.CostoRealizacion);
            parametros[5] = new MySqlParameter("_tipo_evento", evento.TipoEvento.ToString());
            parametros[6] = new MySqlParameter("_fecha_realizacion", evento.FechaRealizacion);
            parametros[7] = new MySqlParameter("_descripcion", evento.Descripcion);
            parametros[8] = new MySqlParameter("_permite_reingreso", evento.PermiteReingreso);
            parametros[9] = new MySqlParameter("_permite_grabacion", evento.PermiteGrabacion);
            parametros[10] = new MySqlParameter("_banner_promocional", evento.BannerPromocional);
            evento.IdEvento = DBManager.Instance.EjecutarProcedimiento("INSERTAR_EVENTO", parametros, "_id_evento");
            resultado = evento.IdEvento;
            return resultado;
        }

        public BindingList<Evento> listarPorNombre(string nombre)
        {
            BindingList<Evento> eventos = new BindingList<Evento>();
            MySqlParameter[] parametros = new MySqlParameter[1];
            parametros[0] = new MySqlParameter("_nombre", nombre);
            lector = DBManager.Instance.EjecutarProcedimientoLectura("LISTAR_EVENTOS_X_NOMBRE", parametros);
            while (lector.Read())
            {
                Evento evento = new Evento();

                evento.IdEvento = lector.GetInt32("id_evento");
                evento.Productora = new Productora();
                evento.Productora.IdProductora = lector.GetInt32("id_productora");
                evento.Productora.Nombre = lector.GetString("nombre_productora");
                evento.Nombre = lector.GetString("nombre_evento");
                evento.FechaRealizacion = lector.GetDateTime("fecha_realizacion");

                eventos.Add(evento);
            }
            DBManager.Instance.CerrarConexion();
            return eventos;
        }

        public Evento obtenerPorId(int idEvento)
        {
            Evento evento = new Evento();
            MySqlParameter[] parametros = new MySqlParameter[1];
            parametros[0] = new MySqlParameter("_id_evento", idEvento);
            lector = DBManager.Instance.EjecutarProcedimientoLectura("OBTENER_EVENTO_X_ID", parametros);
            while (lector.Read())
            {
                evento.IdEvento = lector.GetInt32("id_evento");
                evento.Productora = new Productora();
                evento.Productora.IdProductora = lector.GetInt32("id_productora");
                evento.Productora.Nombre = lector.GetString("nombre_productora");
                evento.Clasificacion = lector.GetChar("id_clasificacion");
                evento.Nombre = lector.GetString("nombre_evento");
                evento.CostoRealizacion = lector.GetDouble("costo_realizacion");
                evento.TipoEvento = (TipoEvento)Enum.Parse(typeof(TipoEvento), lector.GetString("tipo_evento"));
                evento.FechaRealizacion = lector.GetDateTime("fecha_realizacion");
                evento.Descripcion = lector.GetString("descripcion");
                evento.PermiteReingreso = lector.GetBoolean("permite_reingreso");
                evento.PermiteGrabacion = lector.GetBoolean("permite_grabacion");
                evento.BannerPromocional = (byte[])lector["banner_promocional"];
                evento.Activo = lector.GetBoolean("activo");
            }
            DBManager.Instance.CerrarConexion();
            return evento;
        }
    }
}
