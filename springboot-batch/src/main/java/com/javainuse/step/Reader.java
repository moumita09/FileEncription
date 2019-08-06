package com.javainuse.step;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import com.javainuse.controller.JobInvokerController;

@Component
@StepScope
public class Reader implements ItemReader<String> {

	private String[] messages = { "javainuse.com",
			"Welcome to Spring Batch Example",
			"We use H2 Database for this example" };
	
	private int count = 0;

	String filePath;
	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		JobInvokerController jc = new JobInvokerController();
		
		filePath = jc.getJobParameters();
System.out.println("---------------------------------------"+filePath);
		List<String> strList = readFile(filePath);

		if (count < strList.size()) {
			return strList.get(count++);
		} else {
			count = 0;
		}
		return null;
	}
	
	 
	 public  List<String> readFile(String path) {  
	        FileInputStream fis = null;
	        BufferedReader reader = null;
	        List<String> strList=new ArrayList<String>();
	        try {
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