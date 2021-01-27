package com.springcamel.controller;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;

import org.apache.camel.impl.DefaultCamelContext;

import com.springcamel.demo.SimpleRouteBuilder;
import com.springcamel.service.DispatcherService;

@RestController
public class MessageController {
	
	@Autowired
	DispatcherService dser;
	
	
	@PostMapping(value="sendusermsg")
	public ResponseEntity<String> sendermsg(@RequestBody String message)
	{
		dser.sendMessage(message);
		
		
		return new ResponseEntity<String>("Message sent was " + message, HttpStatus.OK);
	}
	
	@GetMapping(value="sendfiletext")
	public ResponseEntity<String> senderfile()
	{
		
		
		
	       SimpleRouteBuilder routeBuilder = new SimpleRouteBuilder();
	        CamelContext ctx = new DefaultCamelContext();
	        
	        //configure jms component        
	        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
	        ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
	        
	        try {
	            ctx.addRoutes(routeBuilder);
	            ctx.start();
	            Thread.sleep(1000);
	            ctx.stop();
	            return new ResponseEntity<String>("Message sent ", HttpStatus.OK);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<String>("Message not sent", HttpStatus.EXPECTATION_FAILED);
	        }
		
		
		
		
	}

}
