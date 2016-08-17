package com.sunny.webServices;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class HelloWorldBean {
	
	private String hello = "Hello, ";
	
	public void HelloWorldBean() {
		
	}
	
	@WebMethod
	public String sayHello(String name) {
		return hello+name;
	}
}
