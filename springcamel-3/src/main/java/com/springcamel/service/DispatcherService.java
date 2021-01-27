package com.springcamel.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class DispatcherService {
	
	@Autowired
	JmsTemplate jmst;
	
	
	String jmsqueue = "Q.TEST";
	
	public void sendMessage(String message)
	{
		jmst.convertAndSend(jmsqueue,message);
		
		
		
	}

}
