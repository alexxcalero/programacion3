package softrhtest;

import pe.edu.pucp.softrh.database.config.DBManager;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import pe.edu.pucp.softrh.compras.bo.OrdenCompraBO;
import pe.edu.pucp.softrh.compras.bo.PrendaSeleccionadaBO;
import pe.edu.pucp.softrh.compras.model.Estado;
import pe.edu.pucp.softrh.compras.model.OrdenCompra;
import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;
import pe.edu.pucp.softrh.prendas.bo.PrendaBO;
import pe.edu.pucp.softrh.prendas.bo.PromocionBO;
import pe.edu.pucp.softrh.prendas.model.Genero;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Promocion;
import pe.edu.pucp.softrh.prendas.model.Talla;
import pe.edu.pucp.softrh.prendas.model.TipoPrenda;
import pe.edu.pucp.softrh.prendas.model.TipoPromocion;
import pe.edu.pucp.softrh.usuarios.bo.AdministradorBO;
import pe.edu.pucp.softrh.usuarios.bo.ClienteBO;
import pe.edu.pucp.softrh.usuarios.bo.CuponBO;
import pe.edu.pucp.softrh.usuarios.bo.DireccionBO;
import pe.edu.pucp.softrh.usuarios.bo.TrabajadorBO;
import pe.edu.pucp.softrh.usuarios.bo.UsuarioBO;
import pe.edu.pucp.softrh.usuarios.model.Administrador;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Cupon;
import pe.edu.pucp.softrh.usuarios.model.Direccion;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

public class SoftRhTest {

    public static void main(String[] args) throws ParseException {
////        testConexionDB();

//        System.out.println("Prueba 1: Usuarios\n");
//        testModuloUsuarios();
//
//        System.out.print("Prueba 2: Prendas\n");
//        testModuloPrendas();
//        System.out.println("Prueba 3: Verificacion de ingreso y roles\n");
//		testModuloVerificacionIngresoConRol();
//
//		System.out.println("Prueba 4: Listar por nombres\n");
//		testModuloListarPrendaPorNombre();
//		System.out.println("Prueba 5: Listar prendas de una promocion");
//		testModuloListarPrendasPorPromocion();
//        OrdenCompraBO ordenCompraBO = new OrdenCompraBO();
//        
//        String uwu = ordenCompraBO.pagarConPaypal(9,"https://google.com","https://google.com");
//        
//        System.out.println(uwu);
//        ArrayList<PrendaSeleccionada> lista;
//        
//        lista = psBO.obtenerPorIdOrden(1);
//        
//        for(PrendaSeleccionada prenda : lista){
//            prenda.show();
//        }
//            
//        OrdenCompraBO ordenBO = new OrdenCompraBO();
//        System.out.println(ordenBO.insertarPrendaSeleccionada(1,10,12,10.));
//# idOrden, fidCliente, fidCupon, fidCarrito, fechaRegistro, fechaProcesado, fechaEntregado, fechaAnulado, estado, dni, correo, subtotal, paypal_id
//'30', '5', '1', '1', '2024-11-22', '2024-11-22', NULL, NULL, 'Pagado', '32457612', 'jp@gmail.com', '102.46', NULL
        
        /*
        Integer idOrdenCompra = 30;
        Date fechaRegistro = parseDate("2024-11-22");
        Date fechaProcesado = parseDate("2024-11-22");
        Date fechaEntregado = null; // No data provided
        Date fechaAnulado = null; // No data provided
        Estado estado = Estado.Pagado; // Assuming "Pagado" maps to PAGADO enum
        String dni = "32457612";
        String correo = "jp@gmail.com";
        Double subtotal = 102.46;
        Cliente cliente = new Cliente(); // Assuming Cliente is a POJO
        String paypal_id = "XDD"; // No data provided

        // Call the modificarOrdenCompra method
        OrdenCompraBO ordencompraBO = new OrdenCompraBO(); // Assuming a generated service client
//        Integer result = ordencompraBO.modificar(
//                idOrdenCompra, fechaRegistro,
//                fechaProcesado, fechaEntregado, fechaAnulado, estado,
//                dni, correo, subtotal, cliente, paypal_id
//        );

        // Output result
        //System.out.println("Result: " + result);
        OrdenCompra orden = ordencompraBO.obtenerPorId(36);
        System.out.println(orden.getPaypal_id());
        */
        
        CuponBO cuponBO = new CuponBO();
        
        ArrayList<Cupon>cupones = cuponBO.listarTodos();
        
        Cupon cupon = new Cupon();
        cupon.setIdCupon(1);
        cupon.setCodigo("TEST123");
        cupon.setDescripcion("Test description");
        cupon.setFechaInicio(new Date());
        cupon.setFechaFin(new Date());
        
        Cliente cliente = new Cliente();
        cliente.setTelefono("123456789");
        cliente.setFechaRegistro(new Date());
        cliente.setRecibePromociones(true);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cliente.ser"))) {
            oos.writeObject(cliente);
            System.out.println("Cliente serializado correctamente");
        } catch (IOException e) {
            System.err.println("Error durante la serialización:");
            e.printStackTrace();
        }
        
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cupon.ser"))) {
            oos.writeObject(cupon);
            System.out.println("Cupon serializado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void testConexionDB() {
        DBManager dbManager = DBManager.obtenerInstancia();
        System.out.println(dbManager);
        dbManager = DBManager.obtenerInstancia();
        System.out.println(dbManager);
        dbManager = DBManager.obtenerInstancia();
        System.out.println(dbManager);

        Connection conexion = dbManager.obtenerConexion();
    }

