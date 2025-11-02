package com.banco.cotizador.infraestructura.adapters;
import com.banco.cotizador.dominio.puertos.TasasMercadoPort; import org.springframework.stereotype.Component;
@Component public class TasasMercadoAdapter implements TasasMercadoPort {
  public double obtenerTasaAnual(String tipo){ String t=tipo==null?"":tipo.toUpperCase(); return switch(t){ case "PREMIUM"->6.5; case "NUEVO"->7.8; default->7.2; }; }
}
