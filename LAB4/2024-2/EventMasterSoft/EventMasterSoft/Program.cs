/* Ingrese sus datos:
 * Codigo PUCP: 20206455
 * Nombre Completo: Alex Calero Revilla
 */

using EventMasterSoftDA.DAO;
using EventMasterSoftDA.DAOImpl;
using EventMasterSoftModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics.Eventing.Reader;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoft
{
    public class Program
    {
        private static ProductoraDAO daoProductora;
        private static Productora productora;
        public static void Main(string[] args)
        {
            daoProductora = new ProductoraDAOImpl();
            int opcion = 0;
            int resultado;
            int idSeleccionado;
            do
            {
                Console.WriteLine("SISTEMA DE GESTION DE PRODUCTORAS");
                Console.WriteLine("------------------------------------------");
                Console.WriteLine("1. Registrar nueva productora.");
                Console.WriteLine("2. Listar todas las productoras.");
                Console.WriteLine("3. Obtener los datos de una productora.");
                Console.WriteLine("4. Eliminar una productora.");
                Console.WriteLine("5. Modificar una productora.");
                Console.WriteLine("6. Salir del sistema de gestión");
                Console.Write("Ingrese su opcion: ");
                opcion = Int32.Parse(Console.ReadLine());
                switch (opcion)
                {
                    case 1:
                        productora = solicitarDatosRegistro();
                        resultado = daoProductora.insertar(productora);
                        if (resultado != 0)
                            Console.WriteLine("Se ha registrado con exito.");
                        else
                            Console.WriteLine("Hubo un error al momento del registro.");
                        break;
                    case 2:
                        BindingList<Productora> productoras = daoProductora.listarTodas();
                        foreach (Productora p in productoras)
                            Console.WriteLine(p.mostrarDatos());
                        break;
                    case 3:
                        Console.Write("Ingrese el id de la productora cuyos datos desea visualizar: ");
                        idSeleccionado = Int32.Parse(Console.ReadLine());
                        productora = daoProductora.obtenerPorId(idSeleccionado);
                        Console.WriteLine(productora.mostrarDatos());
                        break;
                    case 4:
                        Console.Write("Ingrese el id de la productora que desea eliminar: ");
                        idSeleccionado = Int32.Parse(Console.ReadLine());
                        resultado = daoProductora.eliminar(idSeleccionado);
                        if(resultado != 0)
                            Console.WriteLine("La productora se ha eliminado con exito.");
                        else
                            Console.WriteLine("Hubo un error al momento de eliminar la productora.");
                        break;
                    case 5:
                        System.Console.Write("Ingrese el id de la productora cuyos datos desea modificar: ");
                        productora = solicitarDatosModificar(Int32.Parse(System.Console.ReadLine()));
                        resultado = daoProductora.modificar(productora);
                        if (resultado != 0)
                            Console.WriteLine("La productora se ha modificado con exito.");
                        else
                            Console.WriteLine("Hubo un error al momento de modificar la productora.");
                        break;
                    case 6:
                        break;
                    default:
                        Console.WriteLine("Ingrese una opcion valida.");
                        break;
                }
            } while (opcion != 6);
        }

        public static Productora solicitarDatosRegistro()
        {
            string respuesta;
            Productora productora = new Productora();
            Console.Write("Ingrese el nombre de la productora: ");
            productora.Nombre = Console.ReadLine();
            Console.Write("Ingrese la fecha de fundacion de la productora (dd-MM-yyyy): ");
            productora.FechaFundacion = DateTime.Parse(Console.ReadLine());
            Console.Write("Ingrese el numero de filiales que la productora tiene en Peru: ");
            productora.NumeroFilialesEnPeru = Int32.Parse(Console.ReadLine());
            Console.Write("Ingrese el presupuesto anual de la productora (S/.): ");
            productora.PresupuestoAnual = Double.Parse(Console.ReadLine());
            Console.Write("Indique si la productora ofrece alquiler de equipos [S/N]: ");
            respuesta = Console.ReadLine();
            if (respuesta.Equals("S"))
                productora.OfreceAlquilerEquipos = true;
            else
                productora.OfreceAlquilerEquipos = false;
            Console.Write("Indique el tipo de especializacion de la productora [1.AUDIOVISUAL - 2.LOGISTICA - 3.PUBLICIDAD]: ");
            respuesta = Console.ReadLine();
            if (respuesta.Equals("1"))
                productora.TipoEspecializacion = TipoEspecializacion.AUDIOVISUAL;
            else if (respuesta.Equals("2"))
                productora.TipoEspecializacion = TipoEspecializacion.LOGISTICA;
            else
                productora.TipoEspecializacion = TipoEspecializacion.PUBLICIDAD;
            productora.Activa = true;
            return productora;
        }

        public static Productora solicitarDatosModificar(int idProductora)
        {
            string respuesta;
            System.Console.WriteLine("Si desea mantener el valor actual de algun campo, simplemente presione ENTER:");
            Productora productora = daoProductora.obtenerPorId(idProductora);
            Console.Write("Ingrese el nombre de la productora (VALOR ACTUAL: " + productora.Nombre + "): ");
            respuesta = Console.ReadLine();
            if (!respuesta.Equals(""))
                productora.Nombre = respuesta;
            Console.Write("Ingrese la fecha de fundacion de la productora (dd-MM-yyyy) (VALOR ACTUAL: " + productora.FechaFundacion.ToString("dd-MM-yyyy") + "): ");
            respuesta = Console.ReadLine();
            if (!respuesta.Equals(""))
                productora.FechaFundacion = DateTime.Parse(respuesta);
            Console.Write("Ingrese el numero de filiales que la productora tiene en Peru (VALOR ACTUAL: " + productora.NumeroFilialesEnPeru + "): ");
            respuesta = Console.ReadLine();
            if(!respuesta.Equals(""))
                productora.NumeroFilialesEnPeru = Int32.Parse(respuesta);
            Console.Write("Ingrese el presupuesto anual de la productora (S/.) (VALOR ACTUAL: " + productora.PresupuestoAnual + "): ");
            respuesta = Console.ReadLine();
            if(!respuesta.Equals(""))
                productora.PresupuestoAnual = Double.Parse(respuesta);
            Console.Write("Indique si la productora ofrece alquiler de equipos [S/N] (VALOR ACTUAL: ");
            Console.Write(productora.OfreceAlquilerEquipos == true ? "SI" : "NO");
            Console.Write("): ");
            respuesta = Console.ReadLine();
            if(!respuesta.Equals(""))
            {
                if (respuesta.Equals("S"))
                    productora.OfreceAlquilerEquipos = true;
                else
                    productora.OfreceAlquilerEquipos = false;
            }
            Console.Write("Indique el tipo de especializacion de la productora [1.AUDIOVISUAL - 2.LOGISTICA - 3.PUBLICIDAD] (VALOR ACTUAL " + productora.TipoEspecializacion + "): ");
            respuesta = System.Console.ReadLine();
            if(!respuesta.Equals(""))
            {
                if (respuesta.Equals("1"))
                    productora.TipoEspecializacion = TipoEspecializacion.AUDIOVISUAL;
                else if (respuesta.Equals("2"))
                    productora.TipoEspecializacion = TipoEspecializacion.LOGISTICA;
                else
                    productora.TipoEspecializacion = TipoEspecializacion.PUBLICIDAD;
            }
            return productora;
        }
    }
}
