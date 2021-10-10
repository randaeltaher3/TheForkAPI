
import java.net.*;
import java.util.ArrayList;
import java.io.*;
import org.json.*;
import java.util.List;


public class TestCaseAPI {

	public static void main(String[] args) throws Exception {
		
		String url = "https://pokeapi.co/api/v2/pokemon?limit=30";
		URL obj = new URL (url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("GET");
		System.out.println("---Call to API with method GET---");
		
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		System.out.println("---Set Connection Time out---");
		
		connection.setInstanceFollowRedirects(false);
		HttpURLConnection.setFollowRedirects(false);
		System.out.println("---Handling Redirects---");
		
		//Handling response				
		//Reading the response
		int status = connection.getResponseCode();		
		System.out.println("---Getting response---");
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer contentResponse = new StringBuffer();
		while ((inputLine = input.readLine()) != null) {
				 contentResponse.append(inputLine);
				}
		input.close();
		
		System.out.println("URL: "+url);
		System.out.println("Response: "+contentResponse.toString());
		System.out.println("Status: "+status);

		
		//Convert String response to Json objects 
		JSONObject jsonResponse = new JSONObject(contentResponse.toString());
//		System.out.println("---Reading the JSON Response---");
//		System.out.println("Results: "+jsonResponse.getString("results"));
		
		//Access Subfields - Array value by index
		
//		JSONObject subfield = new JSONObject(jsonResponse.getJSONArray("results").getString(1));
//		System.out.println("Subfield: Array value of index 1: "+subfield);
		
		//Iterate over Results JSONArray
//		System.out.println("---Iterate over Results JSONArray--- ");
		
        JSONArray jsonArray = jsonResponse.getJSONArray("results");  
        List nameslist = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {  
        	
        	// store each object in JSONObject  
            JSONObject explrObject = jsonArray.getJSONObject(i);  
              
               
            // get field value from JSONObject using get() method  
            //if (i == 2 ) {
//            System.out.println(explrObject.get("url"));
            
          //Try to connect to the Sub APIs
            URL subURL = new URL (explrObject.get("url").toString());
            HttpURLConnection connection2 = (HttpURLConnection) subURL.openConnection();
            connection2.setRequestMethod("GET");
    		//System.out.println("---Call to SUB API with method GET---");
    		
    		//Reading the response
    		int status2 = connection2.getResponseCode();		
    		//System.out.println("---Getting SUB response---");
    		BufferedReader input2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
    		String inputLine2;
    		StringBuffer contentResponse2 = new StringBuffer();
    		while ((inputLine2 = input2.readLine()) != null) {
    				 contentResponse2.append(inputLine2);
    				}
    		input2.close();
    		
//    		System.out.println("SUB URL: "+subURL);
//    		System.out.println("Status 2: "+status2);
//    		System.out.println("Response 2: "+contentResponse2.toString());
    		
    		
    		//Convert String response 2 to Json objects 2
    		
    		JSONObject jsonResponse2 = new JSONObject(contentResponse2.toString());
//    		System.out.println("---Reading the JSON Response from the SUB API---");
//    		System.out.println("Types: "+jsonResponse2.getString("types"));
    		
    		//Access Subfields - Array value by index
    		
    		JSONObject typesObject = new JSONObject(jsonResponse2.getJSONArray("types").getString(0));
//    		System.out.println("Types array values: "+typesObject);
    		    		
//    		System.out.println("type: "+typesObject.getString("type"));
    		
    		
    		String nameObject = typesObject.getJSONObject("type").getString("name");
//    		System.out.println("Name Object:  "+nameObject);
    		
//    		System.out.println("-------Condition Normal------");
    		
    		if(nameObject.equals("normal")){
    			
//    			ArrayList<JSONObject> pokemonNames =new ArrayList<JSONObject>();
//    			pokemonNames.;;
    			
    			
    		    nameslist.add(explrObject.get("name"));
    			System.out.println("Normal Pokémon here! Name:  "+explrObject.get("name")+" URL: "+subURL);
    			
 
    	      }
    	      else{
    	    	  continue;
    	      }
    		//Close the connection
    		
    		//connection2.disconnect();
        }
    	
        System.out.println(nameslist.toString());
    		
		//Close the connection
		connection.disconnect();
		
		

	}

}
