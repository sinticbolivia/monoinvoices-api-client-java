package net.sinticbolivia.facturacion.client;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.sinticbolivia.facturacion.client.classes.Actividad;
import net.sinticbolivia.facturacion.client.classes.Factura;
import net.sinticbolivia.facturacion.client.classes.FacturaItem;
import net.sinticbolivia.facturacion.client.classes.JsonResponseFactura;
import net.sinticbolivia.facturacion.client.classes.LoginResponseData;
import net.sinticbolivia.facturacion.client.classes.Parametrica;
import net.sinticbolivia.facturacion.client.classes.ParametrosConsultaDTO;
import net.sinticbolivia.facturacion.client.classes.PuntoVenta;
import net.sinticbolivia.facturacion.client.classes.RespuestaListaActividades;
import net.sinticbolivia.facturacion.client.classes.RespuestaListaParametricas;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class AppTest 
{
	protected static	String		token;
	protected	String		username = "1bytev2";
	protected	String		password = "1bytev2";
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    /*
    @Test
    public void testLogin()
    {
    	MonoInvoicesClient client = new MonoInvoicesClient();
    	client.setBaseUrl("https://facturacion.1bytebo.net");
    	try
    	{
    		LoginResponseData res = client.login(this.username, this.password);
    		token = res.getToken();
    		RespuestaListaActividades actividades = client.obtenerActividades(0, 0);
    		for(Actividad a: actividades.listaActividades)
    		{
    			System.out.println("CODIGO: " + a.codigoCaeb);
    			System.out.println("DESCRIPCION: " + a.descripcion);
    			System.out.println("TIPO ACTIVIDAD: " + a.tipoActividad);
    		}
    		RespuestaListaParametricas parametricas = client.obtenerMotivosAnulacion(0, 0);
    		for(Parametrica p: parametricas.listaCodigos)
    		{
    			System.out.println(String.format("Codigo: %s\nDescripcion: %s", p.codigoClasificador.toString(), p.descripcion));
    		}
    		parametricas = client.obtenerDocumentosIdentidad(0, 0);
    		for(Parametrica p: parametricas.listaCodigos)
    		{
    			System.out.println(String.format("Codigo: %s\nDescripcion: %s", p.codigoClasificador.toString(), p.descripcion));
    		}
    		parametricas = client.obtenerUnidadesMedida(0, 0);
    		for(Parametrica p: parametricas.listaCodigos)
    		{
    			System.out.println(String.format("Codigo: %s\nDescripcion: %s", p.codigoClasificador.toString(), p.descripcion));
    		}
    		List<PuntoVenta> puntosVenta = client.obtenerPuntosVenta();
    		for(PuntoVenta pv: puntosVenta)
    		{
    			System.out.println(String.format("ID: %l, Nombre: %s", pv.id, pv.nombre));
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR: " + e.getMessage());
    	}
    	
    }
    @Test
    public void testCrearFactura() throws Exception
    {
    	MonoInvoicesClient client = new MonoInvoicesClient();
    	client.setBaseUrl("https://facturacion.1bytebo.net");
    	LoginResponseData res = client.login(this.username, this.password);
		token = res.getToken();
    	if( this.token == null || this.token.isEmpty() )
    		throw new Exception("ERROR: Token invalido");
    	
    	Factura factura = new Factura();
    	factura.codigo_documento_sector	= 1;
    	factura.codigo_metodo_pago = 1;
    	factura.codigo_moneda = 1;
    	factura.codigo_sucursal = 0;
    	factura.punto_venta = 0;
    	factura.customer = "PPerez";
    	factura.customer_email = "1bytebo.net@gmail.com";
    	factura.nit_ruc_nif = "3301268";
    	factura.tipo_cambio	= 1.0;
    	factura.tipo_documento_identidad	= 1;
    	FacturaItem item = new FacturaItem();
    	item.codigo_actividad = "620900";
    	item.codigo_producto_sin = 83141l;
    	item.unidad_medida			= 58;
    	item.product_code			= "P001";
    	item.product_name			= "Producto de Prueba";
    	item.price					= 10.0;
    	item.quantity				= 1.0;
    	
    	factura.addItem(item);
    	
    	Factura nuevaFactura = client.crearFactura(factura);
    }
    @Test
    public void obtenerFactura() throws Exception
    {
    	MonoInvoicesClient client = new MonoInvoicesClient();
    	client.setBaseUrl("https://facturacion.1bytebo.net");
    	LoginResponseData res = client.login(this.username, this.password);
		this.token = res.getToken();
    	if( this.token == null || this.token.isEmpty() )
    		throw new Exception("ERROR: Token invalido");
    	Long id = 9281l;
    	Factura factura = client.obtenerFactura(id);
    	System.out.println(String.format("SIAT URL: %s", factura.siat_url));
    	System.out.println(String.format("PRINT URL: %s", factura.print_url));
    }
    */
    @Test
    public void testSoapObject() throws IllegalArgumentException, IllegalAccessException
    {
    	ParametrosConsultaDTO obj = new ParametrosConsultaDTO();
    	obj.motivoConsulta = "Motivo 123";
    	System.out.println(obj.toXml());
    }
}
