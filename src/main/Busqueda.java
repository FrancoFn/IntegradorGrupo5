package main;

import main.Entidades.*;
import main.Especialidad;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.Query;
import javax.persistence.EntityManager;

public class Busqueda {
	
	//Quién fue el técnico con más incidentes resueltos en los últimos N días
    /*public static Tecnico masIncidentesResueltos(List<Tecnico> tecnicos, int ultimosDias) {

        Date fechaLimite = new Date();
        Date fechainicio = new Date() - ultimosDias;
        
        //condiciones
        incidente get.EstadoIncidente = Resuelto 
        fechaFinalizacion >= fechainicio y <=fechalimite
        
        // Contar los incidentes resueltos por cada técnico
        Map<Tecnico, Long> incidentesResueltosPorTecnico = tecnicos.stream()
                .flatMap(tecnico -> tecnico.getIncidentes().stream())
                .filter(incidente -> incidente.getTiempoResolucion() != null && incidente.getTiempoResolucion().after(fechaLimite))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Encontrar al técnico con más incidentes resueltos
        return incidentesResueltosPorTecnico.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    
    
    //Quién fue el técnico con más incidentes resueltos de una determinada especialidad en los últimos N días
    public static Tecnico masIncidentesPorEspecialidad(List<Tecnico> tecnicos, Especialidad especialidad, int ultimosDias) {
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
                    incidente -> incidente.getTecnico(), 
                    Collectors.averagingDouble(incidente -> incidente.getTiempoResolucionEnMinutos())
                ));
        
        // Identifico al técnico con el tiempo promedio más rápido
        Entry<Object, Double> tecnicoMasRapido = tiempoPromedioPorTecnico.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);
        
        if (tecnicoMasRapido != null) {
            System.out.println("");
        	System.out.println("El técnico que resuelve más rápido en promedio es: " + tecnicoMasRapido.toString() );
            System.out.println("Tiempo promedio de resolución: " + tecnicoMasRapido.getValue() + " minutos");
            System.out.println("");
        } else {
            System.out.println("No hay datos disponibles para determinar el técnico más rápido.");
        }
    }        
}
    	