using MedicalSoftDA.DAO;
using MedicalSoftDBManager;
using MedicalSoftModel;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace MedicalSoftDA.DAOImpl
{
	public class SalaEspecializadaDAOImpl : SalaEspecializadaDAO
	{
		private MySqlConnection con;
		private MySqlCommand cmd;
		private MySqlDataReader dr;
		int SalaEspecializadaDAO.insertar(SalaEspecializada salaEspecializada)
		{
			int resultado = 0;
			try
			{
				con = DBManager.Instance.Connection;
				con.Open();
				cmd = new MySqlCommand();
				cmd.Connection = con;
				cmd.CommandType = System.Data.CommandType.StoredProcedure;
				cmd.CommandText = "INSERTAR_SALA_ESPECIALIZADA";
				cmd.Parameters.Add("_id_sala_especializada", MySqlDbType.Int32).Direction = System.Data.ParameterDirection.Output;
				cmd.Parameters.AddWithValue("_espacio_en_m2", salaEspecializada.EspacioMetrosCuadrados);
				cmd.Parameters.AddWithValue("_torre", salaEspecializada.Torre);
				cmd.Parameters.AddWithValue("_piso", salaEspecializada.Piso);
				cmd.Parameters.AddWithValue("_nombre", salaEspecializada.Nombre);
				cmd.Parameters.AddWithValue("_tipo_sala", salaEspecializada.TipoSala.ToString());
				cmd.Parameters.AddWithValue("_posee_equipamiento_imagenologia", salaEspecializada.PoseeEquipamientoImagenologia);
				cmd.ExecuteNonQuery();
				salaEspecializada.IdAmbienteClinico = Int32.Parse(cmd.Parameters["_id_sala_especializada"].Value.ToString());
				resultado = salaEspecializada.IdAmbienteClinico;
			} catch (Exception ex)
			{
				throw new Exception(ex.Message);
			} finally
			{
				try
				{
					con.Close();
				} catch(Exception ex)
				{
					throw new Exception(ex.Message);
				}
			}
			return resultado;
		}
		int SalaEspecializadaDAO.modificar(SalaEspecializada salaEspecializada)
		{
			int resultado = 0;
			try
			{
				con = DBManager.Instance.Connection;
				con.Open();
				cmd = new MySqlCommand();
				cmd.Connection = con;
				cmd.CommandType = System.Data.CommandType.StoredProcedure;
				cmd.CommandText = "MODIFICAR_SALA_ESPECIALIZADA";
				cmd.Parameters.AddWithValue("_id_sala_especializada", salaEspecializada.IdAmbienteClinico);
				cmd.Parameters.AddWithValue("_espacio_en_m2", salaEspecializada.EspacioMetrosCuadrados);
				cmd.Parameters.AddWithValue("_torre", salaEspecializada.Torre);
				cmd.Parameters.AddWithValue("_piso", salaEspecializada.Piso);
				cmd.Parameters.AddWithValue("_nombre", salaEspecializada.Nombre);
				cmd.Parameters.AddWithValue("_tipo_sala", salaEspecializada.TipoSala.ToString());
				cmd.Parameters.AddWithValue("_posee_equipamiento_imagenologia", salaEspecializada.PoseeEquipamientoImagenologia);
				resultado = cmd.ExecuteNonQuery();
			}
			catch (Exception ex)
			{
				throw new Exception(ex.Message);
			}
			finally
			{
				try
				{

				}
				catch (Exception ex)
				{
					throw new Exception(ex.Message);
				}
			}
			return resultado;
		}
		int SalaEspecializadaDAO.eliminar(int idSalaEspecializada)
		{
			int resultado = 0;
			try
			{
				con = DBManager.Instance.Connection;
				con.Open();
				cmd = new MySqlCommand();
				cmd.Connection = con;
				cmd.CommandType = System.Data.CommandType.StoredProcedure;
				cmd.CommandText = "ELIMINAR_SALA_ESPECIALIZADA";
				cmd.Parameters.AddWithValue("_id_sala_especializada", idSalaEspecializada);
				resultado = cmd.ExecuteNonQuery();
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

		BindingList<SalaEspecializada> SalaEspecializadaDAO.listarTodas()
		{
			BindingList<SalaEspecializada> salasEspecializadas = new BindingList<SalaEspecializada>();
			try
			{
				con = DBManager.Instance.Connection;
				con.Open();
				cmd = new MySqlCommand();
				cmd.Connection = con;
				cmd.CommandType = System.Data.CommandType.StoredProcedure;
				cmd.CommandText = "LISTAR_SALAS_ESPECIALIZADAS_TODAS";
				dr = cmd.ExecuteReader();
				while (dr.Read())
				{
					SalaEspecializada sala = new SalaEspecializada();
					sala.IdAmbienteClinico = dr.GetInt32("id_sala_especializada");
					sala.EspacioMetrosCuadrados = dr.GetDouble("espacio_en_m2");
					sala.Torre = dr.GetChar("torre");
					sala.Piso = dr.GetInt32("piso");
					sala.Nombre = dr.GetString("nombre");
					sala.TipoSala = (TipoSala)Enum.Parse(typeof(TipoSala), dr.GetString("tipo_sala"));
					sala.PoseeEquipamientoImagenologia = dr.GetBoolean("posee_equipamiento_imagenologia");
					sala.Activo = true;
					salasEspecializadas.Add(sala);
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
			return salasEspecializadas;
		}

		SalaEspecializada SalaEspecializadaDAO.obtenerPorId(int idSalaEspecializada)
		{
			SalaEspecializada sala = new SalaEspecializada();
			try
			{
				con = DBManager.Instance.Connection;
				con.Open();
				cmd = new MySqlCommand();
				cmd.Connection = con;
				cmd.CommandType = System.Data.CommandType.StoredProcedure;
				cmd.CommandText = "OBTENER_SALA_ESPECIALIZADA_X_ID";
				cmd.Parameters.AddWithValue("_id_sala_especializada", idSalaEspecializada);
				dr = cmd.ExecuteReader();
				if(dr.Read())
				{
					sala.IdAmbienteClinico = dr.GetInt32("id_sala_especializada");
					sala.EspacioMetrosCuadrados = dr.GetDouble("espacio_en_m2");
					sala.Torre = dr.GetChar("torre");
					sala.Piso = dr.GetInt32("piso");
					sala.Nombre = dr.GetString("nombre");
					sala.TipoSala = (TipoSala)Enum.Parse(typeof(TipoSala), dr.GetString("tipo_sala"));
					sala.PoseeEquipamientoImagenologia = dr.GetBoolean("posee_equipamiento_imagenologia");
					sala.Activo = true;
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
				} catch(Exception ex)
				{
					throw new Exception (ex.Message);
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
			return sala;
		}
	}
}
