package net.sinticbolivia.facturacion.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;

import net.sinticbolivia.facturacion.client.classes.Request;
import net.sinticbolivia.facturacion.client.classes.Response;
import net.sinticbolivia.facturacion.client.classes.RespuestaListaActividades;
import net.sinticbolivia.facturacion.client.classes.RespuestaListaParametricas;
import net.sinticbolivia.facturacion.client.classes.RespuestaListaProductos;
import net.sinticbolivia.facturacion.client.classes.Factura;
import net.sinticbolivia.facturacion.client.classes.JsonResponseActividades;
import net.sinticbolivia.facturacion.client.classes.JsonResponseFactura;
import net.sinticbolivia.facturacion.client.classes.JsonResponseFacturas;
import net.sinticbolivia.facturacion.client.classes.JsonResponseGeneric;
import net.sinticbolivia.facturacion.client.classes.JsonResponseLogin;
import net.sinticbolivia.facturacion.client.classes.JsonResponseParametrica;
import net.sinticbolivia.facturacion.client.classes.JsonResponsePdf;
import net.sinticbolivia.facturacion.client.classes.JsonResponseProductosServicios;
import net.sinticbolivia.facturacion.client.classes.JsonResponsePuntosVenta;
import net.sinticbolivia.facturacion.client.classes.LoginResponseData;
import net.sinticbolivia.facturacion.client.classes.PuntoVenta;

public class MonoInvoicesClient 
{
	protected	String baseUrl;
	protected	String	token;
	protected	Request lastRequest;
	protected	Response	lastResponse;
	
	public MonoInvoicesClient()
	{
		//SerializationFeature.FAIL_ON_EMPTY_BEANS;
	}
	public void setBaseUrl(String base)
	{
		this.baseUrl = base;
	}
	public void setToken(String t)
	{
		this.token = t;
	}
	protected String buildEndpoint(String path)
	{
		return this.baseUrl + "/api" + path;
	}
	public Request getLastRequest()
	{
		return this.lastRequest;
	}
	public Response getLastResponse()
	{
		return this.lastResponse;
	}
	protected Request instanceRequest() throws Exception
	{
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request req = new Request();
		req.addHeader("Authorization", String.format("Bearer %s", this.token));
		
		return req;
	}
	public LoginResponseData login(final String usr, final String pass) throws Exception
	{
		Object obj = new Object() 
		{
			public String username = usr;
			public String password = pass;
		};
		
		String endpoint = this.buildEndpoint("/v1.0.0/users/get-token");
		Request req = new Request();
		Response res;
		LoginResponseData loginData = null;
		try 
		{
			res = req.post(endpoint, obj);
			//System.out.println(res.getBody());
			//JsonResponse<LoginResponseData> json = res.getObject();
			JsonResponseLogin data = res.getObject(JsonResponseLogin.class);
			if( data.getCode() != 200 )
				throw new Exception(data.getError());
			
			//System.out.println("TOKEN: " + data.getData().getToken());
			//System.out.println("Username: " + data.getData().getUser().username);
			this.setToken(data.getData().getToken());
			loginData = data.getData();
		}
		catch (InterruptedException | URISyntaxException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginData;
	}
	public RespuestaListaActividades obtenerActividades(int sucursal, int puntoventa) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/siat/v2/actividades");
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		JsonResponseActividades res = response.getObject(JsonResponseActividades.class);
		if( res.getCode() != 200 )
			throw new Exception(res.getError());
		
		return res.data.actividades;
	}
	public RespuestaListaProductos obtenerProductosServicios(int sucursal, int puntoventa) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/siat/v2/lista-productos-servicios");
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		JsonResponseProductosServicios res = response.getObject(JsonResponseProductosServicios.class);
		if( res.getCode() != 200 )
			throw new Exception(res.getError());
		
