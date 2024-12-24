using EventMasterSoftDA.DAO;
using EventMasterSoftDA.MySQL;
using EventMasterSoftModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.IO;
using System.Globalization;

namespace EventMasterSoftTester
{
    internal class Program
    {
        private static ProductoraDAO daoProductora;
        private static EventoDAO daoEvento;
        private static int resultado;
        public static void Main(string[] args)
        {
            /* Para probar el listado de productoras */
            
            #region Probando el Listado de Productoras
            daoProductora = new ProductoraMySQL();
            BindingList<Productora> productoras = daoProductora.listarTodas();
            foreach (Productora p in productoras)
                System.Console.WriteLine(p);
            System.Console.ReadLine();
            #endregion
            
            /* --------------------------------------------- */

            /* Para probar el registro de eventos */

            #region Probando el Registro de Eventos
            daoEvento = new EventoMySQL();
            string ruta = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "Images", "Banner.png");
            Image image = Image.FromFile(ruta);
            MemoryStream ms = new MemoryStream();
            image.Save(ms, System.Drawing.Imaging.ImageFormat.Png);
            Evento evento = new Evento(productoras[0], 'T', "¡AY! ¿QUÉ SERA DE MÍ?", 150000.00, TipoEvento.OBRA_TEATRAL, DateTime.ParseExact("30-10-2024", "dd-MM-yyyy", CultureInfo.InvariantCulture), "Un musical original con canciones de Hombres G. Escrito y dirigido por Diego Lombardi", false, true, ms.ToArray());
            ms.Close();
            resultado = daoEvento.insertar(evento);
            if (resultado != 0)
                System.Console.WriteLine("El evento se ha registrado con éxito");
            else
                System.Console.WriteLine("Ha ocurrido un error con el registro del evento");
            System.Console.ReadLine();
            #endregion
            
            /* --------------------------------------------- */

            /* Para probar el listado de eventos */
            
            #region Probando el Listado de Eventos
            daoEvento = new EventoMySQL();
            BindingList<Evento> eventos = daoEvento.listarPorNombre("");
            foreach (Evento ev in eventos)
                ev.mostrarDatosGenerales();
            System.Console.ReadLine();
            #endregion
            
            /* --------------------------------------------- */

            /* Para probar el listado de eventos */
            
            #region Probando el Obtener por ID de un Evento
            daoEvento = new EventoMySQL();
            Evento eventObtenido = daoEvento.obtenerPorId(1);
            eventObtenido.mostrarTodosDatos();
            System.Console.ReadLine();
            #endregion
            
        }
    }
}