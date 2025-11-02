package com.banco.cotizador.aplicacion;
import com.banco.cotizador.dominio.modelo.CotizacionHipotecaria; import com.banco.cotizador.dominio.puertos.RepositorioCotizacionPort; import com.banco.cotizador.dominio.puertos.TasasMercadoPort;
public class CalcularCotizacionUseCase {
  private final TasasMercadoPort tasas; private final RepositorioCotizacionPort repo;
  public CalcularCotizacionUseCase(TasasMercadoPort tasas,RepositorioCotizacionPort repo){ this.tasas=tasas; this.repo=repo; }
  public CotizacionHipotecaria ejecutar(double monto,int plazo,String tipo){ double t=tasas.obtenerTasaAnual(tipo); CotizacionHipotecaria c=new CotizacionHipotecaria(monto,plazo,t); repo.guardar(c); return c; }
}