		return res.data.productosServicios;
	}
	protected RespuestaListaParametricas obtenerParametrica(String endpoint, int sucursal, int puntoventa) throws Exception
	{
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		JsonResponseParametrica jres = response.getObject(JsonResponseParametrica.class);
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		
		return jres.data.respuestaListaParametricas;
	}
	public RespuestaListaParametricas obtenerMotivosAnulacion(int sucursal, int puntoventa) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/siat/v2/sync-motivos-anulacion");
		return this.obtenerParametrica(endpoint, sucursal, puntoventa);
	}
	public RespuestaListaParametricas obtenerDocumentosIdentidad(int sucursal, int puntoventa) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/siat/v2/sync-documentos-identidad");
		return this.obtenerParametrica(endpoint, sucursal, puntoventa);
	}
	public RespuestaListaParametricas obtenerUnidadesMedida(int sucursal, int puntoventa) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/siat/v2/sync-unidades-medida");
		return this.obtenerParametrica(endpoint, sucursal, puntoventa);
	}
	public List<PuntoVenta> obtenerPuntosVenta() throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/siat/v2/puntos-venta");
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		JsonResponsePuntosVenta jres = response.getObject(JsonResponsePuntosVenta.class);
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		
		return jres.data;
	}
	public Factura crearFactura(Factura factura) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices");
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.post(endpoint, factura);
		JsonResponseFactura jres = response.getObject(JsonResponseFactura.class);
		
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		
		return jres.data;
	}
	public Factura anularFactura(final Long id, final Long codigoMotivo) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/" + id.toString() + "/void");
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Object data = new Object() 
		{
			public Long invoice_id = id;
			public Long motivo_id = codigoMotivo;
		};
		
		Request request = this.instanceRequest();
		Response response = request.post(endpoint, data);
		JsonResponseFactura jres = response.getObject(JsonResponseFactura.class);
		
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		
		return jres.data;
	}
	public Factura obtenerFactura(Long id) throws Exception
	{
		String endpoint = this.buildEndpoint("/invoices/siat/v2/invoices/" + id.toString());
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		//System.out.println(response.getBody());
		JsonResponseFactura jres = response.getObject(JsonResponseFactura.class);
		
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		
		return jres.data;
	}
	/**
	 * 
	 * @param id
	 * @param medida Tamaño de la impresion oficio|rollo
	 * @throws Exception
	 */
	public String obtenerPdf(Long id, String medida) throws Exception
	{
		if( !medida.equals("rollo") )
			medida = "";
		String endpoint = this.buildEndpoint(String.format("/invoices/%d/pdf?tpl=", id, medida));
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		JsonResponsePdf jres = response.getObject(JsonResponsePdf.class);
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		return jres.getPdfBuffer();
	}
	public List<Factura> listadoFacturas(int page, int limit) throws Exception
	{
		String endpoint = this.buildEndpoint(String.format("/invoices/siat/v2/invoices?page=%d&limit=%d", page, limit));
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		this.lastRequest = request;
		this.lastResponse = response;
		JsonResponseFacturas jres = response.getObject(JsonResponseFacturas.class);
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		//List<Factura> items = new ArrayList<Factura>();
		
		return jres.data;
	}
	public JsonResponseGeneric reenviarFactura(Long id) throws Exception
	{
		String endpoint = this.buildEndpoint(String.format("/invoices/siat/v2/invoices/%d/reenviar", id));
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		JsonResponseGeneric jres = response.getObject(JsonResponseGeneric.class);
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		
		return jres;
	}
	public List<Factura> buscarFactura(String keyword) throws Exception
	{
		String endpoint = this.buildEndpoint(String.format("/invoices/siat/v2/invoices/search?keyword=%s", keyword));
		if( this.token.isEmpty() )
			throw new Exception("Token invalido");
		Request request = this.instanceRequest();
		Response response = request.get(endpoint);
		//System.out.println(response.getBody());
		JsonResponseFacturas jres = response.getObject(JsonResponseFacturas.class);
		
		if( jres.getCode() != 200 )
			throw new Exception(jres.getError());
		
		return jres.data;
	}
}
