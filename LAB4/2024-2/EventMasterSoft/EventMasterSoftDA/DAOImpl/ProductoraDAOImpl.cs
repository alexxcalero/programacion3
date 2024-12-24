using EventMasterSoftDA.DAO;
using EventMasterSoftDBManager;
using EventMasterSoftModel;
using MySql.Data.MySqlClient;
using System;
using System.CodeDom;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftDA.DAOImpl
{
    public class ProductoraDAOImpl : ProductoraDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader dr;
        int ProductoraDAO.insertar(Productora productora)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.Connection;
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.CommandText = "INSERTAR_PRODUCTORA";
                cmd.Parameters.Add("_id_productora", MySqlDbType.Int32).Direction = System.Data.ParameterDirection.Output;
                cmd.Parameters.AddWithValue("_nombre", productora.Nombre);
                cmd.Parameters.AddWithValue("_fecha_fundacion", productora.FechaFundacion);
                cmd.Parameters.AddWithValue("_numero_filiales_en_peru", productora.NumeroFilialesEnPeru);
                cmd.Parameters.AddWithValue("_presupuesto_anual", productora.PresupuestoAnual);
                cmd.Parameters.AddWithValue("_ofrece_alquiler_equipos", productora.OfreceAlquilerEquipos);
                cmd.Parameters.AddWithValue("_tipo_especializacion", productora.TipoEspecializacion.ToString());
                cmd.ExecuteNonQuery();
                productora.IdEmpresa = Int32.Parse(cmd.Parameters["_id_productora"].Value.ToString());
                resultado = productora.IdEmpresa;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                try
                {
                    con.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return resultado;
        }

        BindingList<Productora> ProductoraDAO.listarTodas()
        {
            BindingList<Productora> productoras = new BindingList<Productora>();
            try
            {
                con = DBManager.Instance.Connection;
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.CommandText = "LISTAR_PRODUCTORA_TODAS";
                dr = cmd.ExecuteReader();
                while (dr.Read())
                {
                    Productora productora = new Productora();
                    productora.IdEmpresa = dr.GetInt32("id_productora");
                    productora.Nombre = dr.GetString("nombre");
                    productora.FechaFundacion = dr.GetDateTime("fecha_fundacion");
                    productora.NumeroFilialesEnPeru = dr.GetInt32("numero_filiales_en_peru");
                    productora.PresupuestoAnual = dr.GetDouble("presupuesto_anual");
                    productora.OfreceAlquilerEquipos = dr.GetBoolean("ofrece_alquiler_equipos");
                    productora.TipoEspecializacion = (TipoEspecializacion)Enum.Parse(typeof(TipoEspecializacion), dr.GetString("tipo_especializacion"));
                    productora.Activa = true;
                    productoras.Add(productora);
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                try
                {
                    dr.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
                try
                {
                    con.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return productoras;
        }

        Productora ProductoraDAO.obtenerPorId(int idProductora)
        {
            Productora productora = new Productora();
            try
            {
                con = DBManager.Instance.Connection;
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.CommandText = "OBTENER_PRODUCTORA_X_ID";
                cmd.Parameters.AddWithValue("_id_productora", idProductora);
                dr = cmd.ExecuteReader();
                if(dr.Read())
                {
                    productora.IdEmpresa = dr.GetInt32("id_productora");
                    productora.Nombre = dr.GetString("nombre");
                    productora.FechaFundacion = dr.GetDateTime("fecha_fundacion");
                    productora.NumeroFilialesEnPeru = dr.GetInt32("numero_filiales_en_peru");
                    productora.PresupuestoAnual = dr.GetDouble("presupuesto_anual");
                    productora.OfreceAlquilerEquipos = dr.GetBoolean("ofrece_alquiler_equipos");
                    productora.TipoEspecializacion = (TipoEspecializacion)Enum.Parse(typeof(TipoEspecializacion), dr.GetString("tipo_especializacion"));
                    productora.Activa = true;
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                try
                {
                    dr.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
                try
                {
                    con.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return productora;
        }

        int ProductoraDAO.eliminar(int idProductora)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.Connection;
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.CommandText = "ELIMINAR_PRODUCTORA";
                cmd.Parameters.AddWithValue("_id_productora", idProductora);
                resultado = cmd.ExecuteNonQuery();
            } catch (Exception ex)
            {
                throw new Exception(ex.Message);
            } finally
            {
                try
                {
                    con.Close();
                }
                catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return resultado;
        }


        int ProductoraDAO.modificar(Productora productora)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.Connection;
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.CommandText = "MODIFICAR_PRODUCTORA";
                cmd.Parameters.AddWithValue("_id_productora", productora.IdEmpresa);
                cmd.Parameters.AddWithValue("_nombre", productora.Nombre);
                cmd.Parameters.AddWithValue("_fecha_fundacion", productora.FechaFundacion);
                cmd.Parameters.AddWithValue("_numero_filiales_en_peru", productora.NumeroFilialesEnPeru);
                cmd.Parameters.AddWithValue("_presupuesto_anual", productora.PresupuestoAnual);
                cmd.Parameters.AddWithValue("_ofrece_alquiler_equipos", productora.OfreceAlquilerEquipos);
                cmd.Parameters.AddWithValue("_tipo_especializacion", productora.TipoEspecializacion.ToString());
                resultado = cmd.ExecuteNonQuery();
            } catch(Exception ex)
            {
                throw new Exception(ex.Message);
            } finally
            {
                try
                {
                    con.Close();
                } catch (Exception ex)
                {
                    throw new Exception(ex.Message);
                }
            }
            return resultado;
        }
    }
}
