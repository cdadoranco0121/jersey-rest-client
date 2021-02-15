package fr.doranco.jaxrs.jersey.client.launchers;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import fr.doranco.jaxrs.jersey.entity.Employe;

public class GetJsonToList {

	public static void main(String[] args) {
		Client client = Client.create();
		
		WebResource webResource = client.resource("http://localhost:9991/jersey/employes/list");
		
		Builder builder = webResource.accept(MediaType.APPLICATION_JSON)
				.header("content-type", MediaType.APPLICATION_JSON);
		
		ClientResponse response = builder.get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			System.out.println("Failed with HTTP code: " + response.getStatus());
			String error = response.getEntity(String.class);
			System.out.println("Error: " + error);
			return;
		}
		
		List<Employe> listeEmployes = response.getEntity(new GenericType<List<Employe>>() {});
		
		System.out.println("Output from server ...");
		System.out.println("(XML List) \n");
		
		for (Employe employe: listeEmployes) {
			System.out.println("\nEmploye:");
			System.out.println("\tEmploye Id: " + employe.getId());
			System.out.println("\tEmploye Nom: " + employe.getNom());
			System.out.println("\tEmploye Prenom: " + employe.getPrenom());
		}
	}

}
