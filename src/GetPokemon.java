import java.util.*;
import java.net.*;
import java.net.http.HttpRequest;
import java.net.http.*;
import java.io.*;


public class GetPokemon {

	public static void main(String[] args) throws Exception {
		
		HttpRequest.newBuilder(new URI("https://postman-echo.com/get"));
		 
		HttpRequest.newBuilder()
		  .uri(new URI("https://postman-echo.com/get"));
		
		HttpRequest request = HttpRequest.newBuilder()
				  .uri(new URI("https://postman-echo.com/get"))
				  .headers("limit", "30")
				  .GET()
				  .build();
		  
		System.out.println("Call to API with method GET with parameters");
		
		//HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		

	}

}
