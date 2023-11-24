package main.Entidades;

import java.util.Date;

public class EstadoAsignado implements Estado {
    @Override
    public void proceso(Incidente incidente) {
        Date fechaActual = new Date();
        long tiempoTranscurrido = fechaActual.getTime() - incidente.getFechaIncidente().getTime();

        if (tiempoTranscurrido < 24 * 60 * 60 * 1000) { // Menos de 24 horas
            System.out.println("Pendiente de revisión para el incidente " + incidente.getId());
        }
    }
}
