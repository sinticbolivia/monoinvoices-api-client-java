package net.sinticbolivia.facturacion.client.classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response
{
	protected	String body;
	
	public Response(String body)
	{
		this.body = body;
	}
	public String getBody() {return this.body;}
	
	public <T> T getObject(Class<?> objType) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructType(objType);
		T res = mapper.readValue(this.body, javaType);
		
		return res;
	}
	/*
	public <T> JsonResponse<T> getObject() throws JsonMappingException, JsonProcessingException
	{
		TypeReference<JsonResponse<T>> typeRef = new TypeReference<JsonResponse<T>>() {};
		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.registerSubtypes(LoginResponseData.class);
		
		JsonResponse<T> res = mapper.readValue(this.body, typeRef);
		return res;
	}
	public <T> JsonResponseArray<T> getArray() throws JsonMappingException, JsonProcessingException
	{
		TypeReference<JsonResponseArray<T>> typeRef = new TypeReference<JsonResponseArray<T>>() {};
		ObjectMapper mapper = new ObjectMapper();
		JsonResponseArray<T> res = mapper.readValue(this.body, typeRef);
		return res;
	}
	*/
}
