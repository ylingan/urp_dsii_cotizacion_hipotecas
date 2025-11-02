package com.banco.cotizador.dominio.modelo;
public class CotizacionHipotecaria {
  private final double monto; private final int plazoMeses; private final double tasaAnual; private final double cuotaMensual;
  public CotizacionHipotecaria(double monto,int plazoMeses,double tasaAnual){
    if(monto<=0||plazoMeses<=0||tasaAnual<=0) throw new IllegalArgumentException("Datos inválidos");
    this.monto=monto; this.plazoMeses=plazoMeses; this.tasaAnual=tasaAnual; this.cuotaMensual=calcular();
  }
  private double calcular(){ double tm=(tasaAnual/100.0)/12.0; return monto*tm/(1-Math.pow(1+tm,-plazoMeses)); }
  public double getMonto(){return monto;} public int getPlazoMeses(){return plazoMeses;} public double getTasaAnual(){return tasaAnual;} public double getCuotaMensual(){return cuotaMensual;}
}
