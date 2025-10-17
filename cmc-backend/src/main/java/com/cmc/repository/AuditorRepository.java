package com.cmc.repository;

import com.cmc.model.Auditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Auditor> obtenerTodosLosActivos() {
    // La consulta ahora no necesita filtrar por 'estado'
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
