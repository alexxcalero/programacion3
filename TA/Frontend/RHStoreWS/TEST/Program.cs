using RHStoreBaseBO;
using RHStoreBaseBO.ServiciosWeb;
using RHStoreComprasBO;
/*
PayPalAPI api = new PayPalAPI();

string url = api.create_order(3, 3, "USD", 180.0, "https://google.com", "https://google.com", "RHStore");

Console.WriteLine(url);

string userInput = Console.ReadLine();

bool completed = api.capture_order(url);

if (completed)
{
    Console.WriteLine("NICE");
}
*/
OrdenCompraBO ordenCompraBO = new OrdenCompraBO();

ordenCompra orden = ordenCompraBO.obtenerPorId(36);


Console.WriteLine(orden.fechaEntregado);
