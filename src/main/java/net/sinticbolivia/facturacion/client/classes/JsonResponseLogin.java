package net.sinticbolivia.facturacion.client.classes;

public class JsonResponseLogin extends JsonResponse 
{
	protected LoginResponseData	data;
	
	public void setData(LoginResponseData d)
	{
		this.data = d;
	}
	public LoginResponseData getData()
	{
		return this.data;
	}
}
