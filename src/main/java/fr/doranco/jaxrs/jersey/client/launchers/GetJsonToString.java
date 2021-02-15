package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class GetJsonToString {

	public static void main(String[] args) {
		
		ClientConfig cfg = new DefaultClientConfig();
		cfg.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cfg);
		
		WebResource webResource = client.resource("http://localhost:9991/jersey/employes/employe-1-JSON");
		
		Builder builder = webResource.accept(MediaType.APPLICATION_JSON)
			.header("content-type", MediaType.APPLICATION_JSON);
		
		ClientResponse response = builder.get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			System.out.println("Failed with HTTP code: " + response.getStatus());
			String error = response.getEntity(String.class);
			System.out.println("Error: " + error);
			return;
		}
		
		String output = response.getEntity(String.class);
		System.out.println("Output from Server ...");
		System.out.println(output);
	}

}
