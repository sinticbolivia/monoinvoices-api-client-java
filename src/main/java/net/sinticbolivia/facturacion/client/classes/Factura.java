package net.sinticbolivia.facturacion.client.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={ "cash", "authorization", "invoice_limite_date", "currency_code", "sector" })
public class Factura extends ApiObject
{
	public 	Long	invoice_id;
	public	Long	user_id;
	public	Long	store_id;
	public	Long 	customer_id;
	public	String 	customer;
	public	String 	customer_email;
	public	String 	nit_ruc_nif;
	public	Integer	codigo_sucursal;
	public	Integer	punto_venta;
	public	Integer	codigo_documento_sector;
	public	Integer	tipo_documento_identidad;
	public	Integer codigo_metodo_pago;
	public	Integer	codigo_moneda;
	public	Double	tipo_cambio;
	public	String	complemento;
	public	String	numero_tarjeta;
	public	Integer	tipo_factura_documento;
	public	Integer	tax_id;
	public	Double	tax_rate;
	public	Double	subtotal;
	public	Double	total_tax;
	public	double 	discount;
	public	Double	monto_giftcard;
	public	Double	total;
	public	Long	invoice_number;
	public	String	control_code;
	public	String	invoice_date_time;
	public	String	status;
	public	String	cuf;
	public	String	cufd;
	public	String	cafc;
	public	Long	evento_id;
	public	Integer	tipo_emision;
	public	Long	nit_emisor;
	public	String	siat_id;
	public	String	leyenda;
	public	String	siat_url;
	public	String	actividad_economica;
	public	Integer	ambiente;
	//public	HashMap<String, String>	data;
	public	String	data;
	public	String	print_url;
	public	List<FacturaItem>	items;
	public	HashMap<String, String>	meta;
	
	public Factura()
	{
		this.items = new ArrayList<>();
	}
	public void addItem(FacturaItem item)
	{
		item.calcularTotales();
		this.items.add(item);
	}
	public void calcularTotales()
	{
		this.subtotal = 0.0;
		this.total = 0.0;
		for(FacturaItem item: this.items)
		{
			item.calcularTotales();
			this.subtotal += item.total;
		}
		this.total = this.subtotal - this.discount;
	}
}
