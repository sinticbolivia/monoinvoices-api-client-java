package net.sinticbolivia.facturacion.client.classes;

public class LoginResponseData 
{
	protected String 	token;
	protected User		user;
	
	public LoginResponseData()
	{
		
	}
	public void setToken(String t) {this.token = t;}
	public String getToken() {return this.token;}
	
	public void setUser(User usr) {this.user = usr;}
	public User getUser() {return this.user;}
	
}
