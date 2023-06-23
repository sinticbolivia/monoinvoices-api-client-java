package net.sinticbolivia.facturacion.client.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Request 
{
	protected HashMap<String, String> headers;
	
	public Request()
	{
		this.headers = new HashMap<String, String>();
	}
	public void addHeader(String key, String value)
	{
		this.headers.put(key, value);
	}
	public Response request(String url, String method, Object data) throws InterruptedException, URISyntaxException, UnsupportedEncodingException, IOException 
	{
		System.out.println("URL: " + url);
		/*
		if( method == "GET" )
		{
			URI targetURI = new URI(url);
			java.net.http.HttpRequest.Builder builder = HttpRequest.newBuilder().uri(targetURI);
			builder.GET();
			
			HttpRequest httpRequest = builder.build();
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpResponse<String> response = null;
			Response res = null;
			try
			{
				response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
				res = new Response(response.body());
			}
			catch(IOException e)
			{
				httpRequest.
				InputStream error = ((HttpURLConnection) con).getErrorStream();
				byte[] bytes = error.readAllBytes();
				String json_error = new String(bytes, StandardCharsets.UTF_8);
				System.out.println("CATCH ERROR: " + json_error);
				res = new Response(json_error);
			}
			
			
			
			return res;
		}
		*/
		
		HttpURLConnection con = null;
		Response res = null;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			
			URL _url = new URL(url);
			con = (HttpURLConnection)_url.openConnection();
			con.setRequestMethod(method);
			con.setRequestProperty("Content-Type", "application/json");
			for(String key: this.headers.keySet())
			{
				//System.out.println(String.format("%s: %s", key, this.headers.get(key)));
				con.setRequestProperty(key, this.headers.get(key));
			}
			con.setDoInput(true);
			con.setDoOutput(true);
			if( (method == "POST" || method == "PUT") && data != null )
			{
				OutputStream os = con.getOutputStream();
				String json = mapper.writeValueAsString(data);
				//System.out.println("JSON: " + json);
				os.write(json.getBytes("utf-8"));
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); 
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while( (responseLine = br.readLine()) != null )
			{
				response.append(responseLine.trim());
			}
			res = new Response(response.toString());
		}
		catch(IOException e)
		{
			InputStream error = ((HttpURLConnection) con).getErrorStream();
			byte[] bytes = error.readAllBytes();
			String json_error = new String(bytes, StandardCharsets.UTF_8);
			System.out.println("CATCH ERROR: " + json_error);
			res = new Response(json_error);
		}
		
		return res;
	}
	public Response get(String url) throws UnsupportedEncodingException, InterruptedException, URISyntaxException, IOException
	{
		return this.request(url, "GET", null);
	}
	public Response post(String url, Object data) throws UnsupportedEncodingException, InterruptedException, URISyntaxException, IOException
	{
		return this.request(url, "POST", data);
	}
	public Response put(String url, Object data) throws UnsupportedEncodingException, InterruptedException, URISyntaxException, IOException
	{
		return this.request(url, "PUT", data);
	}
	public Response delete(String url) throws UnsupportedEncodingException, InterruptedException, URISyntaxException, IOException
	{
		return this.request(url, "DELETE", null);
	}
}
