package com.cmc.repository;

import com.cmc.model.Auditor;
import com.cmc.model.DetalleComisionesAuditoriasMes;
import com.cmc.model.ErrorProceso;
import com.cmc.model.HistorialSueldos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AuditorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Llama al procedimiento principal
    public void ejecutarProcesoComisiones(String fecha, int limite) {
        String sql = "CALL SP_PROCESAR_COMISIONES_MES(?, ?)";
        jdbcTemplate.update(sql, fecha, limite);
    }

    //Obtiene los resultados del detalle
    public List<DetalleComisionesAuditoriasMes> obtenerDetalleComisiones() {
        String sql = "SELECT * FROM detalle_comisiones_auditorias_mes ORDER BY nombre_auditor";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new DetalleComisionesAuditoriasMes(
                        rs.getInt("mes_proceso"),
                        rs.getInt("anno_proceso"),
                        rs.getString("run_auditor"),
                        rs.getString("nombre_auditor"),
                        rs.getString("nombre_profesion"),
                        rs.getInt("comision_total_audit"),
                        rs.getInt("comision_monto_audit"),
                        rs.getInt("comision_prof_critica"),
                        rs.getInt("comision_extra"),
                        rs.getInt("total_comision_audit"),
                        rs.getInt("total_comision_empresa")
                )
        );
    }

    //Obtiene la bitácora de errores
    public List<ErrorProceso> obtenerBitacora() {
        String sql = "SELECT * FROM error_proceso ORDER BY correlativo DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new ErrorProceso(
                        rs.getInt("correlativo"),
                        rs.getString("sentencia_error"),
                        rs.getString("mensaje_error")
                )
        );
    }

    //Actualiza un sueldo (Trigger)
    public int actualizarSueldo(Long idAuditor, int nuevoSueldo) {
        String sql = "UPDATE auditor SET sueldo = ? WHERE id_auditor = ?";
        return jdbcTemplate.update(sql, nuevoSueldo, idAuditor);
    }

    //Obtiene el historial de cambios de sueldo
    public List<HistorialSueldos> obtenerHistorialSueldos() {
        String sql = "SELECT * FROM historial_sueldos ORDER BY fecha_mod DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new HistorialSueldos(
                        rs.getLong("id_log"),
                        rs.getLong("id_auditor"),
                        rs.getInt("sueldo_anterior"),
                        rs.getInt("sueldo_nuevo"),
                        rs.getString("usuario_mod"),
                        rs.getDate("fecha_mod")
                )
        );
    }
    
    //Obtiene todos los auditores
    public List<Auditor> obtenerTodosLosActivos() {
        String sql = "SELECT * FROM auditor ORDER BY appaterno";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Auditor(
                        rs.getLong("id_auditor"),
                        rs.getLong("numrun"),
                        rs.getString("dvrun"),
                        rs.getInt("cod_comuna"),
                        rs.getInt("cod_profesion"),
                        rs.getString("appaterno"),
                        rs.getString("apmaterno"),
                        rs.getString("nombre"),
                        rs.getInt("cod_estcivil"),
                        rs.getInt("puntaje"),
                        rs.getInt("sueldo"),
                        rs.getInt("cod_afp"),
                        rs.getInt("cod_isapre"),
                        rs.getInt("cod_tpcontrato"),
                        rs.getLong("numrun_sup")
                )
        );
    }
}