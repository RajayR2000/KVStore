package kvstore;
import java.io.*;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;

public class KVStore
{
    private HashMap<String, String> map;
    private String path;

    public KVStore()
    {
        map = new HashMap<String, String>();
        path="E:\\" + "keystore.ser";
        File file = new File(path);
        
        try {
        	
            file.createNewFile();
            
        }catch (Exception e){
        	
        	System.out.println(e);
        	
        }
    }

    public KVStore(String path) throws CustomException
    {
    	File check = new File(path);
    	
    	if(!check.exists()) {
    		throw new CustomException("Path " + path + " is invalid");
    	}
    	
        map = new HashMap<String, String>();
        String newPath = path + "keystore.ser"; 
        		
        File file = new File(newPath);
    
        try {
        	
            file.createNewFile();
            
        }catch (Exception e){
        	
        	System.out.println(e);
        	
        }
    }

    
    public synchronized void create(String key, JSONObject value) throws FileNotFoundException, CustomException, IllegalArgumentException
    {
        create(key, value, 0);
    }

    public synchronized void create(String key, JSONObject value, long time) throws FileNotFoundException, IllegalArgumentException , CustomException
    {
        File file = new File(path);
        
        if(key.length() > 32 || key.length() <= 0) {
        	
            throw new CustomException("Key size does not meet the requirements");
            
        }
        
        
        else if((float)((value.length()*8)/1024.0)>16.0) {
        	
            throw new CustomException("Value size is greater than 16kb");
            
        }
        
        else if(!file.exists()) {
      
            throw new FileNotFoundException();
            
        }
        else {
        	
        	for(char c : key.toCharArray()) {
        		if(!Character.isLetter(c)) {
        			throw new IllegalArgumentException("Key must contain only alphabets");
        		}
        	}
        }
        if(file.length()>0)
        {
            map = getMap(path);
            if (map.containsKey(key)) {
            	 throw new CustomException("The pair with key "+ key + " is already present in storage");
            }
        }
        map.put(key, value.toString());
        System.out.println("The pair with key "+ key + "  has been added to the store");
        storeMap(path,map);

        if(time > 0) {
        	System.out.println("The pair with key "+ key + "will be deleted after " + time + " seconds");
        	 new Timer().schedule(new TTL(key,path), time*1000);
        }

        
    }

    public synchronized JSONObject read(String key) throws FileNotFoundException, CustomException, JSONException
    {
        File file = new File(path);
        
        if(!file.exists())
            throw new FileNotFoundException();
        
        else if(file.length()>0)
        {
            map = getMap(path);
            if(map.containsKey(key)) {
            	
                return new JSONObject(map.get(key));
                
            }
            else {
            	
                throw new CustomException("The pair with key "+ key + " is not present in the storage.Please enter a valid key");
                
            }
        }
        else {
        	throw new CustomException("File is empty");
        }
    }
    public synchronized  void delete(String key) throws FileNotFoundException, CustomException
    {
    	delete(key , path);
    }
   
    public synchronized static void delete(String key , String path) throws FileNotFoundException, CustomException
    {
        File file = new File(path);
        
        if(!file.exists()) {
            throw new FileNotFoundException();
        }
        
        else if(file.length()>0)
        {
            HashMap<String, String> storage = getMap(path);

            if(storage.containsKey(key))
            {
                storage.remove(key);
                System.out.println("The pair with key "+ key + " has been deleted");
                storeMap(path, storage);
                
            }
            else {
            	throw new CustomException("The pair with key "+ key + " is not present in the storage.Please enter a valid key");
            }
                
        }
        else {
        	throw new CustomException("File is empty");
        }
    }
    private static synchronized void storeMap(String filename, HashMap<String, String> map) 
    {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try
        {
             fos = new FileOutputStream(filename);
             oos = new ObjectOutputStream(fos);
             oos.writeObject(map);
             
             long oneGB = 1073741824;
            if((float)(new File(filename).length() / oneGB) > 1) { 
            	oos.close();
                fos.close();
            	throw new CustomException("File size is more than 1gb");
            }
            oos.close();
            fos.close();
        }catch(CustomException e){
        	System.out.println(e);
        }
        catch(IOException e) {
        	System.out.println(e);
        }
        
       
    }

    @SuppressWarnings("unchecked")
	private static synchronized HashMap<String, String> getMap(String path)
    {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        HashMap<String, String> map = null;
        try
        {
             fis = new FileInputStream(path);
             ois = new ObjectInputStream(fis);
             map = (HashMap<String , String>)ois.readObject();
             ois.close();
             fis.close();
             
        }catch(Exception e){
        	
        	e.printStackTrace();
        	
        }
        
        return map;
    }

    
    public void clearStorage() {
    	File file = new File(path);
    	file.delete();
    	System.out.println("Storage File has been deleted");
    }
    
}
