package com.cmc.service;

import com.cmc.model.Auditor;
import com.cmc.repository.AuditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditorService {

    // Con @Autowired, Spring inyecta automáticamente una instancia de AuditorRepository
    @Autowired
    private AuditorRepository auditorRepository;

    // Este método simplemente delega la llamada al repository
    public List<Auditor> listarTodosLosAuditoresActivos() {
        return auditorRepository.obtenerTodosLosActivos();
    }
}
