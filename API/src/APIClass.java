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
				JSONArray data = jo.getJSONArray("results");

				if(data != null) {

					String[] first_names = new String[data.length()];
					String[] last_names = new String[data.length()];
					int[] age = new int[data.length()];

					for(int i = 0 ; i < data.length() ; i++) {
						JSONObject jso = data.getJSONObject(i);
						JSONObject o = jso.getJSONObject("person");
						first_names[i] = o.getString("first_name");
						last_names[i] = o.getString("last_name");
						age[i] = o.getInt("age");

						Person p = new Person(first_names[i], last_names[i], age[i]);
						System.out.println(p.toString());
						save.println(p);
					}
				}

			
				save.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
