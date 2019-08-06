package com.javainuse.step;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;




@Component
public class BufferedReaderExample {  

    public  List<String> readFile(String path) {
     
        FileInputStream fis = null;
        BufferedReader reader = null;
        List<String> strList=new ArrayList<String>();
        try {
        	 System.out.println(this.getClass().getResource("/").getPath());
        	 fis = new FileInputStream(path);
        	 reader = new BufferedReader(new InputStreamReader(fis));
        	 System.out.println("Reading File line by line using BufferedReader");
        	 String line = reader.readLine();
        	 while(line != null){
                System.out.println(line);
                strList.add(line);
                line = reader.readLine();
        	 }          
         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BufferedReaderExample.class.getName()).log(Level.ERROR, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BufferedReaderExample.class.getName()).log(Level.ERROR, null, ex);
         
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(BufferedReaderExample.class.getName()).log(Level.ERROR, null, ex);
            }
        }
		return strList;
    }
   
}



