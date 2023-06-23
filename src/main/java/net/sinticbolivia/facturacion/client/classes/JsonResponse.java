package net.sinticbolivia.facturacion.client.classes;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class JsonResponse 
{
	protected	String	response;
	protected	String	status;
	protected	Integer	code;
	protected	String 	message;
	protected	String	error;
	public		String 	module;
	public		String	controller;
	public		String	method;
	
	//protected	T		data;
	
	public JsonResponse()
	{
		
	}
	public void setResponse(String res) {this.response = res;}
	public String getResponse() {return this.response;}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setCode(Integer code) {this.code = code;}
	public Integer getCode() 
	{
		if( this.code == null )
			return -1;
		
		return this.code;
	}
	
	public void setMessage(String msg) {this.message = msg;}
	public String getMessage() {return this.message;}
	
	public void setError(String msg) {this.error = msg;}
	public String getError() {return this.error;}
	
	//public void setData(T data) { this.data = data;}
	//public T getData() {return this.data;}
	/**
	@SuppressWarnings({ "unchecked", "deprecation" })
    @JsonProperty("data")
    private void unpackNested(Map<String,Object> obj) throws InstantiationException, IllegalAccessException 
	{
		//this.data = (T) this.data.getClass().newInstance();
		//java.lang.reflect.Type t = this.getClass().getGenericSuperclass();
		//java.lang.reflect.ParameterizedType pt = (java.lang.reflect.ParameterizedType) t;
		//this.data = (T) pt.getActualTypeArguments()[0].getClass().newInstance();
		
		for(String key: obj.keySet())
		{
			System.out.println("NAME: " + key);
			
		}
		
        //this.brandName = (String)brand.get("name");
        //Map<String,String> owner = (Map<String,String>)brand.get("owner");
        //this.ownerName = owner.get("name");
    }
    **/
}
