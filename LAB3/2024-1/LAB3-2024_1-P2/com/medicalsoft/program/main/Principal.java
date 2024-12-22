/*
	Nombre Completo: Alex Calero Revilla
	Codigo PUCP: 20206455
	Fecha: 16/09/2024
*/

package com.medicalsoft.program.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.medicalsoft.rrhh.model.Especialidad;
import com.medicalsoft.rrhh.model.Medico;
import com.medicalsoft.rrhh.model.Paciente;
import com.medicalsoft.rrhh.model.AtencionMedica;
import com.medicalsoft.infraestructura.model.Consultorio;
import com.medicalsoft.infraestructura.model.SalaEspecializada;
import com.medicalsoft.infraestructura.model.ExamenDiagnostico;
import com.medicalsoft.infraestructura.model.TipoExamen;
import com.medicalsoft.servicios.model.ConsultaPresencial;
import com.medicalsoft.servicios.model.Teleconsulta;
import com.medicalsoft.servicios.model.Plataforma;

public class Principal {
	public static void main(String[] args) throws Exception {
		// Se crean tres especialidades
		Especialidad esp01 = new Especialidad("MEDICINA INTERNA");
		Especialidad esp02 = new Especialidad("RADIOLOGIA");
		Especialidad esp03 = new Especialidad("OTORRINOLARINGOLOGIA");
		
		// Se crean tres médicos de distintas especialidades
		Medico med01 = new Medico("18282901", "NATALI", "LEIVA", "REYES", esp01, "062258", "030568");
		Medico med02 = new Medico("13876114", "RAUL", "NEIRA", "GORRITI", esp02, "059092", "030077");
		Medico med03 = new Medico("29813127", "LUIS", "PEREZ", "LU", esp03, "039811", "029899");
		
		// Se crea una paciente
		Paciente pac01 = new Paciente("17920084", "PATRICIA", "FERNANDEZ", "TELLO", 3873);
		
		// Se crea un consultorio
		Consultorio con01 = new Consultorio(17.8, 'A', 2, "204");
		
		// Se crea una sala especializada
		SalaEspecializada sal01 = new SalaEspecializada(39.8, 'B', 1, "CAYETANO", true);
		
		// Creamos una atencion medica de tipo Consulta Presencial para el 29 de Marzo a las 16:00
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		AtencionMedica atm01 = new ConsultaPresencial(pac01, med01, sdf.parse("29-03-2024 16:00"), "DOLOR DE ESPALDA", con01, false);
		
		// Creamos una atencion medica de tipo Examen Diagnostico para el 30 de Marzo a las 08:30
		AtencionMedica atm02 = new ExamenDiagnostico(pac01, med02, sdf.parse("30-03-2024 08:30"), sal01, TipoExamen.ECOGRAFIA);
		
		// Creamos una atencion medica de tipo Teleconsulta para el 01 de Abril a las 15:30
		AtencionMedica atm03 = new Teleconsulta(pac01, med03, sdf.parse("01-04-2024 15:30"), "DOLOR DE GARGANTA", Plataforma.ZOOM, "https://pucp.zoom.us/j/98403730464");
		
		// Relacionamos las atenciones con el paciente
		pac01.setAtencionesMedicas(new ArrayList<>());
		pac01.getAtencionesMedicas().add(atm01);
		pac01.getAtencionesMedicas().add(atm02);
		pac01.getAtencionesMedicas().add(atm03);
		
		// Consultamos datos de todas las atenciones médicas
		System.out.println("PRIMERA CONSULTA.....");
		System.out.println(atm01.consultarDatos());
		System.out.println(atm02.consultarDatos());
		System.out.println(atm03.consultarDatos());
		
		// Consultamos datos de únicamente las citas médicas del paciente 01
		System.out.println("SEGUNDA CONSULTA.....");
		pac01.listarCitasMedicasProgramadas();
	}
}
