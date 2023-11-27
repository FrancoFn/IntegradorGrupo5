package main;

import main.Entidades.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.stream.Collectors;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.persistence.Query;


public class Busqueda {
	
	//Quién fue el técnico con más incidentes resueltos en los últimos N días
    public static Tecnico MasIncidentesResueltos(List<Tecnico> tecnicos) {
    // Obtener la fecha límite
    Date fechaLimite = new Date();

    // Leer el número de días desde la consola
    int ultimosDias = obtenerUltimosDias();

    // Restar N días a la fecha actual
    fechaLimite = new Date(fechaLimite.getTime() - ultimosDias * 24 * 3600 * 1000);

    // Filtrar y contar los incidentes resueltos por cada técnico
    Map<Tecnico, Long> incidentesResueltosPorTecnico = tecnicos.stream()
            .flatMap(tecnico -> tecnico.getIncidentes().stream())
            .filter(incidente -> EstadoIncidente.RESUELTO.equals(incidente.getEstadoInc()))
            .filter(incidente -> incidente.getFechaFinalizacion() != null && incidente.getFechaFinalizacion())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    // Encontrar al técnico con más incidentes resueltos
        return incidentesResueltosPorTecnico.entrySet().stream()
                .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
                .orElse(null);
}

    public static Tecnico MasIncidentesResueltosPorEspecialidad(List<Tecnico> tecnicos, Especialidad especialidad) {
        // Obtener la fecha límite
        Date fechaLimite = new Date();

        // Leer el número de días desde la consola
        int ultimosDias = obtenerUltimosDias();

        // Restar N días a la fecha actual
        fechaLimite = new Date(fechaLimite.getTime() - ultimosDias * 24 * 3600 * 1000);

        // Filtrar y contar los incidentes resueltos por cada técnico
        Map<Tecnico, Long> incidentesResueltosPorTecnico = tecnicos.stream()
                .flatMap(tecnico -> tecnico.getIncidentes().stream())
                .filter(incidente -> EstadoIncidente.RESUELTO.equals(incidente.getEstadoInc()))
                .filter(incidente -> incidente.getFechaFinalizacion() != null && incidente.getFechaFinalizacion())
                .filter(incidente -> incidente.getTecnico().stream().anyMatch(tecnico -> tecnico.getEspecialidades().contains(especialidad)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Encontrar al técnico con más incidentes resueltos
        return incidentesResueltosPorTecnico.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static int obtenerUltimosDias() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de días para buscar incidentes resueltos: ");
        return scanner.nextInt();
    }

    
    //Quién fue el técnico con más incidentes resueltos de una determinada especialidad en los últimos N días
    /*public static Tecnico masIncidentesPorEspecialidad(List<Tecnico> tecnicos, Especialidad especialidad, int ultimosDias) {
        Date fechaLimite = new Date(); // Usa la fecha actual

        // Filtrar los incidentes de la especialidad específica
        List<Incidente> incidentesEspecialidad = tecnicos.stream()
                .flatMap(tecnico -> tecnico.getIncidentes().stream())
                .filter(incidente -> incidente.getTiempoResolucion() != null && incidente.getTiempoResolucion().after(fechaLimite))
                .filter(incidente -> incidente.getTecnico().stream().anyMatch(tecnico -> tecnico.getEspecialidades().contains(especialidad)))
                .collect(Collectors.toList());

        // Contar los incidentes resueltos por cada técnico en la especialidad
        Map<Tecnico, Long> incidentesResueltosPorTecnicoEspecialidad = incidentesEspecialidad.stream()
                .flatMap(incidente -> incidente.getTecnico().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Encontrar al técnico con más incidentes resueltos de la especialidad
        return incidentesResueltosPorTecnicoEspecialidad.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }*/
    
    
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
    	