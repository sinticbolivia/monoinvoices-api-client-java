package net.sinticbolivia.facturacion.client.classes;

import net.sinticbolivia.annotations.XmlClass;
import net.sinticbolivia.annotations.XmlField;

@XmlClass(isRoot = true, xmlDeclaration = true)
public class ParametrosConsultaDTO extends SoapObject 
{
	@XmlField(nullable = true)
	public	String 	codigoInformacion;
	@XmlField(nullable = true)
	public	String 	motivoConsulta;
	@XmlField(nullable = true)
	public	String	numeroIdentificacion;
	@XmlField(nullable = true)
	public	String	primerApellido;
	@XmlField(nullable = true)
	public	String	tipoIdentificacion;
	
	public ParametrosConsultaDTO()
	{
		
	}
}
