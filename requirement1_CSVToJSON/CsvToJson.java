import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CsvToJson {

	public static void main(String[] args) {

		String csv = "academy_award_actresses.csv"; //The file to be converted. Must be in the project directory.
		
		// read the data from the .csv file and add each element to a JSONObject (and each JSONObject to a JSONArray)
		BufferedReader br = null;
		JSONArray jsonArr = new JSONArray();
		String line = "";
		
		try {
			br = new BufferedReader(new FileReader(csv));
			List<String> titleList = new ArrayList<String>(); //to keep an ordered list of titles for each data element
			int i = 0; //Variable to help capture the keys/titles
			while((line = br.readLine()) != null) {
				JSONObject jsonObj = new JSONObject();
				//Add values to array arr
				String[] arr = line.split(",");
				//Capture keys/titles
				if(i == 0){
					for(int j = 0; j < arr.length; j++) {
						titleList.add(arr[j].replace("\"", ""));
					}
					i = 1;
				//Populate JSON objects, add to JSON array
				} else {
					for(int j = 0; j < arr.length; j++) {
						jsonObj.put(titleList.get(j).replace("\"", ""), arr[j].replace("\"", ""));
					}
					jsonArr.add(jsonObj);
				}
			}
		} catch(IOException e) {
			e.getMessage();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch(IOException e) {
				e.getMessage();
			}
			
		}
		
	
		//write the data to a JSON file
		BufferedWriter bw = null;
		
		try{
			bw = new BufferedWriter(new FileWriter("academy_award_actresses.json",false));
			//convert to string and fix the format
			bw.write(jsonArr.toJSONString().replace("[","[\n\t").replace("{","{\n\t\t").replace("\",","\",\n\t\t").replace("},{","\n\t},\n\t{\t\t").replace("}]","\n\t}\n]"));
			System.out.println("JSON File Created");
		} catch(IOException e) {
			e.getMessage();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch(IOException e) {
				e.getMessage();
			}
			
		}
		
	}
	
}