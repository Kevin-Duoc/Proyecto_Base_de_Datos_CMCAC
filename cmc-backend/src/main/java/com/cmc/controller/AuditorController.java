package com.cmc.controller;

import com.cmc.model.Auditor;
import com.cmc.service.AuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auditores")
@CrossOrigin(origins = "http://localhost:5173") // Permite la conexión desde tu frontend de React
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    // Este método se activa cuando alguien llama a GET http://localhost:8080/api/auditores
    @GetMapping
    public List<Auditor> getTodosLosAuditores() {
        // Simplemente llama al service y devuelve lo que este le entregue
        return auditorService.listarTodosLosAuditoresActivos();
    }
}
