package com.javainuse.step;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.javainuse.controller.JobInvokerController;

public class Writer implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> messages) throws Exception {
		JobInvokerController jc = new JobInvokerController();
		String filePath = jc.getJobParameters();
		String path=filePath.substring(filePath.lastIndexOf("/")+1);
		String newpath=filePath.replace(path, "output_"+path);
	
		File file = new File(newpath);
		file.getName();
		if (file.createNewFile())
    	{
    	    System.out.println("File is created!");
    	} else {
    	    System.out.println("File already exists.");
    	}
		for (String msg : messages) {
	    	FileWriter writer = new FileWriter(file,true);
	    	writer.write(msg+ "\n");
	    	writer.close();
			System.out.println("Writing the data " + msg);
		}
		
	}
	
}