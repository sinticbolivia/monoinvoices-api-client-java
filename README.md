## Build jar

mvn clean package


## Install jar into local repository

mvn install

## Manually Install into local repository

Install the jar into local repository

mvn install:install-file -Dfile=monoinvoices-client-1.0.0.jar -DgroupId=net.sinticbolivia.facturacion.client -DartifactId=monoinvoices-client -Dversion=1.0.0 -Dpackaging=jar


## Conectar con el servidor

```
import net.sinticbolivia.facturacion.client.MonoInvoicesClient;
import net.sinticbolivia.facturacion.client.classes.LoginResponseData;

String username = "usuario";
String password = "contraena"
MonoInvoicesClient client = new MonoInvoicesClient();
client.setBaseUrl("https://facturacion.1bytebo.net");
LoginResponseData res = client.login(username, password);
//##obtener token
token = res.getToken();
if( this.token == null || this.token.isEmpty() )
	throw new Exception("ERROR: Token invalido");
```
 
## Obtner Parametricas

```
import net.sinticbolivia.facturacion.client.MonoInvoicesClient;
import net.sinticbolivia.facturacion.client.classes.LoginResponseData;
import net.sinticbolivia.facturacion.client.classes.RespuestaListaActividades;

String username = "usuario";
String password = "contraena"
MonoInvoicesClient client = new MonoInvoicesClient();
client.setBaseUrl("https://facturacion.1bytebo.net");
LoginResponseData res = client.login(username, password);
//##obtener token
token = res.getToken();
if( this.token == null || this.token.isEmpty() )
	throw new Exception("ERROR: Token invalido");
int sucursal = 0;
int puntoventa = 0;
RespuestaListaActividades actividades = client.obtenerActividades(sucursal, puntoventa);
for(Actividad a: actividades.listaActividades)
{
	System.out.println("CODIGO: " + a.codigoCaeb);
	System.out.println("DESCRIPCION: " + a.descripcion);
	System.out.println("TIPO ACTIVIDAD: " + a.tipoActividad);
}
```


## Crear Factura


```
import net.sinticbolivia.facturacion.client.MonoInvoicesClient;
import net.sinticbolivia.facturacion.client.classes.LoginResponseData;
import net.sinticbolivia.facturacion.client.classes.Factura;
import net.sinticbolivia.facturacion.client.classes.FacturaItem;


String username = "usuario";
String password = "contraena"
MonoInvoicesClient client = new MonoInvoicesClient();
client.setBaseUrl("https://facturacion.1bytebo.net");
LoginResponseData res = client.login(username, password);
//##obtener token
token = res.getToken();
if( this.token == null || this.token.isEmpty() )
	throw new Exception("ERROR: Token invalido");
	
Factura factura = new Factura();
factura.codigo_documento_sector = 1;
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
System.out.println("ID Factura: " + nuevaFactura.invoice_id );
System.out.println("URL Factura: " + nuevaFactura.print_url );
```

