package com.banco.cotizador.infraestructura.config;
import com.banco.cotizador.aplicacion.CalcularCotizacionUseCase; import com.banco.cotizador.dominio.puertos.*; import org.springframework.context.annotation.*;
@Configuration public class BeanConfiguration {
  @Bean public CalcularCotizacionUseCase calcularCotizacionUseCase(TasasMercadoPort tasas, RepositorioCotizacionPort repo){ return new CalcularCotizacionUseCase(tasas,repo); }
}
