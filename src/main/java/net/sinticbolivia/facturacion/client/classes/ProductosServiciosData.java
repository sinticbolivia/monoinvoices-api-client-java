package net.sinticbolivia.facturacion.client.classes;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProductosServiciosData 
{
	@JsonAlias("RespuestaListaProductos")
	public	RespuestaListaProductos	productosServicios;
}
