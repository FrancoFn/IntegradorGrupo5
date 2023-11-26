import main.Entidades.Especialidad;
import main.Entidades.Incidente;
import main.Entidades.Tecnico;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class Busquedas {

    public static Tecnico MasIncidentesResueltos(List<Tecnico> tecnicos, int ultimosDias) {

        Date fechaLimite = new Date();

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

    public static Tecnico obtenerTecnicoConMasIncidentesResueltos(List<Tecnico> tecnicos, int ultimosDias) {
        Date fechaLimite = new Date();

        Predicate<Incidente> filtroFecha = incidente ->
                incidente.getTiempoResolucion() != null &&
                        incidente.getTiempoResolucion().after(fechaLimite);


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
