package com.cmc.service;

import com.cmc.model.Auditor;
import com.cmc.model.DetalleComisionesAuditoriasMes;
import com.cmc.model.ErrorProceso;
import com.cmc.model.HistorialSueldos;
import com.cmc.repository.AuditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditorService {

    @Autowired
    private AuditorRepository auditorRepository;

    public void lanzarCalculoComisiones(String fecha, int limite) {
        auditorRepository.ejecutarProcesoComisiones(fecha, limite);
    }
    
    public List<DetalleComisionesAuditoriasMes> verDetalleComisiones() {
        return auditorRepository.obtenerDetalleComisiones();
    }
    
    public List<ErrorProceso> verBitacora() {
        return auditorRepository.obtenerBitacora();
    }

    public int cambiarSueldoAuditor(Long idAuditor, int nuevoSueldo) {
        return auditorRepository.actualizarSueldo(idAuditor, nuevoSueldo);
    }

    public List<HistorialSueldos> verHistorialSueldos() {
        return auditorRepository.obtenerHistorialSueldos();
    }

    public List<Auditor> listarTodosLosAuditores() {
        return auditorRepository.obtenerTodosLosActivos();
    }
}
