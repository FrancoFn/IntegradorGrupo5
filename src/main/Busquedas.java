import main.Entidades.Incidente;
import main.Entidades.Tecnico;
import main.Especialidad;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class Busquedas {
	
	//Quién fue el técnico con más incidentes resueltos en los últimos N días
    public static Tecnico MasIncidentesResueltos(List<Tecnico> tecnicos, int ultimosDias) {

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
    public static Tecnico obtenerIncidentesPorEspecialidad(List<Tecnico> tecnicos, Especialidad especialidad, int ultimosDias) {
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
    }
    
    
    //Quién fue el técnico que más rápido resolvió los incidentes
    public static Tecnico obtenerTecnicoConMasIncidentesResueltos(List<Tecnico> tecnicos) {
        Date fechaLimite = new Date();

        Predicate<Incidente> filtroFecha = incidente ->
                incidente.getTiempoResolucion() != null &&
                        incidente.getTiempoResolucion().after(fechaLimite);
        //Estado  == resuelto

        List<Incidente> incidentesResueltos = tecnicos.stream()
                .flatMap(tecnico -> tecnico.getIncidentes().stream())
                .filter(filtroFecha)
                .collect(Collectors.toList());


        Optional<Tecnico> tecnicoConMasIncidentes = incidentesResueltos.stream()
                .flatMap(incidente -> incidente.getTecnico().stream())
                .collect(Collectors.groupingBy(
                        tecnico -> tecnico,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        return tecnicoConMasIncidentes.orElse(null);
    }
}
