package net.sinticbolivia.facturacion.client.classes;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ActividadesData 
{
	@JsonAlias("RespuestaListaActividades")
	public RespuestaListaActividades	actividades;
}