    private static void testModuloUsuarios() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int resultado;
        DireccionBO direccionBO = new DireccionBO();
//         Insertar direccion
//        resultado = direccionBO.insertar("Calle Las Rosas", "San Miguel", "Lima", "Lima", "Lima3", "Cruce de las avenidas La Marina con Universitaria", cliente1);
//        System.out.println(resultado);
//        resultado = direccionBO.insertar("Calle Los Girasoles", "Bellavista", "Callao", "Callao", "Callao2", "Cerca a Metro", cliente1);
//        System.out.println(resultado);
//        resultado = direccionBO.insertar("Calle Los Geranios", "Magdalena", "Lima", "Lima", "Lima6", "Por la Av. Sucre", cliente2);
//        System.out.println(resultado);
//
//         Modificar direccion
//        resultado = direccionBO.modificar(1, "Calle Los Robles", "Miraflores", "Lima", "Lima", "Lima10", "Cerca al Kennedy", cliente1);
//        System.out.println(resultado);
//
//         Eliminar direccion
//        resultado = direccionBO.eliminar(2);
//        System.out.println(resultado);
//
//         Listar direcciones
//        ArrayList<Direccion> direcciones = direccionBO.listarTodos();
//        for (Direccion d : direcciones) {
//            System.out.println(d.toString());
//        }
//
//         Obtener direccion por id
//        Direccion direcBuscada = direccionBO.obtenerPorId(1);
//        System.out.println(direcBuscada.toString());
    }

    private static void testModuloPrendas() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int resultado;
        PrendaBO prendaBO = new PrendaBO();
        PromocionBO promocionBO = new PromocionBO();

        // Insertar prenda
        resultado = prendaBO.insertar("Polo Never Surrender", "Polo manga corta con fit oversize", TipoPrenda.Polo, null, Talla.L, Genero.Mujer, "Negro", 40.99, 10);
        System.out.println(resultado);
        resultado = prendaBO.insertar("Polo RH Team", "Polo con logo de la tienda", TipoPrenda.Polo, null, Talla.L, Genero.Unisex, "Blanco", 90.99, 20);
        System.out.println(resultado);
        resultado = prendaBO.insertar("Polera RH Team", "Polera con logo de la tienda", TipoPrenda.Polera, null, Talla.XL, Genero.Unisex, "Marron", 129.99, 15);
        System.out.println(resultado);
        resultado = prendaBO.insertar("Casaca motera", "Casaca con estilo motera", TipoPrenda.Casaca, null, Talla.XL, Genero.Hombre, "Negro", 60.99, 12);
        System.out.println(resultado);
        resultado = prendaBO.insertar("Pantalon cammo", "Pantalon cargo", TipoPrenda.Pantalon, null, Talla.M, Genero.Hombre, "Verde", 80.99, 30);
        System.out.println(resultado);

        Prenda prenda1 = new Prenda("Polo 'Never Surrender'", "Polo manga corta, fit oversize", TipoPrenda.Polo, null, Talla.L, Genero.Mujer, "Negro", 40.99, 10);
        prenda1.setIdPrenda(1);

        // Modificar prenda
        resultado = prendaBO.modificar(5, "Pantalon rasgado", "Pantalon con un estilo rasgado en las piernas", TipoPrenda.Pantalon, null, Talla.S, Genero.Mujer, "Verdiblanco", 80.99, 35);
        System.out.println(resultado);

        // Eliminar prenda
        resultado = prendaBO.eliminar(2);
        System.out.println(resultado);

