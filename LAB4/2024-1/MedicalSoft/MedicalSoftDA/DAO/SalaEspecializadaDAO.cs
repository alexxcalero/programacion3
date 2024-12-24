using MedicalSoftModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MedicalSoftDA.DAO
{
	public interface SalaEspecializadaDAO
	{
		int insertar(SalaEspecializada salaEspecializada);
		int modificar(SalaEspecializada salaEspecializada);
		int eliminar(int idSalaEspecializada);
		BindingList<SalaEspecializada> listarTodas();
		SalaEspecializada obtenerPorId(int idSalaEspecializada);
	}
}
