package main.Entidades;

import java.util.Date;

public class EstadoResuelto implements Estado {
    @Override
    public void proceso(Incidente incidente) {
        Date fechaActual = new Date();

        if (incidente.getTiempoResolucion().equals(fechaActual) || incidente.getTiempoResolucion().after(fechaActual)) {
            for (Tecnico tecnico : incidente.getTecnico()) {
                tecnico.setDisponibilidad(true);
            }
            System.out.println("Incidente resuelto para el incidente " + incidente.getId());
        }
    }
}
