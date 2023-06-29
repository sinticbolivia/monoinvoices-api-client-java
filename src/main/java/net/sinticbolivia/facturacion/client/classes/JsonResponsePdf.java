package net.sinticbolivia.facturacion.client.classes;

import java.util.HashMap;

public class JsonResponsePdf extends JsonResponse 
{
	public HashMap<String, String> data;
	
	public String getPdfBuffer()
	{
		return this.data.get("buffer");
	}
}
