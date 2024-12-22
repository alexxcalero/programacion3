/*
	Coloque sus datos completos:
    Codigo PUCP: 20206455
    Nombre Completo: Alex Calero Revilla
*/

package pe.edu.pucp.eventmastersoft.program;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import pe.edu.pucp.eventmastersoft.logistica.dao.LocalDAO;
import pe.edu.pucp.eventmastersoft.logistica.model.Local;
import pe.edu.pucp.eventmastersoft.logistica.model.TipoLocal;
import pe.edu.pucp.eventmastersoft.logistica.mysql.LocalMySQL;

public class Principal {
    public static void main(String[] args) throws Exception{
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Programa de registro de locales:");
        System.out.println("(sedes para la realizacion de eventos):");
        System.out.println("----------------------------------------------------------");
        System.out.print("Ingrese el nombre del local: ");
        String nombre = teclado.readLine();
        System.out.print("Ingrese la direccion del local: ");
        String direccion = teclado.readLine();
        System.out.print("Ingrese la capacidad del local (# max. de asistentes): ");
        int capacidad = Integer.parseInt(teclado.readLine());
        System.out.print("Ingrese el espacio en m2 del local: ");
        double espacioM2 = Double.parseDouble(teclado.readLine());
        System.out.println("Indique el tipo de local: ");
        System.out.println("1. TEATRO");
        System.out.println("2. AUDITORIO");
        System.out.println("3. ANFITEATRO");
        System.out.println("4. ESTADIO");
        System.out.print("Ingrese la opcion: ");
        int opTipoLocal = Integer.parseInt(teclado.readLine());
        
        TipoLocal tipoLocal = TipoLocal.valueOf(opTipoLocal==1?"TEATRO":(opTipoLocal==2?"AUDITORIO":(opTipoLocal==3?"ANFITEATRO":"ESTADIO")));
        
        Local local = new Local(nombre, direccion, capacidad, espacioM2, tipoLocal);
        LocalDAO daoLocal = new LocalMySQL();
        int resultado = daoLocal.insertar(local);
        
        if(resultado != 0)
            System.out.println("El local se ha registrado con exito.");
        else
            System.out.println("Ha ocurrido un error con el registro del local.");
    }
}
