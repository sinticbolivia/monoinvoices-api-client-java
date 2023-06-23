package net.sinticbolivia.facturacion.client.classes;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class User 
{
	public 		int		user_id;
	public 		String 	first_name;
	public 		String 	last_name;
	public	 	String 	username;
	public		String 	pwd;
	public		String	email;
	public		String	status;
	public		HashMap<String, String> meta;
	public		List<String> permissions;
	public		Integer	role_id;
	public		Integer	store_id;
	public		String 	role_key;
	public		String	avatar;
	public		String	last_modification_date;
	public		String	creation_date;
	
	
}
