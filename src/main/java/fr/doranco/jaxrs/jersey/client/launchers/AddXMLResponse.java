package fr.doranco.jaxrs.jersey.client.launchers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import fr.doranco.jaxrs.jersey.entity.Employe;

public class AddXMLResponse {

	public static void main(String[] args) {
		
		// Employe
		Employe employe = new Employe("Victor", "HUGO", "développeur");

		// Client
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:9991/jersey/employes/add");
		
		Builder builder = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML);
		
		ClientResponse response = builder.post(ClientResponse.class, employe);
		
		if (response.getStatus() != Status.CREATED.getStatusCode()) {
			System.out.println("Failed with HTTP code: " + response.getStatus());
			String error = response.getEntity(String.class);
			System.out.println("Error: " + error);
			System.exit(1);
		}
		
		Employe employeAdded = response.getEntity(Employe.class);
		System.out.println("Code status: " + response.getStatus() + " OK");
		System.out.println("Output from Server ...");
		System.out.println(employeAdded);
	}

}
