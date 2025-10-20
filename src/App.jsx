import { useState, useEffect } from 'react';
import './App.css';

function App() {
  //Estados para el proceso principal
  const [fecha, setFecha] = useState('202106');
  const [limite, setLimite] = useState('250000');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [detalleComisiones, setDetalleComisiones] = useState([]);
  const [loadingResultados, setLoadingResultados] = useState(false);

  //Estados para las nuevas secciones
  const [bitacora, setBitacora] = useState([]);
  const [historialSueldos, setHistorialSueldos] = useState([]);
  const [auditores, setAuditores] = useState([]);

  //Función para cargar la lista de auditores al iniciar la página
  const fetchAuditores = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/auditores');
      if (!response.ok) throw new Error('Error al cargar auditores');
      const data = await response.json();
      setAuditores(data);
    } catch (error) {
      console.error("Error cargando auditores:", error);
      setMessage(`Error al cargar la lista de auditores: ${error.message}`);
    }
  };

  useEffect(() => {
    fetchAuditores();
  }, []); //El array vacío significa que solo se ejecuta una vez al montar el componente

  //Función para ejecutar el proceso de comisiones
  const handleProcesarComisiones = async () => {
    setLoading(true);
    setMessage('Procesando comisiones, por favor espera...');
    setDetalleComisiones([]);
    setBitacora([]);
    setHistorialSueldos([]);

    const url = `http://localhost:8080/api/comisiones/procesar?fecha=${fecha}&limite=${limite}`;

    try {
      const response = await fetch(url, { method: 'POST' });
      const data = await response.text();
      if (!response.ok) throw new Error(data || 'Error del servidor');
      setMessage(data);
    } catch (error) {
      setMessage(`Error: ${error.message}`);
    } finally {
      setLoading(false);
    }
  };

  //Función para cargar los resultados del detalle
  const handleMostrarResultados = async () => {
    setLoadingResultados(true);
    setMessage('');
    setBitacora([]);
    setHistorialSueldos([]);

    const url = 'http://localhost:8080/api/comisiones/detalle';
    try {
      const response = await fetch(url);
      if (!response.ok) throw new Error(`Error del servidor: ${response.status} ${response.statusText}`);
      const data = await response.json();
      setDetalleComisiones(data);
    } catch (error) {
      setMessage(`Error al cargar resultados: ${error.message}`);
      setDetalleComisiones([]);
    } finally {
      setLoadingResultados(false);
    }
  };

  //Función para cargar la bitácora
  const handleMostrarBitacora = async () => {
    setDetalleComisiones([]);
    setHistorialSueldos([]);
    try {
      const response = await fetch('http://localhost:8080/api/bitacora');
      if (!response.ok) throw new Error('Error al cargar la bitácora');
      const data = await response.json();
      setBitacora(data);
    } catch (error) {
      setMessage(`Error al cargar bitácora: ${error.message}`);
    }
  };

  //Función para cargar el historial de sueldos
  const handleMostrarHistorial = async () => {
    setDetalleComisiones([]);
    setBitacora([]);
    try {
      const response = await fetch('http://localhost:8080/api/historial-sueldos');
      if (!response.ok) throw new Error('Error al cargar el historial');
      const data = await response.json();
      setHistorialSueldos(data);
    } catch (error) {
      setMessage(`Error al cargar historial: ${error.message}`);
    }
  };
  
  //Función para actualizar un sueldo
  const handleUpdateSueldo = async (idAuditor) => {
    const nuevoSueldo = prompt(`Ingresa el nuevo sueldo para el auditor ID ${idAuditor}:`);
    if (!nuevoSueldo || isNaN(nuevoSueldo)) {
      alert("Por favor, ingresa un número válido.");
      return;
    }

    try {
      const url = `http://localhost:8080/api/auditores/${idAuditor}/sueldo?nuevoSueldo=${nuevoSueldo}`;
      const response = await fetch(url, { method: 'PUT' });
      const message = await response.text();
      alert(message);
      fetchAuditores(); //Se recarga la lista de auditores para ver el sueldo actualizado
    } catch (error) {
      alert(`Error al actualizar sueldo: ${error.message}`);
    }
  };

  return (
    <div className="container">
      <h1>Panel de Control - CMC Auditores y Consultores</h1>
      <hr />
      
      {/* Sección para ejecutar el proceso*/}
      <section>
        <h2>Ejecutar Proceso de Comisiones</h2>
        <p>Ingresa los parámetros y haz clic para ejecutar el procedimiento `SP_PROCESar_COMISIONES_MES`.</p>
        <div>
          <label htmlFor="fechaPeriodo">Período (YYYYMM): </label>
          <input id="fechaPeriodo" type="text" value={fecha} onChange={(e) => setFecha(e.target.value)} />
        </div>
        <div>
          <label htmlFor="limiteComision">Límite Comisión: </label>
          <input id="limiteComision" type="number" value={limite} onChange={(e) => setLimite(e.target.value)} />
        </div>
        <button onClick={handleProcesarComisiones} disabled={loading}>
          {loading ? 'Procesando...' : `Ejecutar Proceso (${fecha})`}
        </button>
      </section>

      <hr />
      <section>
        <h2>Resultados y Bitácora</h2>
        <button onClick={handleMostrarResultados} disabled={loadingResultados}>
          {loadingResultados ? 'Cargando...' : 'Mostrar Detalle de Comisiones'}
        </button>
        <button onClick={handleMostrarBitacora}>Mostrar Bitácora</button>
        
        {message && <p><strong>{message}</strong></p>}

        {detalleComisiones.length > 0 && (
          <>
            <h3>Resultados del Proceso (Tabla DETALLE_COMISIONES)</h3>
            <table className="results-table">
              <thead>
                <tr>
                  <th>RUN Auditor</th>
                  <th>Nombre Auditor</th>
                  <th>Profesión</th>
                  <th>Total Comisión</th>
                </tr>
              </thead>
              <tbody>
                {detalleComisiones.map((detalle) => (
                  <tr key={detalle.runAuditor}>
                    <td>{detalle.runAuditor}</td>
                    <td>{detalle.nombreAuditor}</td>
                    <td>{detalle.nombreProfesion}</td>
                    <td>{'$' + detalle.totalComisionAudit.toLocaleString('es-CL')}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </>
        )}

        {bitacora.length > 0 && (
          <>
            <h3>Bitácora del Sistema (Tabla ERROR_PROCESO)</h3>
            <table className="results-table">
              <thead>
                <tr>
                  <th>Correlativo</th>
                  <th>Evento/Error</th>
                  <th>Mensaje</th>
                </tr>
              </thead>
              <tbody>
                {bitacora.map((log) => (
                  <tr key={log.correlativo}>
                    <td>{log.correlativo}</td>
                    <td>{log.sentenciaError}</td>
                    <td>{log.mensajeError}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </>
        )}
      </section>

      <hr />
      <section>
        <h2>Gestión de Auditores y Prueba de Trigger</h2>
        <p>Actualiza el sueldo de un auditor para disparar el `TRG_AUDIT_SUELDO_AUDITOR`.</p>
        
        <table className="results-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Sueldo Actual</th>
              <th>Acción</th>
            </tr>
          </thead>
          <tbody>
            {auditores.map(auditor => (
              <tr key={auditor.idAuditor}>
                <td>{auditor.idAuditor}</td>
                <td>{`${auditor.nombre} ${auditor.appaterno}`}</td>
                <td>{'$' + auditor.sueldo.toLocaleString('es-CL')}</td>
                <td>
                  <button onClick={() => handleUpdateSueldo(auditor.idAuditor)}>
                    Cambiar Sueldo
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        
        <br/>
        <button onClick={handleMostrarHistorial}>Mostrar Historial de Cambios de Sueldo</button>

        {historialSueldos.length > 0 && (
          <>
            <h3>Historial de Cambios (Tabla HISTORIAL_SUELDOS)</h3>
            <table className="results-table">
              <thead>
                <tr>
                  <th>ID Auditor</th>
                  <th>Sueldo Anterior</th>
                  <th>Sueldo Nuevo</th>
                  <th>Usuario</th>
                  <th>Fecha</th>
                </tr>
              </thead>
              <tbody>
                {historialSueldos.map(hist => (
                  <tr key={hist.idLog}>
                    <td>{hist.idAuditor}</td>
                    <td>{'$' + hist.sueldoAnterior.toLocaleString('es-CL')}</td>
                    <td>{'$' + hist.sueldoNuevo.toLocaleString('es-CL')}</td>
                    <td>{hist.usuarioMod}</td>
                    <td>{new Date(hist.fechaMod).toLocaleDateString('es-CL')}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </>
        )}
      </section>
    </div>
  );
}

export default App;