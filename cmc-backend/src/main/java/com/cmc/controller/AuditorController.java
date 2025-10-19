package com.cmc.controller;

import com.cmc.model.Auditor;
import com.cmc.model.DetalleComisionesAuditoriasMes;
import com.cmc.model.ErrorProceso;
import com.cmc.model.HistorialSueldos;
import com.cmc.service.AuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    //POST http://localhost:8080/api/comisiones/procesar
    @PostMapping("/comisiones/procesar")
    public ResponseEntity<String> ejecutarProceso(@RequestParam String fecha, @RequestParam int limite) {
        try {
            auditorService.lanzarCalculoComisiones(fecha, limite);
            return ResponseEntity.ok("Proceso ejecutado para el período " + fecha);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al ejecutar proceso: " + e.getMessage());
        }
    }

    //GET http://localhost:8080/api/comisiones/detalle
    @GetMapping("/comisiones/detalle")
    public List<DetalleComisionesAuditoriasMes> getDetalleComisiones() {
        return auditorService.verDetalleComisiones();
    }

    //GET http://localhost:8080/api/bitacora
    @GetMapping("/bitacora")
    public List<ErrorProceso> getBitacora() {
        return auditorService.verBitacora();
    }
    
    //GET http://localhost:8080/api/auditores
    @GetMapping("/auditores")
    public List<Auditor> getTodosLosAuditores() {
        return auditorService.listarTodosLosAuditores();
    }

    // PUT http://localhost:8080/api/auditores/{id}/sueldo
    @PutMapping("/auditores/{id}/sueldo")
    public ResponseEntity<String> actualizarSueldo(@PathVariable Long id, @RequestParam int nuevoSueldo) {
        int filas = auditorService.cambiarSueldoAuditor(id, nuevoSueldo);
        if (filas > 0) {
            return ResponseEntity.ok("Sueldo del auditor " + id + " actualizado. Trigger ejecutado.");
        }
        return ResponseEntity.status(404).body("Auditor no encontrado.");
    }

    //GET http://localhost:8080/api/historial-sueldos
    @GetMapping("/historial-sueldos")
    public List<HistorialSueldos> getHistorialSueldos() {
        return auditorService.verHistorialSueldos();
    }
}
