package net.sinticbolivia.facturacion.client.classes;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacturaData 
{
	public	Integer		excepcion;
	public	Long		nro_factura;
	public 	HashMap<String, Object> custom_fields;
	
	@JsonProperty("nro_factura")
	private void unpackNroFactura(Object obj)
	{
		if( obj == null || obj.toString().isEmpty() )
			return;
		
		this.nro_factura = Long.parseLong(obj.toString());
	}
}
