package kvstore;
import java.io.*;
import java.util.*;

public class TTL extends TimerTask
{
    private String key,filename;

    public TTL(String key, String filename){
        this.key = key;
        this.filename = filename;
    }

    @Override
    public void run() {
    	
        if(this.key != null & this.key.length() > 0){
            try {
                KVStore.delete(this.key, this.filename);
            }
            catch(FileNotFoundException e){
            	System.out.println(e);
            }
             catch(CustomException e){
            	 System.out.println(e);
            }
             
        }
    }

}