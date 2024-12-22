/*
 * Coloque su codigo y nombre completo
 * Codigo PUCP: 20206455
 * Nombre Completo: Alex Calero Revilla
 */

package com.medicalsoft.program.main;

import com.medicalsoft.infraestructura.model.SalaEspecializada;
import com.medicalsoft.infraestructura.mysql.SalaEspecializadaMySQL;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
		// Creo una sala especializada
		SalaEspecializada sala = new SalaEspecializada();

		// Creo el dao de una sala especializada
		SalaEspecializadaMySQL salaDao = new SalaEspecializadaMySQL();

		// Declaro un scanner para leer desde consola
		Scanner sc = new Scanner(System.in);

		System.out.println("Sistema de registro de salas especializadas");
		System.out.println("--------------------------------------------------");

		System.out.print("Ingrese el nombre de la sala: ");
		sala.setNombre(sc.next());
		sc.next();  // Para leer el enter

		System.out.print("Ingrese el espacio en m2 de la sala: ");
		sala.setEspacioEnMetrosCuadrados(sc.nextDouble());

		System.out.print("Ingrese la torre donde esta ubicada la sala (letra): ");
		String torre;
		torre = sc.next();
		sala.setTorre(torre.charAt(0));

		System.out.print("Ingrese el piso donde esta ubicada la sala (numero): ");
		sala.setPiso(sc.nextInt());

		System.out.print("Indique si tiene o no equipamiento de imagenologia: ");
		String tiene;
		tiene = sc.next();
		if(tiene.compareTo("S") == 0)
			sala.setPoseeEquipamientoImagenologia(true);
		else
			sala.setPoseeEquipamientoImagenologia(false);

		if(salaDao.insertar(sala) == 1)
			System.out.println("Se ha realizado el registro con exito");
		else
			System.out.println("No se ha podido realizar el registro");
    }
}
