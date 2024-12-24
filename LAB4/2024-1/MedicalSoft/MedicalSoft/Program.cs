/* Ingrese sus datos:
 * Codigo PUCP: 20206455
 * Nombre Completo: Alex Calero Revilla
 */

using MedicalSoftDA.DAO;
using MedicalSoftDA.DAOImpl;
using MedicalSoftModel;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MedicalSoft
{
    public class Program
    {
        private static SalaEspecializadaDAO daoSalaEspecializada;
        public static void Main(string[] args)
        {
            int resultado = 0;
            daoSalaEspecializada = new SalaEspecializadaDAOImpl();
            do
            {
                System.Console.WriteLine();
                System.Console.WriteLine("SISTEMA DE GESTION DE SALAS ESPECIALIZADAS");
                System.Console.WriteLine("---------------------------------------------------");
                System.Console.WriteLine("1. Registrar nueva sala especializada.");
                System.Console.WriteLine("2. Listar todas las salas especializadas.");
                System.Console.WriteLine("3. Obtener los datos de una sala especializada por id.");
                System.Console.WriteLine("4. Eliminar una sala especializada.");
                System.Console.WriteLine("5. Modificar una sala especializada.");
                System.Console.WriteLine("6. Salir del sistema de gestion.");
                System.Console.Write("Ingrese su opcion: ");
                resultado = Int32.Parse(System.Console.ReadLine());
                if (resultado == 1)
                {
                    SalaEspecializada sala = solicitarDatosRegistro();
					int instruccion = daoSalaEspecializada.insertar(sala);
					if (instruccion != 0)
					{
						System.Console.WriteLine("Se ha registrado con exito.");
					}
					else
					{
						System.Console.WriteLine("No se pudo registrar la sala especializada.");
					}
				}
                else if (resultado == 2)
                {
                    BindingList<SalaEspecializada> salas = daoSalaEspecializada.listarTodas();
                    foreach (SalaEspecializada sala in salas)
                    {
                        sala.mostrarDatos();
                    }
                }
                else if (resultado == 3)
                {
                    System.Console.Write("Ingrese el id de la sala cuyos datos desea visualizar: ");
                    int idSala = Int32.Parse(System.Console.ReadLine());
                    SalaEspecializada sala = daoSalaEspecializada.obtenerPorId(idSala);
                    sala.mostrarDatos();
                }
                else if (resultado == 4)
                {
					System.Console.Write("Ingrese el id de la sala cuyos datos desea eliminar: ");
					int idSala = Int32.Parse(System.Console.ReadLine());
                    int instruccion = daoSalaEspecializada.eliminar(idSala);
                    if(instruccion != 0)
                    {
						System.Console.WriteLine("La sala ha sido eliminada satisfactoriamente");
                    } else
                    {
						System.Console.WriteLine("No se pudo eliminar la sala seleccionada");
                    }
				}
                else if (resultado == 5)
                {
					System.Console.Write("Ingrese el id de la sala cuyos datos desea modificar: ");
					int idSala = Int32.Parse(System.Console.ReadLine());
					SalaEspecializada sala = solicitarDatosModificar(idSala);
					int instruccion = daoSalaEspecializada.modificar(sala);
					if (instruccion != 0)
					{
						System.Console.WriteLine("La sala se ha modificado con exito");
					}
					else
					{
						System.Console.WriteLine("No se pudo modificar la sala especializada.");
					}
				}
                else
                {
                    System.Console.WriteLine("Ingrese una opcion valida");
                }
            } while (resultado != 6);
        }

        public static SalaEspecializada solicitarDatosRegistro()
        {
            string respuesta;
			SalaEspecializada sala = new SalaEspecializada();
            System.Console.Write("Ingrese el nombre de la sala: ");
			sala.Nombre = System.Console.ReadLine();
            System.Console.Write("Ingrese el espacio en metros cuadrados de la sala: ");
            sala.EspacioMetrosCuadrados = Double.Parse(System.Console.ReadLine());
			System.Console.Write("Ingrese la torre donde esta ubicada la sala: ");
            sala.Torre = Char.Parse(System.Console.ReadLine());
			System.Console.Write("Ingrese el piso donde esta ubicada la sala: ");
            sala.Piso = Int32.Parse(System.Console.ReadLine());
			System.Console.Write("Ingrese el tipo de sala [1.UCI - 2.CIRUGIA - 3.EMERGENCIA]: ");
			respuesta = System.Console.ReadLine();
			if (respuesta.Equals("1"))
                sala.TipoSala = TipoSala.UCI;
            else if (respuesta.Equals("2"))
                sala.TipoSala = TipoSala.CIRUGIA;
            else
                sala.TipoSala = TipoSala.EMERGENCIA;
			System.Console.Write("Ingrese si tiene o no equipamiento [S/N]: ");
			respuesta = System.Console.ReadLine();
            if (respuesta.Equals("S"))
                sala.PoseeEquipamientoImagenologia = true;
            else
				sala.PoseeEquipamientoImagenologia = false;
			return sala;
        }
        
        public static SalaEspecializada solicitarDatosModificar(int idSalaEspecializada)
        {
            SalaEspecializada salaSeleccionada = daoSalaEspecializada.obtenerPorId(idSalaEspecializada);
            string respuesta;
			System.Console.WriteLine("Si desea mantener el valor actual de algun campo, simplemente presione ENTER:");
			System.Console.Write("Ingrese el nombre de la sala (VALOR ACTUAL: " + salaSeleccionada.Nombre + "): ");
			respuesta = System.Console.ReadLine();
            if (!respuesta.Equals(""))
                salaSeleccionada.Nombre = respuesta;
            System.Console.Write("Ingrese el espacio en metros cuadrados de la sala (VALOR ACTUAL: " + salaSeleccionada.EspacioMetrosCuadrados + "): ");
            respuesta = System.Console.ReadLine();
            if(!respuesta.Equals(""))
                salaSeleccionada.EspacioMetrosCuadrados = Double.Parse(respuesta);
            System.Console.Write("Ingrese la torre donde esta ubicada la sala (VALOR ACTUAL: " + salaSeleccionada.Torre + "): ");
			respuesta = System.Console.ReadLine();
			if (!respuesta.Equals(""))
                salaSeleccionada.Torre = char.Parse(respuesta);
			System.Console.Write("Ingrese el piso donde esta ubicada la sala (VALOR ACTUAL: " + salaSeleccionada.Piso + "): ");
            respuesta = System.Console.ReadLine();
            if (!respuesta.Equals(""))
                salaSeleccionada.Piso = Int32.Parse(respuesta);
            System.Console.Write("Ingrese el tipo de sala [1.UCI - 2.CIRUGIA - 3.EMERGENCIA] (VALOR ACTUAL: " + salaSeleccionada.TipoSala + "): ");
            respuesta = System.Console.ReadLine();
			if(!respuesta.Equals(""))
            {
				if (respuesta.Equals("1"))
					salaSeleccionada.TipoSala = TipoSala.UCI;
				else if (respuesta.Equals("2"))
					salaSeleccionada.TipoSala = TipoSala.CIRUGIA;
				else
					salaSeleccionada.TipoSala = TipoSala.EMERGENCIA;
			}
            System.Console.Write("Ingrese si tien o no equipamiento [S/N] (VALOR ACTUAL: ");
			System.Console.Write(salaSeleccionada.PoseeEquipamientoImagenologia == true ? "SI" : "NO");
			System.Console.Write("): ");
			respuesta = System.Console.ReadLine();
			if (!respuesta.Equals(""))
			{
                if (respuesta.Equals("S"))
                    salaSeleccionada.PoseeEquipamientoImagenologia = true;
                else
                    salaSeleccionada.PoseeEquipamientoImagenologia = false;
			}
			return salaSeleccionada;
        }
    }
}
