package net.sinticbolivia.facturacion.client.classes;

public class JsonResponseArray<T>
{
	protected	String	status;
	protected	Integer	code;
	protected	String 	message;
	protected	T[]		data;
	
	public JsonResponseArray()
	{
		
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setCode(Integer code) {this.code = code;}
	public Integer getCode() {return this.code;}
	
	public void setMessage(String msg) {this.message = msg;}
	public String getMessage() {return this.message;}
	
	public void setData(T[] data) { this.data = data;}
	public T[] getData() {return this.data;}
}
