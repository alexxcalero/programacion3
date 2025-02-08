using RHStoreBaseBO;
using RHStoreBaseBO.ServiciosWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStoreUsuariosBO
{
	public class RecuperarContrasenhaBO : BaseBO
	{
		public int guardarToken(int idUsuario, string token, DateTime fechaExpiracion)
		{
			return RecuperarContrasenhaWS.guardarToken(idUsuario, token, fechaExpiracion);
		}

		public int obtenerIdUsuarioPorToken(string token)
		{
			return RecuperarContrasenhaWS.obtenerIdUsuarioPorToken(token);
		}
	}
}
