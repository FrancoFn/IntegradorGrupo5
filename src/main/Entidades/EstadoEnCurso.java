package main.Entidades;

import java.util.Date;

public class EstadoEnCurso implements Estado {
    @Override
    public void proceso(Incidente incidente) {
        Date fechaActual = new Date();

        if (!incidente.getTiempoResolucion().equals(fechaActual)) {
            System.out.println("Nuestros técnicos están trabajando en el incidente " + incidente.getId());
        }
    }
}
