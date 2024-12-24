using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Data;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;

namespace EventMasterSoftDBManager
{
    public class DBManager
    {
        private string archivo = "connection_settings.txt";
        private static DBManager dbManager = new DBManager();
        private MySqlConnection con;

        private DBManager()
        {
            string ruta = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, archivo);
            string cadena = "";
            if (File.Exists(ruta))
            {
                foreach (string line in File.ReadLines(ruta))
                {
                    cadena += line;
                }
            }
            con = new MySqlConnection(cadena);
        }

        public static DBManager Instance
        {
            get
            {
                return dbManager;
            }
        }

        public MySqlConnection Connection
        {
            get
            {
                AbrirConexion();
                return con;
            }
        }

        //Método para abrir la conexión
        public void AbrirConexion()
        {
            if (con.State != ConnectionState.Open)
            {
                con.Open();
            }
        }

        //Método para cerrar la conexión
        public void CerrarConexion()
        {
            if (con.State != ConnectionState.Closed)
            {
                con.Close();
            }
        }

        //Método para ejecutar un procedimiento almacenado insert/update/delete
        public int EjecutarProcedimiento(string nombreProcedimiento, MySqlParameter[] parameters, string nombreParametroSalida)
        {
            int resultado = 0;
            try
            {
                AbrirConexion();
                MySqlCommand comando = new MySqlCommand();
                comando.Connection = con;
                comando.CommandText = nombreProcedimiento;
                comando.CommandType = CommandType.StoredProcedure;

                // Añade los parámetros de entrada si existen
                if (parameters != null)
                    comando.Parameters.AddRange(parameters);

                // Ejecuta el procedimiento
                resultado = comando.ExecuteNonQuery();

                // Si existe parámetro de salida, obtenemos el valor en el resultado
                if (nombreParametroSalida != null)
                    resultado = Convert.ToInt32(comando.Parameters[nombreParametroSalida].Value);
            }
            catch (MySqlException ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                CerrarConexion();
            }
            return resultado;
        }

        //Método para ejecutar un procedimiento almacenado select
        public MySqlDataReader EjecutarProcedimientoLectura(string nombreProcedimiento, MySqlParameter[] parameters)
        {
            MySqlDataReader lector = null;
            try
            {
                AbrirConexion();
                MySqlCommand comando = new MySqlCommand();
                comando.Connection = con;
                comando.CommandText = nombreProcedimiento;
                comando.CommandType = CommandType.StoredProcedure;

                // Añade los parámetros de entrada si existen
                if (parameters != null)
                    comando.Parameters.AddRange(parameters);

                lector = comando.ExecuteReader();

            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            return lector;
        }
    }
}
