package com.example.realtimework.Modelo;

public class Productos {
   private String codigoProducto;
   private String nombreProducto;
   private Double Stock;
   private Double precioCosto;
   private Double precioVenta;

   public String getCodigoProducto() {
      return codigoProducto;
   }

   public void setCodigoProducto(String codigoProducto) {
      this.codigoProducto = codigoProducto;
   }

   public String getNombreProducto() {
      return nombreProducto;
   }

   public void setNombreProducto(String nombreProducto) {
      this.nombreProducto = nombreProducto;
   }

   public Double getStock() {
      return Stock;
   }

   public void setStock(Double stock) {
      Stock = stock;
   }

   public Double getPrecioCosto() {
      return precioCosto;
   }

   public void setPrecioCosto(Double precioCosto) {
      this.precioCosto = precioCosto;
   }

   public Double getPrecioVenta() {
      return precioVenta;
   }

   public void setPrecioVenta(Double precioVenta) {
      this.precioVenta = precioVenta;
   }

   public Productos(String codigoProducto, String nombreProducto, Double stock, Double precioCosto, Double precioVenta) {
      this.codigoProducto = codigoProducto;
      this.nombreProducto = nombreProducto;
      Stock = stock;
      this.precioCosto = precioCosto;
      this.precioVenta = precioVenta;
   }

   public Productos() {
   }
}
