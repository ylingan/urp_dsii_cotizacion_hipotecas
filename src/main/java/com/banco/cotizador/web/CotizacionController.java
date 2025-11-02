package com.banco.cotizador.web;
import com.banco.cotizador.aplicacion.CalcularCotizacionUseCase; import com.banco.cotizador.dominio.modelo.CotizacionHipotecaria; import jakarta.validation.constraints.*; import org.springframework.validation.annotation.Validated; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/cotizaciones") @Validated
public class CotizacionController {
  private final CalcularCotizacionUseCase useCase; public CotizacionController(CalcularCotizacionUseCase u){ this.useCase=u; }
  @GetMapping public CotizacionResponse cotizar(@RequestParam @Min(1) double monto,@RequestParam(name="plazoMeses") @Min(1) int plazo,@RequestParam @NotBlank String tipoCliente){
    CotizacionHipotecaria c=useCase.ejecutar(monto,plazo,tipoCliente); return new CotizacionResponse(c.getMonto(),c.getPlazoMeses(),c.getTasaAnual(),c.getCuotaMensual()); }
  public record CotizacionResponse(double monto,int plazoMeses,double tasaAnual,double cuotaMensual){}
}
