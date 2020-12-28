import java.io.*;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;

import kvstore.*;

public class EndUser {
	public static void main(String[] args)  {
		
		try {
			KVStore obj = new KVStore();
			//obj.clearStorage();
			
			JSONObject j1 = new JSONObject("{\"name\":\"John\", \"age\":30, \"car\":null }");
			JSONObject j2 = new JSONObject("{\"name\":\"Sam\", \"age\":35, \"car\":null }");
			
			
			obj.create("John" , j1);
			obj.create("Sam" , j2 , 10);
			obj.delete("Sam");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
