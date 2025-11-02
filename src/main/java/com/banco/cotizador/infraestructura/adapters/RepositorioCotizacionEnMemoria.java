package com.banco.cotizador.infraestructura.adapters;
import com.banco.cotizador.dominio.modelo.CotizacionHipotecaria; import com.banco.cotizador.dominio.puertos.RepositorioCotizacionPort; import org.springframework.stereotype.Component; import java.util.*;
@Component public class RepositorioCotizacionEnMemoria implements RepositorioCotizacionPort {
  private final List<CotizacionHipotecaria> almacen=new ArrayList<>(); public void guardar(CotizacionHipotecaria c){ almacen.add(c);} public List<CotizacionHipotecaria> obtenerTodas(){ return List.copyOf(almacen);} }
