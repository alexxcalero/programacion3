import java.util.ArrayList;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
/*
	Código PUCP:
	Nombre Completo:
	Fecha:
*/
class Principal{
	public static void main(String[] args) throws Exception{
		//Creamos un objeto para manejo de fechas
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//Creamos una productora
		Productora prod1 = new Productora("RUIDOS RITMICOS PRODUCCIONES");		
		//Creamos un artista (una banda de rock)
		Artista artGrupo = new Artista("LIBIDO","PERUANA");
		//Creamos a los integrantes de la banda (quienes también son artistas)
		Artista artInt1 = new Artista("SALIM VERA","PERUANA");
		Artista artInt2 = new Artista("MANOLO HIDALGO","PERUANA");
		Artista artInt3 = new Artista("ANTONIO JAUREGUI","PERUANA");
		Artista artInt4 = new Artista("JEFFRY FISCHMAN","PERUANA");
		//Asociamos los integrantes a la banda
		artGrupo.agregarArtista(artInt1);
		artGrupo.agregarArtista(artInt2);
		artGrupo.agregarArtista(artInt3);
		artGrupo.agregarArtista(artInt4);
		//Creamos un artista de obras teatrales
		Artista actor = new Artista("SALVADOR DEL SOLAR","PERUANA");
		//Creamos locales
		Local local1 = new Local("TEATRO NOS","Av. Camino Real 1037 - San Isidro",600,TipoLocal.TEATRO);
		Local local2 = new Local("TEATRO CANOUT", "Av. Petit Thouars 4550 - Miraflores", 1000,TipoLocal.TEATRO);
		//Creamos un evento de tipo concierto rock
		Evento evento1 = new Concierto("LIBIDO - LA REUNION",1500000.00,'T',TipoConcierto.ACUSTICO,true);
		//Asociamos los datos del evento
		evento1.agregarArtista(artGrupo);
		evento1.setLocal(local1);
		evento1.agregarFuncion(new Funcion(sdf.parse("14-09-2024"),LocalTime.of(20,00,00)));
		evento1.agregarFuncion(new Funcion(sdf.parse("15-09-2024"),LocalTime.of(16,30,00)));
		//Creamos un evento de tipo obra teatral
		Evento evento2 = new ObraTeatral("YO CINNA",5500.00,'A',5,true);
		//Asociamos los datos del evento
		evento2.agregarArtista(actor);
		evento2.setLocal(local2);
		evento2.agregarFuncion(new Funcion(sdf.parse("31-10-2024"),LocalTime.of(17,30,00)));
		evento2.agregarFuncion(new Funcion(sdf.parse("31-10-2024"),LocalTime.of(20,30,00)));
		//Asociamos los eventos a la productora
		prod1.agregarEvento(evento1);
		prod1.agregarEvento(evento2);
		//Imprimimos datos de los eventos
		System.out.print(prod1.consultarDatosEvento(0));
		System.out.print(prod1.consultarObrasTeatrales());
	}
}