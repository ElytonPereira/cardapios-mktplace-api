package br.com.senai.cardapiosmktplaceapi.integration.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.senai.cardapiosmktplaceapi.integration.processor.ErrorProcessor;

@Component
public class ToEmail extends RouteBuilder{
	
	@Value("${email.url}")
	private String urlDeEnvio;
	
	@Autowired
	private ErrorProcessor errorProcessor;
	
	@Override
	public void configure() throws Exception {
		
		
	}

	
}
