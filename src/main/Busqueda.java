package main;

import main.Entidades.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Stream;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.persistence.Query;



public class Busqueda {
	
	//Quién fue el técnico con más incidentes resueltos en los últimos N días
	public static void masIncidentesResueltos() {
		EstadoIncidente estado = EstadoIncidente.RESUELTO;
        Query q = (Query) ManagerPersistence.getEntityManager().createQuery("SELECT i FROM Incidente i WHERE i.estadoInc = :doc");
        q.setParameter("doc", estado);
        List<Incidente> incidentes = q.getResultList();
		
		// Obtener la fecha límite
	    Date fechaLimite = new Date();
	            
	    // Leer el número de días desde la consola
	    int ultimosDias = obtenerUltimosDias();
	    
	    // Restar N días a la fecha actual
	    Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaLimite);
	    calendar.add(Calendar.DAY_OF_YEAR, -ultimosDias);
	    
	    Date fechaInicial = calendar.getTime();

	    // Filtrar y contar los incidentes resueltos por cada técnico
	    Map<Object, Long> cantidadIncidentes = incidentes.stream()
	    		.filter(incidente -> incidente.getFechaFinalizacion().after(fechaInicial) && incidente.getFechaFinalizacion().before(fechaLimite))
	            .collect(Collectors.groupingBy(
	                    incidente -> incidente.getTecnico().getNombre(),
	            		Collectors.counting()));

	    // Encontrar al técnico con más incidentes resueltos
	    Entry<Object,Long> tecnicoMasIncidentes = cantidadIncidentes.entrySet().stream()
	            .max(Map.Entry.comparingByValue())
	            .orElse(null);
	    
	    if (tecnicoMasIncidentes != null) {
            System.out.println("");
        	System.out.println("El técnico con más incidentes es: " + tecnicoMasIncidentes.getKey());
            System.out.println("Cantidad de incidentes resueltos: " + tecnicoMasIncidentes.getValue());
            System.out.println("");
        } else {
            System.out.println("No hay datos disponibles para determinar el técnico con más incidentes.");
        }
	}

	public static void masIncidentesResueltosPorEspecialidad() {
		EstadoIncidente estado = EstadoIncidente.RESUELTO;
        Query q = (Query) ManagerPersistence.getEntityManager().createQuery("SELECT i FROM Incidente i WHERE i.estadoInc = :doc");
        q.setParameter("doc", estado);
        List<Incidente> incidentes = q.getResultList();
        
        // Obtener la fecha límite
	    Date fechaLimite = new Date();
	            
	    // Leer el número de días desde la consola
	    int ultimosDias = obtenerUltimosDias();
	    
	    // Restar N días a la fecha actual
	    Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaLimite);
	    calendar.add(Calendar.DAY_OF_YEAR, -ultimosDias);
	    
	    Date fechaInicial = calendar.getTime();

	    // Filtrar y contar los incidentes resueltos por cada técnico
	    Map<Object, Long> incidentesResueltosPorTecnico = incidentes.stream()
	    	    .filter(incidente -> incidente.getFechaFinalizacion().after(fechaInicial) && incidente.getFechaFinalizacion().before(fechaLimite))
	    	    .collect(Collectors.groupingBy(
	    	    incidente -> Arrays.asList(incidente.getTecnico().getNombre(), incidente.getCategoria()),
	    	    Collectors.counting()
	    	    ));

	    
	  
	    // Encontrar al técnico con más incidentes resueltos
	    Entry<Object,Long> tecnicoMasIncidentesporCategoria = incidentesResueltosPorTecnico.entrySet().stream()
	    		.max(Map.Entry.comparingByValue())
	            .orElse(null);
	   	      
	    if (tecnicoMasIncidentesporCategoria != null) {
            System.out.println("");
        	System.out.println("El técnico con más incidentes resueltos es: "+ tecnicoMasIncidentesporCategoria.getKey()+" categoria.");
            System.out.println("");
        } else {
            System.out.println("No hay datos disponibles para determinar el técnico con más incidentes.");
        }
	    
	}

	private static int obtenerUltimosDias() {
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Ingrese la cantidad de días para buscar incidentes resueltos: ");
	    return scanner.nextInt();
	}
    
    
    //Quién fue el técnico que más rápido resolvió los incidentes
    public static void obtenerTecnicoMasRapido () {
    	
    	EstadoIncidente estado = EstadoIncidente.RESUELTO;
        Query q = (Query) ManagerPersistence.getEntityManager().createQuery("SELECT i FROM Incidente i WHERE i.estadoInc = :doc");
        q.setParameter("doc", estado);
        List<Incidente> incidentes = q.getResultList();
        
        // Calculo el tiempo promedio de resolucion
        Map<Object, Double> tiempoPromedioPorTecnico = incidentes.stream()
                .collect(Collectors.groupingBy(
                    incidente -> incidente.getTecnico().getNombre(), 
                    Collectors.averagingDouble(incidente -> incidente.getTiempoResolucionEnMinutos())
                ));
        
        // Identifico al técnico con el tiempo promedio más rápido
        Entry<Object, Double> tecnicoMasRapido = tiempoPromedioPorTecnico.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);
        
        if (tecnicoMasRapido != null) {
            System.out.println("");
        	System.out.println("El técnico que resuelve más rápido en promedio es: " + tecnicoMasRapido.getKey());
            System.out.println("Tiempo promedio de resolución: " + Busqueda.convertirString(tecnicoMasRapido.getValue()) + " minutos");
            System.out.println("");
        } else {
            System.out.println("No hay datos disponibles para determinar el técnico más rápido.");
        }
    }
    
    public static String convertirString(double decimal){
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##", symbols);
		return decimalFormat.format(decimal);
	}
}
    	