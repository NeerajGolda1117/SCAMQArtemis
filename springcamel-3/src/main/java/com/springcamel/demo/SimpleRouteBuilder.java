package com.springcamel.demo;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		 from("file:D:/ApacheProject/Source").split().tokenize("\n").to("jms:queue:Q.TEST");
		
	}

}
