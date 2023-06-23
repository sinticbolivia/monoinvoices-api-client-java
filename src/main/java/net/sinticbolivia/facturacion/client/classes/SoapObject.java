package net.sinticbolivia.facturacion.client.classes;

import java.lang.reflect.Field;

import net.sinticbolivia.annotations.XmlClass;
import net.sinticbolivia.annotations.XmlField;


public class SoapObject 
{
	
	
	public Field[] getFields()
	{
		Field[] publicFields = this.getClass().getFields();
		/*
		for(Field field: publicFields)
		{
			field.get(this);
		}
		*/
		return publicFields;
	}
	public String toXml() throws IllegalArgumentException, IllegalAccessException
	{
		String xml = "";
		for(Field field: this.getFields())
		{
			Object fieldValue = field.get(this);
			String tagName = field.getName();
			if( field.isAnnotationPresent(XmlField.class) ) 
			{
				XmlField fieldAnnotation = field.getAnnotation(XmlField.class);
				if( !fieldAnnotation.alias().isEmpty() )
					tagName = fieldAnnotation.alias();
				
				if( fieldAnnotation.nullable() && fieldValue == null )
				{
					xml += String.format("<%s xsi:nil=\"true\" />", tagName);
				}
				else
					xml += String.format("<%s>%s</%s>", tagName, fieldValue == null ? "" : fieldValue.toString(), tagName);
			}
			xml += String.format("<%s>%s</%s>", tagName, fieldValue == null ? "" : fieldValue.toString(), tagName);
		}
		if( this.getClass().isAnnotationPresent(XmlClass.class) )
		{
			XmlClass anno = this.getClass().getAnnotation(XmlClass.class);
			if( anno.isRoot() )
			{
				String rootTag = this.getClass().getSimpleName();
				String xmlDeclaration = anno.xmlDeclaration() ? "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" : "";
				if( !anno.tag().isEmpty() )
					rootTag = anno.tag();
				xml = String.format("%s<%s>\n%s</%s>", xmlDeclaration, rootTag, xml, rootTag);
			}
			
			
		}
		return xml;
	}
}
