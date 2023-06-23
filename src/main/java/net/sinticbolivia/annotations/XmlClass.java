package net.sinticbolivia.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XmlClass 
{
	public boolean 	isRoot() default false;
	public String	tag() default "";
	public boolean	xmlDeclaration() default false;
}