//      Listar prendas
        ArrayList<Prenda> prendas = prendaBO.listarTodos();
        for (Prenda p : prendas) {
            System.out.println(p.toString());
        }

        // Obtener prenda por id
        Prenda prendaBuscada = prendaBO.obtenerPorId(1);
        System.out.println(prendaBuscada.toString());

        // Insertar promocion
        TrabajadorBO trabajadorBO = new TrabajadorBO();
        Trabajador trabajador1 = trabajadorBO.obtenerPorId(4);

        resultado = promocionBO.insertar("Dia del niño", "Promoción por el día del niño", 20.0, TipoPromocion.Porcentaje, sdf.parse("2024-03-1"), sdf.parse("2024-04-1"), null, trabajador1);
        System.out.println(resultado);
        resultado = promocionBO.insertar("Black Friday", "Promoción por black friday", 10.0, TipoPromocion.Porcentaje, sdf.parse("2024-11-20"), sdf.parse("2024-12-1"), null, trabajador1);
        System.out.println(resultado);
        resultado = promocionBO.insertar("Navidad", "Promoción por época de navidad", 10.0, TipoPromocion.MontoFijo, sdf.parse("2024-12-1"), sdf.parse("2025-01-1"), null, trabajador1);
        System.out.println(resultado);

        // Modificar promocion
        resultado = promocionBO.modificar(1, "Año Nuevo", "Promocion por año nuevo", 15.0, TipoPromocion.MontoFijo, sdf.parse("2024-12-20"), sdf.parse("2025-01-15"), null, trabajador1);
        System.out.println(resultado);

        // Eliminar promocion
        resultado = promocionBO.eliminar(2);
        System.out.println(resultado);

        // Listar promociones
        ArrayList<Promocion> promociones = promocionBO.listarTodos();
        for (Promocion p : promociones) {
            System.out.println(p.toString());
        }

        // Obtener promocion por id
        Promocion promocionBuscada = promocionBO.obtenerPorId(1);
        System.out.println(promocionBuscada.toString());

    }

    private static void testModuloVerificacionIngresoConRol() {
        int resultado;
        String rol;

        UsuarioBO usuarioBO = new UsuarioBO();

        resultado = usuarioBO.verificarIngresoUsuario("alex@gmail.com", "calerinho");
        rol = usuarioBO.obtenerRolUsuario("mikler@gmail.com", "tenis123");
        System.out.println(resultado);
        System.out.println(rol);
    }

    private static void testModuloListarPrendaPorNombre() {
        PrendaBO prendaBO = new PrendaBO();
        ArrayList<Prenda> prendasCoincidentes = prendaBO.listarPorNombreDescripcion("polo");
        for (Prenda p : prendasCoincidentes) {
            System.out.println(p.toString());
        }
    }

    private static void testModuloListarPrendasPorPromocion() {
        PromocionBO promocionBO = new PromocionBO();
        ArrayList<Prenda> prendasSeleccionadas = promocionBO.listarPrendasAgregadas(12);
        for (Prenda p : prendasSeleccionadas) {
            System.out.println(p.toString());
        }
    }
}
