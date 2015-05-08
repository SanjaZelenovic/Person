import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class APIClass {

	public static void main(String[] args) {
		try {
			URLConnection connection = new URL("https://randomapi.com/api/?key=EJM6-W3M9-0S0D-7KUA&id=6p15ww3&results=7").openConnection(); //otvorena je konekcija sa urlom
			BufferedReader br = new BufferedReader( new InputStreamReader(connection.getInputStream())); //ovde citamo response
			String response = br.readLine();
			

			PrintWriter save = new PrintWriter (new BufferedWriter(new FileWriter("data/file.out")));
			
			try {
				JSONObject jo = new JSONObject(response);
				System.out.println(jo.toString(4));
				JSONArray data = jo.getJSONArray("data");
				
				if(data != null) {
				   
					String[] first_names = new String[data.length()];
				    String[] last_names = new String[data.length()];
				    int[] age = new int[data.length()];
				    
				    for(int i = 0 ; i < data.length() ; i++) {
				    	JSONObject jso = data.getJSONObject(i);
				    	first_names[i] = jso.getString("first_name");
				    	last_names[i] = jso.getString("last_name");
				    	age[i] = jso.getInt("age");

				    	Person p = new Person(first_names[i], last_names[i], age[i]);
						save.println(p);
				    }
				}
				save.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
