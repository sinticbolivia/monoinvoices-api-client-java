package net.sinticbolivia.facturacion.client.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={ "store_id", "user_id", "data" })
public class FacturaItem extends ApiObject
{
	public	Long	item_id;
	public	Long	invoice_id;
	public	Long	product_id;
	public	String	product_code;
	public	String	product_name;
	public	double	price;
	public	double	quantity;
	public	double	discount;
	public	Integer	unidad_medida;
	public	Long	codigo_producto_sin;
	public	String	codigo_actividad;
	public	Double	total;
	public	String	numero_serie;
	public	String	numero_imei;
	
	
	public FacturaItem()
	{
		
	}
	public void calcularTotales()
	{
		Double subtotal = this.price * this.quantity;
		this.total = subtotal - this.discount;
	}
}
